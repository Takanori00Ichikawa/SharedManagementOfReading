package settings;

/**
 * セキュリティ対策設定クラス
 * @author user
 *
 */
public class SecuritySettings {

	/**
	 * パスワードの妥当性チェックのための正規表現（英大文字・英小文字・数字を全種取り混ぜて8～20文字で設定）
	 */
	public static final String PASSWORD_REGEXP_STRING = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-z0-9A-Z]{8,20}$";

}
