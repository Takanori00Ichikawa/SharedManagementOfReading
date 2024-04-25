package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
import validation.RecordOfReadingValidation;
import validation.ValidationUtil;

/**
 * Servlet implementation class RecordOfReadingUpdateServlet
 */
@WebServlet("/RecordOfReadingUpdate")
public class RecordOfReadingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordOfReadingUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// idがint型でなかったら、Mainへリダイレクトする。
			if (!ValidationUtil.isInteger(request.getParameter("id"))) {
				response.sendRedirect(request.getContextPath() + "/Main");

				return;
			}

			// 指定の読書記録を取得します。
			RecordOfReadingLogic logic = new RecordOfReadingLogic();

			// セッションスコープに保存されたユーザー情報を取得します。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// 指定ID、指定ユーザーIDの読書記録を取得します。
			RecordOfReadingModel model = logic.find(Integer.parseInt(request.getParameter("id")), user.getId());

			if (model == null) {
				// 読書記録を取得できなかったら、Mainにリダイレクトする。
				response.sendRedirect(request.getContextPath() + "/Main");
				return;
			}

			// アップデートページへフォワードします。
			request.setAttribute("recordOfReading", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/recordOfReadingUpdate.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// idがint型でなかったら、無条件でMainページ送りにします。
			if (!ValidationUtil.isInteger(request.getParameter("id"))) {
				response.sendRedirect(request.getContextPath() + "/Main");

				return;
			}

			// リクエストパラメータ
			int id = Integer.parseInt(request.getParameter("id"));
			String startedDate = request.getParameter("startedDate");
			String finishedDate = request.getParameter("finishedDate");
			String bookTitle = request.getParameter("bookTitle");
			String author = request.getParameter("author");
			String dateOfIssue = request.getParameter("dateOfIssue");
			String bookmark = request.getParameter("bookmark");
			String totalPages = request.getParameter("totalPages");
			String purpose = request.getParameter("purpose");
			String memo = request.getParameter("memo");

			// バリデーションチェックを行います。
			RecordOfReadingValidation validate = new RecordOfReadingValidation(request);
			Map<String, String> errors = validate.validate();

			// バリエーションエラーがあった時。
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);

				// JSPのinputタグのvalue値の表示に使うためにリクエストパラメータをMapに保存します。
				Map<String, String> recordOfReading = new HashMap<String, String>();
				recordOfReading.put("startedDate", startedDate);
				recordOfReading.put("finishedDate", finishedDate);
				recordOfReading.put("bookTitle", bookTitle);
				recordOfReading.put("author", author);
				recordOfReading.put("dateOfIssue", dateOfIssue);
				recordOfReading.put("bookmark", bookmark);
				recordOfReading.put("totalPages", totalPages);
				recordOfReading.put("purpose", purpose);
				recordOfReading.put("memo", memo);
				request.setAttribute("recordOfReading", recordOfReading);

				// 登録ページへフォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/recordOfReadingUpdate.jsp");
				dispatcher.forward(request, response);

				return;
			}

			// セッションスコープに保存したユーザー情報を取得します。
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			// リクエストパラメータをRecordOfReadingModelに設定する。
			RecordOfReadingModel recordOfReading = new RecordOfReadingModel();

			recordOfReading.setId(id);
			recordOfReading.setUserId(user.getId());
			recordOfReading.setStartedDate(Date.valueOf(startedDate));

			if (finishedDate == null || finishedDate.length() <= 0) {
				recordOfReading.setFinishedDate(null);
				recordOfReading.setIsFinished(0); // 読了日が無い場合、未読了。
			} else {
				recordOfReading.setFinishedDate(Date.valueOf(finishedDate));
				recordOfReading.setIsFinished(1); // 読了日がある場合、読了済み。
			}

			recordOfReading.setBookTitle(bookTitle);
			recordOfReading.setAuthor(author);
			recordOfReading.setDateOfIssue(dateOfIssue);

			if (bookmark == null || bookmark.length() <= 0) {
				recordOfReading.setBookmark(0);
			} else {
				recordOfReading.setBookmark(Integer.valueOf(bookmark));
			}

			if (totalPages == null || totalPages.length() <= 0) {
				recordOfReading.setTotalPages(0);
			} else {
				recordOfReading.setTotalPages(Integer.valueOf(totalPages));
			}

			recordOfReading.setPurpose(purpose);
			recordOfReading.setMemo(memo);
			recordOfReading.setIsDeleted(0);

			// この本の読書記録を登録する。
			RecordOfReadingLogic logic = new RecordOfReadingLogic();

			if (!logic.update(recordOfReading)) {
				// エラーがあった時は、エラーページへリダイレクトする。
				response.sendRedirect(PageSettings.PAGE_ERROR);

				return;
			}

			// Mainへリダイレクトします。
			response.sendRedirect(request.getContextPath() + "/Main");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}

}
