package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.CommandException;
import kz.enu.epam.azimkhan.auth.resource.PathManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command is used if empty or wrong command is specified
 */
public class EmptyCommand extends ActionCommand{

    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    /**
     * Return to the error page
     * @param request request to read the command from
     * @param response response
     * @return path
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return PathManager.INSTANCE.getString("path.page.main");
    }
}
