package kz.enu.epam.azimkhan.tour.command.client;

import kz.enu.epam.azimkhan.tour.command.ClientCommand;
import kz.enu.epam.azimkhan.tour.dao.OrderDAO;
import kz.enu.epam.azimkhan.tour.entity.Order;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import kz.enu.epam.azimkhan.tour.logic.authentication.AuthenticationLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * view list of orders
 */
public class AccountCommand extends ClientCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = AuthenticationLogic.user(request);

        try {
            List<Order> orders = OrderDAO.getInstance().findOrdersForUser(user);
            request.setAttribute("orders", orders);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        }

        return pathManager.getString("path.page.client.account");
    }
}
