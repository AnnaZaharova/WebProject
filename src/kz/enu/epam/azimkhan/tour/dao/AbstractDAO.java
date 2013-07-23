package kz.enu.epam.azimkhan.tour.dao;

import kz.enu.epam.azimkhan.tour.entity.Entity;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract Data Access Object
 * @param <K>
 * @param <T>
 */
public abstract class AbstractDAO <K, T extends Entity> {
	protected final String NO_CONNECTION_MESSAGE = "Unable to get connection with database";

	/**
	 * Find all entites
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract List<T> findAll() throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Find entity by id
	 * @param id
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract T findById(K id) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Delete entity by id
	 * @param id
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean delete(K id) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Delete entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean delete(T entity) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Create entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean create(T entity) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Update entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean update(T entity) throws DAOLogicalException, DAOTechnicalException;

	public abstract T createEntity(ResultSet set) throws SQLException;
}