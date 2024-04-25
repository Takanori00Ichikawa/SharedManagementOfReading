package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserDAO;
import database.DBConnection;
import model.UserModel;

/**
 * ユーザーロジッククラス
 * @author user
 *
 */
public class UserLogic {

	/**
	 * ユーザーを1件追加します。(ユーザー登録時)
	 * @param model
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int creat(UserModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();

			return dao.creat(conn, model);
		}
	}

	/**
	 * ユーザーを全件取得します。
	 * @return 検索結果 (ユーザーモデルのリスト)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<UserModel> find() throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();

			return dao.findAll(conn);
		}
	}

	/**
	 * 指定E-mailアドレスとパスワードのユーザーを取得します。(ログイン時)
	 * @param email E-mailアドレス
	 * @param password パスワード
	 * @return 検索結果(ユーザーモデル)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserModel find(String email, String password) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection con = db.getInstance();
			UserDAO dao = new UserDAO();

			return dao.findOne(con, email, password);
		}
	}

	/**
	 * 指定ユーザーIDのユーザーを1件検索します。(ユーザー情報の参照や値を更新画面で使用したり、ログイン後に使う)
	 * @param userId ユーザーID
	 * @return 検索結果(ユーザーモデル)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserModel find(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();

			return dao.findOne(conn, userId);
		}
	}

	/** 
	 * 指定ユーザーIDのユーザー情報を1件更新します。
	 * @param model ユーザーモデル
	 * @return 実行結果 1:成功、 0:失敗、その他:エラーコード
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update(UserModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();

			return dao.update(conn, model);
		}
	}
}
