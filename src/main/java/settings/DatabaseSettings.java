package settings;

/**
 * データベース関連の定数を設定したクラス
 * @author user
 *
 */
public class DatabaseSettings {

	// データベース関連

	/**
	 * JDBCドライバ名
	 */
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * JDBC接続文字列(文字コードをutf8mb4_general_ciに設定)
	 */
	public static final String JDBC_URL = "jdbc:mysql://localhost/jsp_shared_management_of_reading?connectionCollation=utf8mb4_general_ci";

	/**
	 * データベース接続ユーザー名
	 */
	public static final String DB_USER = "root";

	/**
	 * データベース接続パスワード
	 */
	public static final String DB_PASS = "";

	/**
	 * データベース操作成功
	 */
	public static final int DB_EXECUTION_SUCCESS = 1;

	/**
	 * データベース操作失敗
	 */
	public static final int DB_EXECUTION_FAILURE = 2;

	/**
	 * ユニークKEYの重複
	 */
	public static final int DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME = 1062;
}
