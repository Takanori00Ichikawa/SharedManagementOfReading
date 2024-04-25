package settings;

/**
 * ページ表示に関する設定クラス
 * @author user
 *
 */
public class PageSettings {
	
	/**
	 * エラー発生時のフォワード先のJSPファイル
	 */
	public final static String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";
	
	/**
	 * ログイン画面のJSPファイル
	 */
	public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";		
			
	/**
	 * ログイン画面のサーブレット
	 */
	public static final String LOGIN_SERVLET = "/Login";
}
