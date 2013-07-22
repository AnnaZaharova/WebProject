package kz.enu.epam.azimkhan.auth.command;

import com.sun.deploy.net.HttpRequest;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract command
 */
public abstract class ActionCommand {

    /**
     * Check the access of user, return true if the user has access to
     * this command, otherwise return false
     * @param user can be null
     * @return
     */
    public abstract boolean checkAccess(User user);

    /**
     * This method reads a command from the request
     * and processes it. The result will be given as
     * a page to forward to
     *
     * @param request request to read the command from
     * @param response
     * @return forward page
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
