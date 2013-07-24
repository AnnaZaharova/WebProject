package kz.enu.epam.azimkhan.tour.command.admin;

import kz.enu.epam.azimkhan.tour.command.AdminCommand;
import kz.enu.epam.azimkhan.tour.dao.TourDAO;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import kz.enu.epam.azimkhan.tour.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.tour.logic.client.ClientLogic;
import kz.enu.epam.azimkhan.tour.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        TourDAO dao = TourDAO.getInstance();
        try {
            List<Tour> tours = dao.findAll();
            request.setAttribute("tours", tours);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        }
		return pathManager.getString("path.page.admin.manager");
	}
}
