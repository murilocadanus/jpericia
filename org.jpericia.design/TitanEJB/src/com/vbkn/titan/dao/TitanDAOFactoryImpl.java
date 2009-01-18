package com.vbkn.titan.dao;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vbkn.titan.ejb.exception.TitanDAOFactoryException;

/**
 * @author Valter Bruno Konrad Neto
 * 
 * Classe que implementa o padrão
 * Abstract Factory
 * 
 */
public class TitanDAOFactoryImpl implements TitanDAOFactory {
	
	private static Logger logger = Logger.getLogger(TitanDAOFactoryImpl.class);
	
	private static TitanDAOFactoryImpl me;

	public TitanDAOFactoryImpl() {
		
	}
	
	private static synchronized void loadInstance() {
		if (me == null) {
			me = new TitanDAOFactoryImpl();
		}
	}

	public Dao getDao(Class<?> daoClass) throws TitanDAOFactoryException {
		Dao dao = null;
		try {
			Properties daoProperties = this.getProperties();
			String tipo = daoProperties.getProperty(daoClass.getName() + ".type");
			if("JPA".equals(tipo)){
				String concreteClass = daoProperties.getProperty(daoClass.getName()+ ".concreteClass");
				dao = (Dao) this.createNewInstance(concreteClass);
			}
		} catch (TitanDAOFactoryException e) {
			logger.fatal(e.getMessage(), e);
			throw new TitanDAOFactoryException(e);
		} 
		return dao;
	}

	public TitanDAOFactory getDAOFactory(String name) throws TitanDAOFactoryException {
		if (TitanDAOFactory.DEFAULT_FACTORY_NAME.equals(name)){
			loadInstance();
		}
		else{
			logger.fatal("Factory não existe para " + name);
			throw new TitanDAOFactoryException("Factory não existe");
		}
		return me;
	}
	
	/**
	 * Obtém o arquivo properties do DAO.
	 * 
	 * @return properties do DAO.
	 * @throws TitanDAOFactoryException
	 *             se não conseguir ler o arquivo.
	 */
	private Properties getProperties() throws TitanDAOFactoryException {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties"));
		} catch (IOException e) {
			logger.fatal("Erro carregando dao.properties");
			throw new TitanDAOFactoryException(e);
		}
		return properties;
	}
	
	/**
	 * Obtem o Class para uma dada String.
	 * 
	 * @param clazz
	 *            nome da classe
	 * @return retorna o Class
	 * @throws TitanDAOFactoryException
	 *             exceção lançada quando não for possível obter o Class
	 */
	private Class<?> getClass(String clazz) throws TitanDAOFactoryException {
		Class<?> classe = null;
		try {
			classe = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			logger.fatal(e.getMessage(), e);
			throw new TitanDAOFactoryException(e);
		}
		return classe;
	}

	
	/**
	 * Cria um instancia do Dao.
	 * 
	 * @param clazz
	 *            nome da classe
	 * @return retorna uma instancia da classe
	 * @throws TitanDAOFactoryException
	 *             exceção lançada quando não for possíve criar a instanica
	 */
	private Object createNewInstance(String clazz) throws TitanDAOFactoryException {
		Object obj = null;
		try {
			obj = this.getClass(clazz).newInstance();
		} catch (InstantiationException e) {
			logger.fatal(e.getMessage(), e);
			throw new TitanDAOFactoryException(e);
		} catch (IllegalAccessException e) {
			logger.fatal(e.getMessage(), e);
			throw new TitanDAOFactoryException(e);
		}
		return obj;
	}

	
	
	
}
