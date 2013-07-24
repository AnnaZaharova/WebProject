package kz.enu.epam.azimkhan.tour.dao;

import kz.enu.epam.azimkhan.tour.connection.ConnectionPool;
import kz.enu.epam.azimkhan.tour.entity.Order;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.ConnectionPoolException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class OrderDAO extends AbstractDAO<Integer, Order>{

    private static final String SELECT_USER_ORDERS = "SELECT order.id, tour.tourname, order.amount, order.purchase_date FROM `tour_purchase` `order`JOIN `tour` ON order.tour_id = tour.id WHERE order.client_id = ?";
    private static final String EMPTY_USER = "EMPTY USER";
    private final String CREATE_ORDER = "INSERT INTO tour_purchase (tour_id, client_id, amount, purchase_date) VALUES (?, ?, ?, ?)";
    private Logger logger = Logger.getRootLogger();

    private OrderDAO(){}

    private static OrderDAO instance;

    public static OrderDAO getInstance(){
        if (instance == null){
            instance = new OrderDAO();
        }
        return instance;
    }
    
    @Override
    public List<Order> findAll() throws DAOLogicalException, DAOTechnicalException {
        return null;
    }

    public List<Order> findOrdersForUser(User user) throws DAOLogicalException, DAOTechnicalException{
        if (user != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;

            LinkedList<Order> orders = new LinkedList<Order>();

            if (connection != null){
                try {
                    statement = connection.prepareStatement(SELECT_USER_ORDERS);
                    statement.setInt(1, user.getId());

                    ResultSet set = statement.executeQuery();

                    while(set.next()){

                        Order order = createOrder(set);
                        order.setUser(user);
                        orders.add(order);
                    }

                    return orders;
                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION_MESSAGE);
            }
        } else {
            throw new DAOLogicalException(EMPTY_USER);
        }

    }

    private Order createOrder(ResultSet set) throws SQLException{
        Order order = new Order();
        order.setId(set.getInt("id"));
        order.setAmount(set.getDouble("amount"));
        order.setDateTime(set.getTimestamp("purchase_date"));

        Tour tour = new Tour();
        tour.setTourname(set.getString("tourname"));

        order.setTour(tour);

        return order;
    }

    @Override
    public Order findById(Integer id) throws DAOLogicalException, DAOTechnicalException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DAOLogicalException, DAOTechnicalException {
        return false;
    }

    @Override
    public boolean delete(Order entity) throws DAOLogicalException, DAOTechnicalException {
        return false;
    }

    @Override
    public boolean create(Order entity) throws DAOLogicalException, DAOTechnicalException {
        if (entity != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null){
                try {
                    statement = connection.prepareStatement(CREATE_ORDER);
                    statement.setInt(1, entity.getTour().getId());
                    statement.setInt(2, entity.getUser().getId());
                    statement.setDouble(3, entity.getAmount());
                    statement.setTimestamp(4, new Timestamp(entity.getDateTime().getTime()));

                    int affected = statement.executeUpdate();
                    return (affected > 0);

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e.getMessage());

                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION_MESSAGE);
            }
        }

        return false;
    }

    @Override
    public boolean update(Order entity) throws DAOLogicalException, DAOTechnicalException {
        return false;
    }

    @Override
    public Order createEntity(ResultSet set) throws SQLException {
        Order order = new Order();
        order.setId(set.getInt("id"));
        order.setAmount(set.getDouble("amount"));
        order.setDateTime(set.getTimestamp("purchase_date"));

        return order;
    }
}
