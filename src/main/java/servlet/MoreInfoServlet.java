package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.RecordOfReadingLogic;
import model.RecordOfReadingModel;
import model.UserModel;
import settings.PageSettings;
import validation.ValidationUtil;

/**
 * Servlet implementation class MoreInfoServlet
 */
@WebServlet("/MoreInfo")
public class MoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// idがint型でなかったら、Mainへリダイレクトします。
			if (!ValidationUtil.isInteger(request.getParameter("id"))) {
				response.sendRedirect(request.getContextPath() + "/Main");
			}

			// 指定の本の読書記録を取得します。
			RecordOfReadingLogic logic = new RecordOfReadingLogic();

			// セッションスコープに保存されたユーザー情報を取得します。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// 指定ID、指定ユーザーIDの読書記録を取得します。
			RecordOfReadingModel model = logic.find(Integer.parseInt(request.getParameter("id")), user.getId());

			if (model == null) {
				// 読書記録を取得出来なかったら、Mainにリダイレクトする。
				response.sendRedirect(request.getContextPath() + "/Main");
				return;
			}

			// 読書記録詳細表示ページへフォワードする。
			request.setAttribute("recordOfReading", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/moreInfo.jsp");
			dispatcher.forward(request, response);

			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
