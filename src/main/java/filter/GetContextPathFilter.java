package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class GetContextPathFilter
 */
@WebFilter("/*")
public class GetContextPathFilter implements Filter {
       
    /**
     * コンテキストのパスを取得
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// コンテキストのパスを取得し、JSPで${ root_path }でアクセス出来るようにする。
		request.setAttribute("root_path", ((HttpServletRequest) request).getContextPath());

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
