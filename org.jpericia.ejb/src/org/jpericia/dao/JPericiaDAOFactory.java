package org.jpericia.dao;

import org.jpericia.ejb.exception.JPericiaDAOFactoryException;

public interface JPericiaDAOFactory {
	
	public static final String DEFAULT_FACTORY_NAME = "JPA";
	
	/**
	 * Recupera o DAO configurado no arquivo dao.properties.
	 * 
	 * @param daoClass do Dao
	 * @return retorna uma instancia do dao
	 * @throws JPericiaDAOFactoryException exceção lançada quando não for possível obter o Dao
	 */
	public abstract Dao getDao(Class<?> daoClass) throws JPericiaDAOFactoryException;
	
}
