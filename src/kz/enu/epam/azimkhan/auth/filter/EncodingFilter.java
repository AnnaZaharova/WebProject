package kz.enu.epam.azimkhan.auth.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter
 */
public class EncodingFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		String encoding = req.getCharacterEncoding();
		if (!"UTF-8".equalsIgnoreCase(encoding)){
			resp.setCharacterEncoding("UTF-8");
		}
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig config) throws ServletException {

	}

}
