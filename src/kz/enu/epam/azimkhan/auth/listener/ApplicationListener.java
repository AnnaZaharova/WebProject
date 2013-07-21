package kz.enu.epam.azimkhan.auth.listener; /**
 *
 */

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.exception.ConnectionPoolException;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.resource.LocaleManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener{

    private Logger logger = Logger.getRootLogger();
    private LocaleManager localeManager = LocaleManager.INSTANCE;


    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        context.setAttribute("locales", LocaleManager.INSTANCE.getLocales());

        Locale.setDefault(Locale.ENGLISH);

    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.shutDown();
        } catch (ConnectionPoolException e) {
            logger.error(e.getMessage());
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {

    }



    public void sessionDestroyed(HttpSessionEvent se) {

    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest();
        Locale locale = localeManager.resolveLocale(request);
        Logger.getRootLogger().info(locale.getDisplayLanguage());
        request.setAttribute("locale", locale);
    }
}
