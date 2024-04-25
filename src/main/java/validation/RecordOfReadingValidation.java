package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;

/**
 * 読書記録の送られてきたパラメーターを検証するクラス
 * @author user
 *
 */
public class RecordOfReadingValidation extends Validation {

	/**
	 * コンストラクタ
	 * @param request リクエストオブジェクト
	 */
	public RecordOfReadingValidation(HttpServletRequest request) {
		super(request);
	}

	/**
	 * バリデーションを行います。
	 * @return バリデーションエラーのMap<項目名, エラーメッセージ>
	 */
	public Map<String, String> validate() {

		//読書開始日のバリデーション (@param startedDate)
		if (!ValidationUtil.isDate(this.request.getParameter("startedDate"))) {
			this.errors.put("startedDate", String.format(MessageSettings.MSG_INVALID_VALUE, "読書開始日"));
		}

		if (!ValidationUtil.isMinLength(this.request.getParameter("startedDate"), 1)) {
			this.errors.put("startedDate", String.format(MessageSettings.MSG_REQUIRED, "読書開始日"));
		}

		// 読了日のバリデーション (@param finishedDate)
		if (!ValidationUtil.isDate(this.request.getParameter("finishedDate"))) {
			this.errors.put("finishedDate", String.format(MessageSettings.MSG_INVALID_VALUE, "読了日"));
		}

		// 本のタイトルのバリデーション (@param bookTitle)
		if (!ValidationUtil.isMaxLength(this.request.getParameter("bookTitle"), 50)) {
			this.errors.put("bookTitle", String.format(MessageSettings.MSG_LENGTH_LONG, "本のタイトル", 50));
		}

		if (!ValidationUtil.isMinLength(this.request.getParameter("bookTitle"), 1)) {
			this.errors.put("bookTitle", String.format(MessageSettings.MSG_REQUIRED, "本のタイトル"));
		}

		// 本の著者のバリデーション (@param author)
		if (!ValidationUtil.isMaxLength(this.request.getParameter("author"), 50)) {
			this.errors.put("author", String.format(MessageSettings.MSG_LENGTH_LONG, "本の著者", 50));
		}

		// 本の発行年月(日)のバリデーション (@param dateOfIssue)
		if (!ValidationUtil.isMaxLength(this.request.getParameter("dateOfIssue"), 50)) {
			this.errors.put("dateOfIssue", String.format(MessageSettings.MSG_LENGTH_LONG, "本の発行年月(日)", 50));
		}

		// 読み終えた箇所までのページ数のバリデーション (@param bookmark)
		if (!ValidationUtil.isIntegerRegister(this.request.getParameter("bookmark"))) {
			this.errors.put("bookmark", String.format(MessageSettings.MSG_INVALID_PAGE, "読み終えた箇所までのページ数"));
		}

		// 本の総ページ数のバリデーション (@param totalPages)
		if (!ValidationUtil.isIntegerRegister(this.request.getParameter("totalPages"))) {
			this.errors.put("totalPages", String.format(MessageSettings.MSG_INVALID_PAGE, "本の総ページ数"));
		}

		// この本を読書する目的のバリデーション (@param purpose)
		if (!ValidationUtil.isMaxLength(this.request.getParameter("purpose"), 50)) {
			this.errors.put("purpose", String.format(MessageSettings.MSG_LENGTH_LONG, "この本を読書する目的", 50));
		}

		// この本の感想・内容等のバリデーション (@param memo)
		if (!ValidationUtil.isMaxLength(this.request.getParameter("memo"), 256)) {
			this.errors.put("memo", String.format(MessageSettings.MSG_LENGTH_LONG, "この本の感想・内容等", 256));
		}

		return errors;
	}

}
