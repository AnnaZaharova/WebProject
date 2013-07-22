package kz.enu.epam.azimkhan.auth.dao;

import kz.enu.epam.azimkhan.auth.connection.ConnectionPool;
import kz.enu.epam.azimkhan.auth.entity.Tour;
import kz.enu.epam.azimkhan.auth.entity.TourType;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.ConnectionPoolException;
import kz.enu.epam.azimkhan.auth.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.auth.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Tour DAO
 */
public class TourDAO extends AbstractDAO<Integer, Tour>{
	private final String SELECT_ALL = "SELECT id, tourname, details, hot, price, regular_discount, type FROM tour";
	private static final Logger logger = Logger.getRootLogger();

	@Override
	public List<Tour> findAll() throws DAOLogicalException, DAOTechnicalException {
		ConnectionPool connectionPool = null;
		try{
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e){
			throw new DAOTechnicalException(e);
		}

		Connection connection = connectionPool.getConnection();
		PreparedStatement statement = null;

		LinkedList<Tour> tours= new LinkedList<Tour>();

		if (connection != null){
			try {
				statement = connection.prepareStatement(SELECT_ALL);
				ResultSet set = statement.executeQuery();

				while(set.next()){
					Tour tour = createEntity(set);
					tours.add(tour);
				}

				return tours;
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
			throw new DAOTechnicalException();
		}
	}

	@Override
	public Tour findById(Integer id) throws DAOLogicalException, DAOTechnicalException {
		return null;  
	}

	@Override
	public boolean delete(Integer id) throws DAOLogicalException, DAOTechnicalException {
		return false;  
	}

	@Override
	public boolean delete(Tour entity) throws DAOLogicalException, DAOTechnicalException {
		return false;  
	}

	@Override
	public boolean create(Tour entity) throws DAOLogicalException, DAOTechnicalException {
		return false;  
	}

	@Override
	public boolean update(Tour entity) throws DAOLogicalException, DAOTechnicalException {
		return false;  
	}

	@Override
	public Tour createEntity(ResultSet set) throws SQLException{
		Tour tour = new Tour();

		tour.setId(set.getInt("id"));
		tour.setDetails(set.getString("details"));

		int hot = set.getInt("hot");
		tour.setHot(hot == 1);

		tour.setPrice(set.getInt("price"));
		tour.setTourname(set.getString("tourname"));
		tour.setRegularDiscount(set.getInt("regular_discount"));

		TourType type = TourType.findById(set.getInt("type"));
		tour.setType(type);

		return tour;
	}
}
