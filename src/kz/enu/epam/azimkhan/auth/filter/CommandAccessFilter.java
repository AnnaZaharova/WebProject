package kz.enu.epam.azimkhan.auth.filter;

import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.helper.RequestHelper;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class CommandAccessFilter implements Filter {

    private PathManager pathManager = PathManager.INSTANCE;
    private Logger logger = Logger.getRootLogger();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        RequestHelper requestHelper = RequestHelper.INSTANCE;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        ActionCommand command = requestHelper.getCommand(request);

        User user = AuthenticationLogic.user(request);

        if (command.checkAccess(user)){

            chain.doFilter(req, resp);
        } else{
            response.setStatus(403);
            logger.error(String.format("Access denied for %s to the following command: %s", (user != null) ? user : "anonymous user", command));
            request.getRequestDispatcher(pathManager.getString("path.error403")).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
