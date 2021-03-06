package kz.enu.epam.azimkhan.tour.command;

import kz.enu.epam.azimkhan.tour.command.ActionCommand;
import kz.enu.epam.azimkhan.tour.dao.TourDAO;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import kz.enu.epam.azimkhan.tour.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.tour.logic.client.ClientLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class ViewToursCommand extends ActionCommand{
	@Override
	public boolean checkAccess(User user) {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		TourDAO dao = TourDAO.getInstance();
		try {
			List<Tour> tours = dao.findAll();
            User client = AuthenticationLogic.user(request);
            boolean regular = ClientLogic.isRegularClient(client);
            request.setAttribute("regular", regular);
			request.setAttribute("tours", tours);
		} catch (DAOLogicalException e) {
			throw new CommandException(e);
		} catch (DAOTechnicalException e) {
			throw new CommandException(e);
		}

		return pathManager.getString("path.page.tours");
	}
}
