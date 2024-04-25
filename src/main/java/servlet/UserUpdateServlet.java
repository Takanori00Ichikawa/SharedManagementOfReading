package servlet;

import java.io.IOException;
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

import logic.UserLogic;
import model.UserModel;
import settings.DatabaseSettings;
import settings.MessageSettings;
import settings.PageSettings;
import validation.UserValidation;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ユーザー更新画面へフォワードします。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータ 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordForConfirmation = request.getParameter("passwordForConfirmation");
		String name = request.getParameter("name");

		try {
			// バリデーションチェックを行います。
			UserValidation validate = new UserValidation(request);
			Map<String, String> errors = validate.validate();

			// バリエーションエラーがあった時
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);
				// JSPのinputタグのvalue値の表示に使う為に使うリクエストパラメータをMapに保存する。
				Map<String, String> user = new HashMap<String, String>();
				user.put("email", email);
				user.put("password", password);
				user.put("passwordForConfirmation", passwordForConfirmation);
				user.put("name", name);
				request.setAttribute("user", user);

				// ユーザー更新画面へフォワードする。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// ユーザーIDをセッションから取得します。
			HttpSession session = request.getSession();
			UserModel userModel = (UserModel) session.getAttribute("user");
			int id = userModel.getId();

			// リクエストパラメータをユーザーモデルに設定する。
			UserModel user = new UserModel();
			user.setId(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);

			// ユーザー情報を更新します。
			UserLogic logic = new UserLogic();
			int ret = logic.update(user);

			// 実行結果により処理を切り替えます。
			switch (ret) {
			case DatabaseSettings.DB_EXECUTION_SUCCESS:
				// データベース操作成功の時、ログイン状態を保持しつつ、メイン画面へリダイレクトして終了します。
				session.removeAttribute("user");
				session.setAttribute("user", user);
				request.setAttribute("passwordForConfirmation", user.getPassword());
				request.setAttribute("msgUserUpdateSuccece", MessageSettings.MSG_USER_UPDATE_SUCCESS);
				response.sendRedirect(request.getContextPath() + "/Main");
				return;

			case DatabaseSettings.DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME:
				// ユニークKEYが重複(メールアドレスが重複)している時、エラーメッセージをリクエストスコープに保存します。
				request.setAttribute("db_error", String.format(MessageSettings.MSG_ER_DUP_KEYNAME, user.getEmail()));
				break;

			default:
				// その他のエラーの時、エラーメッセージをリクエストスコープに保存します。
				request.setAttribute("db_error", MessageSettings.MSG_ERROR_OCCURRED);
				break;
			}

			// リクエストスコープにユーザーモデルを保存します。
			request.setAttribute("user", user);

			// ユーザー更新画面へフォワードします。
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードします。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);
		}
	}

}
