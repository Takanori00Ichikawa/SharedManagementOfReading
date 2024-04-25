package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 読書記録を取得します。
			RecordOfReadingLogic logic = new RecordOfReadingLogic();

			// セッションスコープに保存されたユーザー情報を取得します。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// カウンターで使う数値を取得します。
			int countReading = logic.countReading(user.getId());
			int countIsFinished = logic.countIsFinished(user.getId());
			int countTotal = logic.countTotal(user.getId());

			// リクエストスコープに数値を設定します。
			request.setAttribute("countReading", countReading);
			request.setAttribute("countIsFinished", countIsFinished);
			request.setAttribute("countTotal", countTotal);

			List<RecordOfReadingModel> recordsReading = null;
			List<RecordOfReadingModel> recordsFinishedReading = null;

			if (request.getParameter("key") != null) {
				// 検索キーワードがある場合。
				recordsReading = logic.findReading(user.getId(), request.getParameter("key"));
				recordsFinishedReading = logic.findFinishedReading(user.getId(), request.getParameter("key"));
				// 検索テキストボックス表示用
				request.setAttribute("key", request.getParameter("key"));
			} else {
				// 検索キーワードが無い場合。
				recordsReading = logic.findReading(user.getId());
				recordsFinishedReading = logic.findFinishedReading(user.getId());
			}

			// メイン画面の読書中の本の一覧で使用する。
			request.setAttribute("recordsReading", recordsReading);

			// メイン画面の読了本の一覧で使用する。
			request.setAttribute("recordsFinishedReading", recordsFinishedReading);

			// 今日の日付を取得します。
			Date today = new Date();
			request.setAttribute("today", today);

			// メイン画面にフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
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
