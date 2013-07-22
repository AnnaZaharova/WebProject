package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.entity.Role;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.AuthenticationLogicalException;
import kz.enu.epam.azimkhan.auth.exception.AuthenticationTechnicalException;
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
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Login command
 */
public class LoginCommand extends ActionCommand{

    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";

    private Logger logger = Logger.getRootLogger();

    @Override
    public boolean checkAccess(User user) {
        return true;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        PathManager pathManager = PathManager.INSTANCE;
        Notification notification = null;

        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
        final String login = request.getParameter(LOGIN_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);

        if (login != null && password != null){

            try {
				User user = AuthenticationLogic.authenticate(login, password);

					HttpSession session = request.getSession();
					session.setAttribute(AuthenticationLogic.SESSION_VAR, user);

                    logger.info("Successful authentication by login: " + login);
                    notification = NotificationCreator.createFromProperty("info.auth.success", locale);

                    if (user.getRole().getRolename().equals(Role.ROLE_ADMIN)){
						return pathManager.getString("path.page.admin.manager");
					} else {
						return pathManager.getString("path.page.tours");
					}


            } catch (AuthenticationTechnicalException e) {
                throw new CommandException(e);
            } catch (AuthenticationLogicalException e) {
                logger.info("Authentication fail by login: " + login);
                notification = NotificationCreator.createFromProperty("error.auth.invalid_login_pass", Notification.Type.ERROR, locale);

            } finally {
                if (notification != null){
                    NotificationService.push(request.getSession(), notification);
                }
            }
        }
        return pathManager.getString("path.page.login");
    }
}
