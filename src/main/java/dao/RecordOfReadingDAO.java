package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RecordOfReadingModel;
import model.UserModel;

/**
 * Readin記録DAOクラス
 * @author user
 *
 */
public class RecordOfReadingDAO {

	// 基本とたなるSELECT文
	private final String BASE_SQL = "SELECT "
			+ "r.id,"
			+ "r.user_id,"
			+ "r.started_date,"
			+ "r.finished_date,"
			+ "r.book_title,"
			+ "r.author,"
			+ "r.date_of_issue,"
			+ "r.bookmark,"
			+ "r.total_pages,"
			+ "r.purpose,"
			+ "r.memo,"
			+ "r.is_finished,"
			+ "r.is_deleted,"
			+ "r.created_at,"
			+ "r.updated_at,"
			+ "u.email,"
			+ "u.password,"
			+ "u.name,"
			+ "u.is_deleted AS user_is_deleted,"
			+ "u.created_at AS user_created_at,"
			+ "u.updated_at AS user_updated_at "
			+ "FROM records_of_reading r "
			+ "INNER JOIN users u ON r.user_id=u.id ";

	/**
	 * 読書記録を全件取得します。
	 * @param Connection connection データベースコネクションのインスタンス
	 * @return RecoredOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findAll(Connection connection) {
		// レコードを格納するArrayListを生成する。
		List<RecordOfReadingModel> list = new ArrayList<RecordOfReadingModel>();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 AND u.is_deleted=0 "
					+ "ORDER BY r.started_date ASC, r.id DESC";

			// SQLを実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果をArrayListに格納する。
					while (rs.next()) {
						RecordOfReadingModel model = new RecordOfReadingModel();
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						UserModel userModel = new UserModel();
						userModel.setEmail(rs.getString("email"));
						userModel.setPassword(rs.getString("password"));
						userModel.setName(rs.getString("name"));
						userModel.setIsDeleted(rs.getInt("user_is_deleted"));
						userModel.setCreatedAt(rs.getTimestamp("user_created_at"));
						userModel.setUpdatedAt(rs.getTimestamp("user_updated_at"));

						model.setUserModel(userModel);

						// ArrayListにレコードを追加する。
						list.add(model);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return list;
	}

	/**
	 * 読書記録を1件取得します。(idとuserIdからの情報で本1冊分の読書記録を取得します。)
	 * @param connection Connection connection データベースコネクションのインスタンス
	 * @param id 読書記録のid
	 * @param userId
	 * @return RecordOfReadingModel
	 */
	public RecordOfReadingModel findOne(Connection connection, int id, int userId) {
		// RecordOfReadingModelクラスのインスタンスを生成します。
		RecordOfReadingModel model = new RecordOfReadingModel();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 "
					+ "AND u.is_deleted=0 "
					+ "AND r.id=? "
					+ "AND r.user_id=?";

			// SQL文を実行する。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, id);
				stmt.setInt(2, userId);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						UserModel userModel = new UserModel();
						userModel.setEmail(rs.getString("email"));
						userModel.setPassword(rs.getString("password"));
						userModel.setName(rs.getString("name"));
						userModel.setIsDeleted(rs.getInt("user_is_deleted"));
						userModel.setCreatedAt(rs.getTimestamp("user_created_at"));
						userModel.setUpdatedAt(rs.getTimestamp("user_updated_at"));

						model.setUserModel(userModel);
					} else {
						// 実行結果が無い時は、nullを代入する。
						model = null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return model;
	}

