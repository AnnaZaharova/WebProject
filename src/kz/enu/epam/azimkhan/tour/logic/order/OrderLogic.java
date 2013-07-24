package kz.enu.epam.azimkhan.tour.logic.order;

import kz.enu.epam.azimkhan.tour.dao.OrderDAO;
import kz.enu.epam.azimkhan.tour.entity.Order;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Performs order logic
 */
public class OrderLogic {
    private static final Logger logger = Logger.getRootLogger();

    public static boolean clientOrders(User user, Tour tour, double amount){

        if (user != null && tour != null){
            Order order = new Order();
            order.setUser(user);
            order.setTour(tour);

            order.setAmount(amount);
            order.setDateTime(new Date());

            OrderDAO dao = OrderDAO.getInstance();
            try{
                return dao.create(order);
            } catch (DAOTechnicalException e) {
                logger.error(e.getMessage(), e);
                return false;
            } catch (DAOLogicalException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        } else{
            logger.error("Invalid data user: " + user + ", tour: " + tour);
            return false;
        }

    }
}
