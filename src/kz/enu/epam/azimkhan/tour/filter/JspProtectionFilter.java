package kz.enu.epam.azimkhan.tour.filter;

import kz.enu.epam.azimkhan.tour.resource.PathManager;
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
        response.setStatus(404);
        logger.error("Someone tried to access jsp file directly");
        request.getRequestDispatcher(pathManager.getString("path.error404")).forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
