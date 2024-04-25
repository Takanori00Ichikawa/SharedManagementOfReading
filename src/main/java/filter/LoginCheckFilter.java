package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import settings.PageSettings;

/**ログインチェックを行うフィルター
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "LoginCheckFilter") // フィルターを実行するURLは/WEB-INF/web.xmlで指定します。
public class LoginCheckFilter extends HttpFilter implements Filter {

	/**
	 * ログインチェックを行う
	 * セッションにユーザー情報が登録されているかを確認して、登録されていなければログイン画面にリダイレクトします。
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ServletRequestクラスオブジェクトではgetSession()メソッド、getContextPath()メソッドが使えないので、
		// HttpServletRequestクラスオブジェクトにキャストする。
		HttpServletRequest req = (HttpServletRequest) request;

		// ServletResponseクラスオブジェクトではsendRedirect()メソッドが使えないので、
		// HttpServletResponseクラスオブジェクトにキャストする。
		HttpServletResponse res = (HttpServletResponse) response;

		// セッションスコープを取得します。無ければ作成します。
		HttpSession session = req.getSession(true);

		// セッションスコープに保存されているユーザー情報が無い時
		if (session.getAttribute("user") == null) {
			// ログインページにリダイレクトします。
			res.sendRedirect(req.getContextPath() + PageSettings.LOGIN_SERVLET);

			// ここでreturnしないと「レスポンスをコミットした後でフォワード出来ません」と例外が発生します。
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
