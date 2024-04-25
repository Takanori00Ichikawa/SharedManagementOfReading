package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;

/**
 * ユーザー登録・更新バリデーションクラス
 * @author user
 *
 */
public class UserValidation extends Validation {

	// 
	// @param request
	/**
	 * コンストラクタ
	 * @param request リクエストオブジェクト
	 */
	public UserValidation(HttpServletRequest request) {
		super(request);
	}

	/**
	 * バリデーションを行います。
	 * @return バリデーションエラーのMap<項目名, エラーメッセージ>
	 */
	public Map<String, String> validate() {
		// メールアドレスのバリデーション
		if (!ValidationUtil.isEmail(this.request.getParameter("email"))) {
			this.errors.put("email", MessageSettings.MSG_EMAIL_FAILURE);
		}

		// パスワードのバリデーション
		if (!ValidationUtil.isPassword(this.request.getParameter("password"))) {
			this.errors.put("password", MessageSettings.MSG_PASSWORD_FAILURE);
		}

		// 
		if (!ValidationUtil.isPasswordConf(this.request.getParameter("password"),
				this.request.getParameter("passwordForConfirmation"))) {
			this.errors.put("passwordConf", MessageSettings.MSG_PASSWORD_CONF_FAILURE);
		}

		// ニックネームのバリデーション
		if (!ValidationUtil.isMinLength(this.request.getParameter("name"), 1)) {
			this.errors.put("name", String.format(MessageSettings.MSG_REQUIRED, "ニックネーム"));
		}

		if (!ValidationUtil.isMaxLength(this.request.getParameter("name"), 50)) {
			this.errors.put("name", String.format(MessageSettings.MSG_LENGTH_LONG, "ニックネーム", 50));
		}

		return errors;
	}

}