	/**
	 * 指定ユーザーIDの読書中の本のReading記録を全件取得します。(メイン画面の読書中の本の一覧で使う)
	 * @param connection Connection connection データベースコネクションのインスタンス
	 * @param userId  ユーザーID
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByUserIdReading(Connection connection, int userId) {
		return findByUserIdReading(connection, userId, Integer.MAX_VALUE, 0);
	}

	/**
	 * 指定ユーザーIDの読書中の本のReading記録を全件取得します。(メイン画面の読書中の本の一覧で使う)
	 * @param connection Connection connection データベースコネクションのインスタンス
	 * @param userId  ユーザーID
	 * @param limit   取得するレコード数(リミット値)
	 * @param offset  取得開始する行数(オフセット値)
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByUserIdReading(Connection connection, int userId, int limit, int offset) {

		// レコードを格納するArrayListを生成する。
		List<RecordOfReadingModel> list = new ArrayList<RecordOfReadingModel>();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=0 "
					+ "AND r.user_id=? "
					+ "ORDER BY started_date ASC, r.id DESC "
					+ "LIMIT ? OFFSET ?";

			// SQLを実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, userId);
				stmt.setInt(2, limit);
				stmt.setInt(3, offset);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// レコードが存在する間、処理を行う。
					while (rs.next()) {
						// RecordOfReadingModelのインスタンスを生成する。
						RecordOfReadingModel model = new RecordOfReadingModel();

						// フィールドに値を設定する。
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						// レコードをArrayListに追加する。
						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return list;
	}

	/**
	 * 指定ユーザーIDの読書中の本のReading記録を全件取得します。(メイン画面の読了本の一覧で使う)
	 * @param connection Connection connection データベースコネクションのインスタンス
	 * @param userId  ユーザーID
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByUserIdFinishedReading(Connection connection, int userId) {
		return findByUserIdFinishedReading(connection, userId, Integer.MAX_VALUE, 0);
	}

	/**
	 * 指定ユーザーIDの読書中の本のReading記録を全件取得します。(メイン画面の読了本の一覧で使う)
	 * @param connection Connection connection データベースコネクションのインスタンス
	 * @param userId userId  ユーザーID
	 * @param limit   取得するレコード数(リミット値)
	 * @param offset  取得開始する行数(オフセット値)
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByUserIdFinishedReading(Connection connection, int userId, int limit,
			int offset) {

		// レコードを格納するArrayListを生成する。
		List<RecordOfReadingModel> list = new ArrayList<RecordOfReadingModel>();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=1 "
					+ "AND r.user_id=? "
					+ "ORDER BY started_date ASC, r.id DESC "
					+ "LIMIT ? OFFSET ?";

			// SQLを実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, userId);
				stmt.setInt(2, limit);
				stmt.setInt(3, offset);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// レコードが存在する間、処理を行う。
					while (rs.next()) {
						// RecordOfReadingModelのインスタンスを生成する。
						RecordOfReadingModel model = new RecordOfReadingModel();

						// フィールドに値を設定する。
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						// レコードをArrayListに追加する。
						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return list;
	}

	/**
	 * 指定ユーザーIDの読書中の本の読書記録をキーワードで検索します。(メイン画面の読書中の本の一覧で使う)
	 * @param Connection connection データベースコネクションのインスタンス
	 * @param userId ユーザーID
	 * @param keyWord ナビゲーションバー内の検索ボックスのキーワード
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByKeyWordReading(Connection connection, int userId, String keyWord) {
		// レコードを格納するArrayListを生成する。
		List<RecordOfReadingModel> list = new ArrayList<RecordOfReadingModel>();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=0 "
					+ "AND r.user_id=? "
					+ "AND (r.book_title LIKE ? OR r.author LIKE ?) "
					+ "ORDER BY started_date ASC, r.id DESC";

			// SQL文を実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, userId);
				stmt.setString(2, "%" + keyWord + "%");
				stmt.setString(3, "%" + keyWord + "%");

				// SQL文を実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果をArrayListに格納する
					while (rs.next()) {
						RecordOfReadingModel model = new RecordOfReadingModel();
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						// レコードをArrayListに追加する。
						list.add(model);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return list;
	}

	/**
	 * 指定ユーザーIDの読了本の本の読書記録をキーワードで検索します。(メイン画面の読書中の本の一覧で使う)
	 * @param connection  データベースコネクションのインスタンス connection
	 * @param userId　ユーザーID
	 * @param keyWord ナビゲーションバー内の検索ボックスのキーワード
	 * @return RecordOfReadingModelのArrayList
	 */
	public List<RecordOfReadingModel> findByKeyWordFinishedReading(Connection connection, int userId, String keyWord) {
		// レコードを格納するArrayListを生成する。
		List<RecordOfReadingModel> list = new ArrayList<RecordOfReadingModel>();

		try {
			// SQL文を設定する。
			String sql = BASE_SQL
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=1 "
					+ "AND r.user_id=? "
					+ "AND (r.book_title LIKE ? OR r.author LIKE ?) "
					+ "ORDER BY started_date ASC, r.id DESC";

			// SQL文を実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, userId);
				stmt.setString(2, "%" + keyWord + "%");
				stmt.setString(3, "%" + keyWord + "%");

				// SQL文を実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果をArrayListに格納する
					while (rs.next()) {
						RecordOfReadingModel model = new RecordOfReadingModel();
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setStartedDate(rs.getDate("started_date"));
						model.setFinishedDate(rs.getDate("finished_date"));
						model.setBookTitle(rs.getString("book_title"));
						model.setAuthor(rs.getString("author"));
						model.setDateOfIssue(rs.getString("date_of_issue"));
						model.setBookmark(rs.getInt("bookmark"));
						model.setTotalPages(rs.getInt("total_pages"));
						model.setPurpose(rs.getString("purpose"));
						model.setMemo(rs.getString("memo"));
						model.setIsFinished(rs.getInt("is_finished"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						// レコードをArrayListに追加する。
						list.add(model);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return list;
	}

	/**
	 *  読書記録を1件追加します。	
	 * @param connection  データベースコネクションのインスタンス connection
	 * @param  model   RecordOfReadingModel model
	 * @return 結果 (true:成功、 false:失敗)
	 */
	public boolean create(Connection connection, RecordOfReadingModel model) {
		try {
			// SQL文を設定する。
			String sql = "INSERT INTO records_of_reading ("
					+ "user_id,"
					+ "started_date,"
					+ "finished_date,"
					+ "book_title,"
					+ "author,"
					+ "date_of_issue,"
					+ "bookmark,"
					+ "total_pages,"
					+ "purpose,"
					+ "memo,"
					+ "is_finished,"
					+ "is_deleted"
					+ ") VALUES ("
					+ "?," // user_id
					+ "?," // started_date
					+ "?," // finished_date
					+ "?," // book_title
					+ "?," // author
					+ "?," // date_of_issue
					+ "?," // bookmark
					+ "?," // total_pages
					+ "?," // purpose
					+ "?," // memo
					+ "?," // is_finished
					+ "?" // is_deleted
					+ ")";

			// SQLを実行する準備をする。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, model.getUserId());
				stmt.setDate(2, model.getStartedDate());
				stmt.setDate(3, model.getFinishedDate());
				stmt.setString(4, model.getBookTitle());
				stmt.setString(5, model.getAuthor());
				stmt.setString(6, model.getDateOfIssue());
				stmt.setInt(7, model.getBookmark());
				stmt.setInt(8, model.getTotalPages());
				stmt.setString(9, model.getPurpose());
				stmt.setString(10, model.getMemo());
				stmt.setInt(11, model.getIsFinished());
				stmt.setInt(12, model.getIsDeleted());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 *  読書記録を1件更新します。
	 * @param connection Connectionのインスタンス
	 * @param model  RecordOfReadingModel
	 * @return 結果(true:成功、 false:失敗)
	 */
	public boolean update(Connection connection, RecordOfReadingModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE records_of_reading SET "
					+ "user_id=?," // 1
					+ "started_date=?," // 2
					+ "finished_date=?," // 3
					+ "book_title=?," // 4
					+ "author=?," // 5
					+ "date_of_issue=?," // 6
					+ "bookmark=?," // 7
					+ "total_pages=?," // 8
					+ "purpose=?," // 9
					+ "memo=?," // 10
					+ "is_finished=?," // 11
					+ "is_deleted=? " // 12
					+ "WHERE id=?"; // 13

			// SQL文を実行する。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, model.getUserId());
				stmt.setDate(2, model.getStartedDate());
				stmt.setDate(3, model.getFinishedDate());
				stmt.setString(4, model.getBookTitle());
				stmt.setString(5, model.getAuthor());
				stmt.setString(6, model.getDateOfIssue());
				stmt.setInt(7, model.getBookmark());
				stmt.setInt(8, model.getTotalPages());
				stmt.setString(9, model.getPurpose());
				stmt.setString(10, model.getMemo());
				stmt.setInt(11, model.getIsFinished());
				stmt.setInt(12, model.getIsDeleted());
				stmt.setInt(13, model.getId());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 読書記録を1件読了に更新します。
	 * @param connection Connectionのインスタンス
	 * @param model RecordOfReadingModel
	 * @return RecordOfReadingModel
	 */
	public boolean updateIsFinished(Connection connection, RecordOfReadingModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE records_of_reading SET "
					+ "finished_date=?," // 1
					+ "is_finished=? " // 2
					+ "WHERE id=?"; // 3

			// SQL文を実行する。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setDate(1, model.getFinishedDate());
				stmt.setInt(2, model.getIsFinished());
				stmt.setInt(3, model.getId());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 読書記録を1件削除に更新します。
	 * @param connection データベースコネクションのインスタンス
	 * @param model RecordOfReadingModel
	 * @return 結果(true:成功、 false:失敗)
	 */
	public boolean updateIsDeleted(Connection connection, RecordOfReadingModel model) {
		try {
			// SQL文を設定する。
			String sql = "UPDATE records_of_reading SET "
					+ "is_deleted=? " // 1
					+ "WHERE id=?"; // 2

			// SQL文を実行する。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setInt(1, model.getIsDeleted());
				stmt.setInt(2, model.getId());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 指定ユーザーIDの読書中の本の冊数を取得します。
	 * @param connection データベースコネクションのインスタンス
	 * @param userId ユーザーID
	 * @return 読書中の本の冊数
	 */
	public int countReadingByUserId(Connection connection, int userId) {
		try {
			// SQL文を設定する。
			String sql = "SELECT COUNT(r.id) AS cnt "
					+ "FROM records_of_reading r "
					+ "INNER JOIN users u ON r.user_id=u.id "
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=0 "
					+ "AND r.user_id=?";

			// SQLを実行する準備をします。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメーターを設定します。
				stmt.setInt(1, userId);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("cnt");
					} else {
						return 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 指定ユーザーIDの読了した本の冊数を取得します。
	 * @param connection データベースコネクションのインスタンス
	 * @param userId connection データベースコネクションのインスタンス
	 * @return 読了した本の冊数
	 */
	public int countIsFinishedByUserId(Connection connection, int userId) {
		try {
			// SQL文を設定する。
			String sql = "SELECT COUNT(r.id) AS cnt "
					+ "FROM records_of_reading r "
					+ "INNER JOIN users u ON r.user_id=u.id "
					+ "WHERE r.is_deleted=0 "
					+ "AND r.is_finished=1 "
					+ "AND r.user_id=?";

			// SQLを実行する準備をします。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメーターを設定します。
				stmt.setInt(1, userId);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("cnt");
					} else {
						return 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 指定ユーザーIDの登録した本の冊数を取得します。
	 * @param connection データベースコネクションのインスタンス
	 * @param userId ユーザーID
	 * @return 読了した本の冊数
	 */
	public int countTotalByUserId(Connection connection, int userId) {
		try {
			// SQL文を設定する。
			String sql = "SELECT COUNT(r.id) AS cnt "
					+ "FROM records_of_reading r "
					+ "INNER JOIN users u ON r.user_id=u.id "
					+ "WHERE r.is_deleted=0 "
					+ "AND r.user_id=?";

			// SQLを実行する準備をします。
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				// パラメーターを設定します。
				stmt.setInt(1, userId);

				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("cnt");
					} else {
						return 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		}
	}
}
