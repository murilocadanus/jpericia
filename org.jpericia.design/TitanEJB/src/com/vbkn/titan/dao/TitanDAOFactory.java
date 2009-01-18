package com.vbkn.titan.dao;

import com.vbkn.titan.ejb.exception.TitanDAOFactoryException;

/**
 * @author Valter Bruno Konrad Neto
 */
public interface TitanDAOFactory {
	
	public static final String DEFAULT_FACTORY_NAME = "JPA";
	
	/**
	 * Recupera o DAO configurado no arquivo dao.properties.
	 * 
	 * @param daoClass do Dao
	 * @return retorna uma instancia do dao
	 * @throws TitanDAOFactoryException exceção lançada quando não for possível obter o Dao
	 */
	public abstract Dao getDao(Class<?> daoClass) throws TitanDAOFactoryException;
	
}
