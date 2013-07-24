package kz.enu.epam.azimkhan.tour.command.client;

import kz.enu.epam.azimkhan.tour.command.ClientCommand;
import kz.enu.epam.azimkhan.tour.dao.OrderDAO;
import kz.enu.epam.azimkhan.tour.dao.TourDAO;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import kz.enu.epam.azimkhan.tour.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.tour.logic.client.ClientLogic;
import kz.enu.epam.azimkhan.tour.logic.order.OrderLogic;
import kz.enu.epam.azimkhan.tour.notification.creator.NotificationCreator;
import kz.enu.epam.azimkhan.tour.notification.entity.Notification;
import kz.enu.epam.azimkhan.tour.notification.service.NotificationService;
import kz.enu.epam.azimkhan.tour.resource.LocaleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Perform a tour order
 */
public class OrderCommand extends ClientCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = AuthenticationLogic.user(request);
        Notification notification = null;

        int id;
        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
        try {
            id = Integer.parseInt(request.getParameter("id"));

        } catch (NumberFormatException e){
            notification = NotificationCreator.createFromProperty("error.invalid_parameter", Notification.Type.ERROR, locale);
            return pathManager.getString("path.page.admin.manager");
        }
        try{
            TourDAO tourDAO = TourDAO.getInstance();
            Tour tour = tourDAO.findById(id);
            boolean regular = ClientLogic.isRegularClient(user);

            double amount = regular ? (double) (tour.getPrice() - (tour.getPrice() * tour.getRegularDiscount() * 0.01)) : tour.getPrice();
            request.setAttribute("amount", amount);
            request.setAttribute("tour", tour);

            if ("1".equals(request.getParameter("confirm"))){
                boolean result = OrderLogic.clientOrders(user, tour, amount);

                if (result){

                    return pathManager.getString("path.page.client.complete");
                } else {
                    notification = NotificationCreator.createFromProperty("unknown error", locale);
                }
            }
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);

        } finally {
            if (notification != null){
                NotificationService.push(request.getSession(), notification);
            }
        }

        return pathManager.getString("path.page.client.order");
    }
}
