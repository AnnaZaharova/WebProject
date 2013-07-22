package kz.enu.epam.azimkhan.auth.filter;

import kz.enu.epam.azimkhan.auth.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class JspProtectionFilter implements Filter{

    private PathManager pathManager = PathManager.INSTANCE;
    private Logger logger = Logger.getRootLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setStatus(403);
        logger.error("Someone tried to access jsp file directly");
    }

    @Override
    public void destroy() {
    }
}
