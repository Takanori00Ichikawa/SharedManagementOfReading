package validation;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;

import settings.SecuritySettings;

/**
 * 送られてくるパラメーターを検証するクラス
 * @author user
 *
 */
public class ValidationUtil {

	/**
	 * 文字列が指定最大文字数以下かどうか調べます。
	 * @param str 文字列
	 * @param length 文字数
	 * @return true:指定文字数以下 false:指定文字数を超えている
	 */
	public static final boolean isMaxLength(String str, int length) {
		return str.length() <= length;
	}

	/**
	 * 文字列が指定最小文字数以上かどうか調べます。
	 * @param str 文字列
	 * @param length 文字数
	 * @return true:指定文字数以上 false:指定文字数より短い
	 */
	public static final boolean isMinLength(String str, int length) {
		return str.length() >= length;
	}

	/**
	 * 文字列がEmail形式として正しいか調べます。
	 * @param email 文字列
	 * @return true:正しい false:正しくない
	 * @see https://blog.mailtrap.io./java-email-validation/#Simple_Email_Validation_in_Java
	 * @see https://commons.apache.org/proper/commons-validator/download_validator.cgi
	 */
	public static final boolean isEmail(String email) {
		// create the EmailValidator instance
		EmailValidator validator = EmailValidator.getInstance();
		//check for valid email addresses using isValid method
		return validator.isValid(email);
	}

	/**
	 * 文字列がパスワード（半角英数大文字小文字数字を全種取り混ぜて8文字以上20文字以下）として正しいかどうか調べます。
	 * @param password 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isPassword(String password) {
		RegexValidator regex = new RegexValidator(SecuritySettings.PASSWORD_REGEXP_STRING);
		return regex.isValid(password);
	}

	/**
	 * パスワードと確認用パスワードが同一かどうか調べます。
	 * @param password 文字列
	 * @param passwordForConfirmation 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isPasswordConf(String password, String passwordForConfirmation) {
		return password.equals(passwordForConfirmation);
	}

	/**
	 * 文字列が日付形式(yyyy-MM-dd)かどうか調べます。
	 * @param value 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isDate(String value) {
		if (value == null || value.length() <= 0) { // 値が無くても大丈夫にします
			return true;
		} else {
			DateValidator date = DateValidator.getInstance();
			return date.isValid(value, "yyyy-MM-dd");
		}
	}

	/**
	 * 文字列がint型かどうか調べます。
	 * @param value 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isInteger(String value) {
		IntegerValidator integer = IntegerValidator.getInstance();
		return integer.isValid(value);
	}

	/**
	 * 文字列がint型かどうか調べます。
	 * @param value 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isIntegerRegister(String value) {
		if (value == null || value.length() <= 0) { // 値が無くても大丈夫にします
			return true;
		} else {
			IntegerValidator integer = IntegerValidator.getInstance();
			return integer.isValid(value);
		}
	}

	/**
	 * 文字列がfloat型かどうか調べます。
	 * @param value 文字列
	 * @return true:正しい false:正しくない
	 */
	public static final boolean isFloat(String value) {
		FloatValidator object = FloatValidator.getInstance();
		return object.isValid(value);
	}
}
