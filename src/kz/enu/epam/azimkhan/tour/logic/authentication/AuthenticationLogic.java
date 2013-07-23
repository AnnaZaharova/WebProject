package kz.enu.epam.azimkhan.tour.logic.authentication;

import kz.enu.epam.azimkhan.tour.dao.UserDAO;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.AuthenticationLogicalException;
import kz.enu.epam.azimkhan.tour.exception.AuthenticationTechnicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Performs authentication
 */
public class AuthenticationLogic {
    public static final String SESSION_VAR = "_user";

    /**
     * Authenticate user
     * @param login
     * @param password
     * @throws kz.enu.epam.azimkhan.tour.exception.AuthenticationTechnicalException
     */
    public static User authenticate(String login, String password) throws AuthenticationTechnicalException, AuthenticationLogicalException {
        if (login != null && password != null){
            String hash = DigestUtils.md5Hex(password);
            UserDAO dao = new UserDAO();

            try {
                User user = dao.findByLoginAndPassword(login, hash);
				return user;

            } catch (DAOLogicalException e) {
                throw new AuthenticationLogicalException(e);
            } catch (DAOTechnicalException e) {
                throw new AuthenticationTechnicalException(e);
            }
        }
        return null;
    }

    /**
     * check if user is logged in to the system
     * @param request
     * @return
     */
    public static boolean isLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return  (session.getAttribute(SESSION_VAR) != null);
    }

    /**
     * perform logout
     * @param request
     */
    public static void logout(HttpSession session){
        session.invalidate();
    }

    /**
     *
     * @param request
     * @return
     */
    public static User user(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object ob = session.getAttribute(SESSION_VAR);
        return (ob != null && ob.getClass().equals(User.class)) ? (User) ob : null;
    }
}
