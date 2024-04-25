package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.RecordOfReadingDAO;
import database.DBConnection;
import model.RecordOfReadingModel;

/**
 * 読書記録のロジッククラス
 * @author user
 *
 */
public class RecordOfReadingLogic {

	/**
	 * 読書記録を全件取得します。
	 * @return 結果 (true:成功 false:失敗)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<RecordOfReadingModel> find() throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findAll(conn);
		}
	}

	/**
	 * 指定ユーザーIDの読書記録の読書中の本の記録を全件取得します。(メイン画面の読書中の本の一覧で使用)
	 * @param userId ユーザーID
	 * @return RecordOfReadingModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<RecordOfReadingModel> findReading(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findByUserIdReading(conn, userId);
		}
	}

	/**
	 * 指定ユーザーIDの読書記録の読書中の本の記録を全件取得します。(メイン画面の読了本の一覧で使用)
	 * @param userId ユーザーID
	 * @return RecordOfReadingModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<RecordOfReadingModel> findFinishedReading(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findByUserIdFinishedReading(conn, userId);
		}
	}

	/**
	 * 指定ユーザーIDの読書記録の読書中の本の記録をキーワードで検索します。(メイン画面の読書中の本の一覧で使用)
	 * @param userId ユーザーID
	 * @param keyWord 検索キーワード
	 * @return RecordOfReadingModelのArrayList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<RecordOfReadingModel> findReading(int userId, String keyWord)
			throws SQLException, ClassNotFoundException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findByKeyWordReading(conn, userId, keyWord);
		}
	}

	/**
	 * 指定ユーザーIDの読書記録の読了本の記録をキーワードで検索します。(メイン画面の読書中の本の一覧で使用)
	 * @param userId ユーザーID
	 * @param keyWord 検索キーワード
	 * @return RecordOfReadingModelのArrayList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<RecordOfReadingModel> findFinishedReading(int userId, String keyWord)
			throws SQLException, ClassNotFoundException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findByKeyWordFinishedReading(conn, userId, keyWord);
		}
	}

	/**
	 * 指定ID、指定ユーザーIDの読書記録を1件取得します。
	 * @param id 読書記録ID
	 * @param userID ユーザーID
	 * @return RecordOfReadingModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public RecordOfReadingModel find(int id, int userID) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.findOne(conn, id, userID);
		}
	}

	/**
	 * 読書記録を1件追加します。
	 * @param model RecordOfReadingModel
	 * @return 結果(ture:成功、 false:失敗)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean create(RecordOfReadingModel model) throws SQLException, ClassNotFoundException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.create(conn, model);
		}
	}

	/**
	 * 読書記録を1件更新します。
	 * @param model RecordOfReadingModel
	 * @return 結果(true:成功、 false:失敗)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean update(RecordOfReadingModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.update(conn, model);
		}
	}

	/**
	 * 読書記録を1件読了に更新します。
	 * @param model RecordOfReadingModel
	 * @return RecordOfReadingModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateIsFinished(RecordOfReadingModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.updateIsFinished(conn, model);
		}
	}

	/**
	 * 読書記録を1件削除に更新します。
	 * @param modelRecordOfReadingModel
	 * @return 結果(true:成功、 false:失敗)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateIsDeleted(RecordOfReadingModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.updateIsDeleted(conn, model);
		}
	}

	/**
	 * 指定ユーザーIDの読書中の本の冊数を取得します。
	 * @param userId ユーザーID
	 * @return 読書中の本の冊数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int countReading(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.countReadingByUserId(conn, userId);
		}
	}

	/**
	 * 指定ユーザーIDの読了した本の冊数を取得します。
	 * @param userId ユーザーID
	 * @return 読了した本の冊数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int countIsFinished(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.countIsFinishedByUserId(conn, userId);
		}
	}

	/**
	 * 指定ユーザーIDの登録した本の冊数を取得します。
	 * @param userId ユーザーID
	 * @return 登録した本の冊数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int countTotal(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			RecordOfReadingDAO dao = new RecordOfReadingDAO();

			return dao.countTotalByUserId(conn, userId);
		}
	}

}
