package kz.enu.epam.azimkhan.tour.command.admin;

import kz.enu.epam.azimkhan.tour.command.AdminCommand;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for managing tours
 */
public class ManagerCommand extends AdminCommand {

    /**
     * Displays a list of tours with the ability to
     * delete, update or create a new one
     *
     * @param request request to read the command from
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		return pathManager.getString("path.page.admin.manager");
	}
}
