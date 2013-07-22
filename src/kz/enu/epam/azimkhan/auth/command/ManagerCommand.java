package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.exception.CommandException;
import kz.enu.epam.azimkhan.auth.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tour
 */
public class ManagerCommand extends ActionCommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		PathManager pathManager = PathManager.INSTANCE;
		return pathManager.getString("path.page.admin.manager");
	}
}
