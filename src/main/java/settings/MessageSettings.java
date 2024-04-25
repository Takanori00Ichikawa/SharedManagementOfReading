package settings;

/**
 * 出力するメッセージの定数を設定したクラス
 * @author user
 *
 */
public class MessageSettings {

	/**
	 * %sはすでに登録されているので使用できません。
	 */
	public static final String MSG_ER_DUP_KEYNAME = "%sはすでに登録されているので使用できません。";

	/**
	 * 申し訳ございません。エラーが発生しました。
	 */
	public static final String MSG_ERROR_OCCURRED = "申し訳ございません。エラーが発生しました。";

	/**
	 * 不正な処理が行われました。
	 */
	public static final String MSG_INVALID_PROCESS = "不正な処理が行われました。";

	/**
	 * E-mailアドレス、または、パスワードが間違っています。
	 */
	public static final String MSG_LOGIN_FAILURE = "E-mailアドレス、または、パスワードが間違っています。";

	/**
	 * 正しいE-mailアドレスを入力してください。
	 */
	public static final String MSG_EMAIL_FAILURE = "正しいE-mailアドレスを入力してください。";

	/**
	 * パスワードは、半角英数大文字小文字数字を全種取り混ぜて8文字以上20文字以下で入力してください。
	 */
	public static final String MSG_PASSWORD_FAILURE = "パスワードは、半角英数大文字小文字数字を全種取り混ぜて8文字以上20文字以下で入力してください。";

	/**
	 * パスワードと確認用パスワードが同一ではありません。
	 */
	public static final String MSG_PASSWORD_CONF_FAILURE = "パスワードと確認用パスワードが同一ではありません。";

	/**
	 * %sは%d文字以上で入力してください。
	 */
	public static final String MSG_LENGTH_SHORT = " %sは%d文字以上で入力してください。";

	/**
	 * %sは%d文字以下で入力してください。
	 */
	public static final String MSG_LENGTH_LONG = " %sは%d文字以下で入力してください。";

	/**
	 * %sは必ず入力してください。
	 */
	public static final String MSG_REQUIRED = "%sは必ず入力してください。";

	//
	/**
	 * %sの形式が正しくありません。
	 */
	public static final String MSG_INVALID_FORMAT = "%sの形式が正しくありません。";

	/**
	 * %sが正しくありません。
	 */
	public static final String MSG_INVALID_VALUE = "%sが正しくありません。";

	/**
	 * %sは半角数字で入力してください。
	 */
	public static final String MSG_INVALID_PAGE = "%sは半角数字で入力してください。";

	/**
	 * アカウント情報を更新しました。
	 */
	public static final String MSG_USER_UPDATE_SUCCESS = "アカウント情報を更新しました。";

	/**
	 * アカウント登録ありがとうございました。
	 */
	public static final String MSG_USER_REGISTER_SUCCESS = "アカウント登録ありがとうございました。";
}
