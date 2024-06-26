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

import logic.UserLogic;
import model.UserModel;
import settings.MessageSettings;
import settings.PageSettings;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログイン画面へフォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームからリクエストパラメータを取得します。
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			// リクエストパラメータを元に、ユーザーを検索する。
			UserLogic logic = new UserLogic();
			UserModel user = logic.find(email, password);

			if (user == null) {
				// ユーザーが見つからなかった時は、エラーメッセージをリクエストスコープに設定する。
				request.setAttribute("error", MessageSettings.MSG_LOGIN_FAILURE);

				// ログインに使用した情報を再表示する為に、リクエストスコープに保存する。
				user = new UserModel();
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				request.setAttribute("user", user);

				// ログインページへフォワードする。
				RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
				dispatcher.forward(request, response);

				return;
			}

			// ユーザーが見つかった時は、ユーザーモデルをセッションに保存し、メイン画面にリダイレクトする。
			// ユーザーモデルがセッションに保存されている事でログイン状態を保持する。
			// セッションからユーザーモデルを削除する事でログアウトとする。
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// Mainへリダイレクトする。
			response.sendRedirect(request.getContextPath() + "/Main");

			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);
		}
	}

}
