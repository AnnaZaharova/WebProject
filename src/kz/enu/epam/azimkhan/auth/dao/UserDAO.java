package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.entity.Role;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.ConnectionPoolException;
import kz.enu.epam.azimkhan.auth.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.auth.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class UserDAO extends AbstractDAO<Integer, User>{

    private static final String SELECT_ALL = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id";
    private static final String FIND_BY_ID = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id WHERE user.id = ?";
    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id WHERE user.username = ? AND user.password = ?";
    private static final String DELETE_BY_ID = "DELETE user WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO user (username, password, role_id) VALUES(?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, role_id = ? WHERE id = ?";
    private static final Logger logger = Logger.getRootLogger();

    /**
     * Find all users
     * @return
     * @throws DAOLogicalException
     * @throws DAOTechnicalException
     */
    @Override
    public List<User> findAll() throws DAOLogicalException, DAOTechnicalException {

        ConnectionPool connectionPool = null;
        try{
            connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e){
            throw new DAOTechnicalException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        LinkedList<User> users = new LinkedList<User>();

        if (connection != null){
            try {
                statement = connection.prepareStatement(SELECT_ALL);
                ResultSet set = statement.executeQuery();

                while(set.next()){

                    User user = createFromResultSet(set);
                    users.add(user);
                }

                return users;
            } catch (SQLException e) {
                throw new DAOLogicalException(e);
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

    /**
     * Find a user by id
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) throws DAOLogicalException, DAOTechnicalException {

        User user = null;

        if (id != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                try{
                    statement = connection.prepareStatement(FIND_BY_ID);
                    statement.setInt(1, id);

                    ResultSet set = statement.executeQuery();


                    if (set.next()){
                        user = createFromResultSet(set);
                    }

                } catch (SQLException e){
                    throw new DAOLogicalException(e);
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

        return user;
    }

    /**
     * Find user by username and hashed password
     * @param login
     * @param password
     * @return
     */
    public User findByLoginAndPassword(String login, String password) throws DAOLogicalException, DAOTechnicalException{
        User user = null;

        if(login != null && password != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = null;

            if (connection != null) {
                try {
                    statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
                    statement.setString(1, login);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        user = createFromResultSet(resultSet);
                    }

                } catch (SQLException e) {
                    throw new DAOLogicalException(e);
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
        return user;
    }

    /**
     * Delete user by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) throws DAOLogicalException, DAOTechnicalException {

        if (null != id) {

            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                try {
                    statement = connection.prepareStatement(DELETE_BY_ID);
                    statement.setInt(1, id);
                    int affected = statement.executeUpdate();
                    return (affected > 0);

                } catch (SQLException e) {
                    throw new DAOLogicalException();
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

    /**
     * Delete user
     *
     * @param entity
     * @return
     */
    @Override
    public boolean delete(User entity) throws DAOLogicalException, DAOTechnicalException {
        if (entity != null){
             return delete(entity.getId());
        }

        return false;
    }

    /**
     * Create user
     *
     * @param entity
     * @return
     */
    @Override
    public boolean create(User entity) throws DAOLogicalException, DAOTechnicalException {
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
                    statement = connection.prepareStatement(CREATE_USER);
                    statement.setString(1, entity.getUsername());
                    statement.setString(2, entity.getPassword());
					statement.setInt(3, entity.getRole().getId());

                    int affected = statement.executeUpdate();
                    return (affected > 0);

                } catch (SQLException e) {
                    throw new DAOLogicalException(e.getMessage());

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

    /**
     * Update user
     *
     *
     *
     * @param entity
     * @return
     */
    @Override
    public boolean update(User entity) throws DAOLogicalException, DAOTechnicalException {


        if (entity != null){
            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e) {
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;

            if (connection != null){
                try {
                    statement = connection.prepareStatement(UPDATE_USER);
                    statement.setString(1, entity.getUsername());
                    statement.setString(2, entity.getPassword());
					statement.setInt(3, entity.getRole().getId());
                    statement.setInt(4, entity.getId());

                    int affected = statement.executeUpdate();

                    return (affected > 0);

                } catch (SQLException e) {
                    throw new DAOLogicalException(e);
                } finally {
                    if (null != statement){
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }

                        connectionPool.release(connection);
                    }
                }

            } else{
                throw new DAOTechnicalException(NO_CONNECTION_MESSAGE);
            }
        }
        return false;
    }

    /**
     * Create user from result set
     * @param set
     * @return
     * @throws SQLException
     */
    private User createFromResultSet(ResultSet set) throws SQLException{

        User user = new User();
        user.setId(set.getInt("id"));
        user.setUsername(set.getString("username"));
        user.setPassword(set.getString("password"));

		Role role = new Role();
		role.setId (set.getInt("role_id"));
		role.setRolename(set.getString("rolename"));

		user.setRole(role);

        return user;
    }
}
