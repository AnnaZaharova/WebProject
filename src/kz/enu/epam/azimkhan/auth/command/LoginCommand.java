package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.exception.AuthenticationException;
import kz.enu.epam.azimkhan.auth.exception.CommandException;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.notification.creator.NotificationCreator;
import kz.enu.epam.azimkhan.auth.notification.entity.Notification;
import kz.enu.epam.azimkhan.auth.notification.service.NotificationService;
import kz.enu.epam.azimkhan.auth.resource.LocaleManager;
import kz.enu.epam.azimkhan.auth.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Login command
 */
public class LoginCommand extends ActionCommand{

    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    private Logger logger = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        final String login = request.getParameter(LOGIN_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);
        final NotificationService notificationService = new NotificationService(request);

        PathManager pathManager = PathManager.INSTANCE;
        Notification notification = null;

        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);

        if (request.getMethod().equalsIgnoreCase("post")){
            try {
                if (AuthenticationLogic.authenticate(request, login, password)){
                    logger.info("Successful authentication by login: " + login);
                    notification = NotificationCreator.createFromProperty("info.auth.success", locale);

                    return pathManager.getString("path.page.main");
                }else{
                    logger.info("Authentication fail by login: " + login);
                    notification = NotificationCreator.createFromProperty("error.auth.invalid_login_pass", Notification.Type.ERROR, locale);
                }

            } catch (AuthenticationException e) {
                throw new CommandException(e);
            } finally {
                if (notification != null){
                    notificationService.push(notification);
                }
            }
        }

        return pathManager.getString("path.page.login");
    }
}
