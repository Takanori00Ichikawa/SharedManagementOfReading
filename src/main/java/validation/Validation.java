package validation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * バリデーション基底クラス
 * @author user
 *
 */
public abstract class Validation {

	/**
	 * リクエストオブジェクト
	 */
	protected HttpServletRequest request;

	/**
	 * エラーが発生した項目名とエラーの内容を格納するMap
	 */
	protected Map<String, String> errors;

	/**
	 * コンストラクタ
	 * @param request リクエスト
	 */
	public Validation(HttpServletRequest request) {
		this.request = request;
		this.errors = new HashMap<String, String>();
	}

	/**
	 * バリデーションの有無を判定します。
	 * @return true:エラーがある、false:エラーはない
	 */
	public boolean hasErrors() {
		if (this.errors.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * バリデーションを行います。（抽象メソッド）
	 * 実装は派生先のクラスで行います。
	 * @return バリデーションエラーのMap<項目名, エラーメッセージ>
	 */
	public abstract Map<String, String> validate();

}
