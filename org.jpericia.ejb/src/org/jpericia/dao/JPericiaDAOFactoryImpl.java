package org.jpericia.dao;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


/**
 * Classe que implementa o padrão
 * Abstract Factory
 * 
 */
public class JPericiaDAOFactoryImpl implements JPericiaDAOFactory {
	
	private static Logger logger = Logger.getLogger(JPericiaDAOFactoryImpl.class);
	
	private static JPericiaDAOFactoryImpl me;

	public JPericiaDAOFactoryImpl() {
		
	}
	
	private static synchronized void loadInstance() {
		if (me == null) {
			me = new JPericiaDAOFactoryImpl();
		}
	}

	public Dao getDao(Class<?> daoClass) throws JPericiaDAOFactoryException {
		Dao dao = null;
		try {
			Properties daoProperties = this.getProperties();
			String tipo = daoProperties.getProperty(daoClass.getName() + ".type");
			if("JPA".equals(tipo)){
				String concreteClass = daoProperties.getProperty(daoClass.getName()+ ".concreteClass");
				dao = (Dao) this.createNewInstance(concreteClass);
			}
		} catch (JPericiaDAOFactoryException e) {
			logger.fatal(e.getMessage(), e);
			throw new JPericiaDAOFactoryException(e);
		} 
		return dao;
	}

	public JPericiaDAOFactory getDAOFactory(String name) throws JPericiaDAOFactoryException {
		if (JPericiaDAOFactory.DEFAULT_FACTORY_NAME.equals(name)){
			loadInstance();
		}
		else{
			logger.fatal("Factory não existe para " + name);
			throw new JPericiaDAOFactoryException("Factory não existe");
		}
		return me;
	}
	
	/**
	 * Obtém o arquivo properties do DAO.
	 * 
	 * @return properties do DAO.
	 * @throws JPericiaDAOFactoryException
	 *             se não conseguir ler o arquivo.
	 */
	private Properties getProperties() throws JPericiaDAOFactoryException {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties"));
		} catch (IOException e) {
			logger.fatal("Erro carregando dao.properties");
			throw new JPericiaDAOFactoryException(e);
		}
		return properties;
	}
	
	/**
	 * Obtem o Class para uma dada String.
	 * 
	 * @param clazz
	 *            nome da classe
	 * @return retorna o Class
	 * @throws JPericiaDAOFactoryException
	 *             exceção lançada quando não for possível obter o Class
	 */
	private Class<?> getClass(String clazz) throws JPericiaDAOFactoryException {
		Class<?> classe = null;
		try {
			classe = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			logger.fatal(e.getMessage(), e);
			throw new JPericiaDAOFactoryException(e);
		}
		return classe;
	}

	
	/**
	 * Cria um instancia do Dao.
	 * 
	 * @param clazz
	 *            nome da classe
	 * @return retorna uma instancia da classe
	 * @throws JPericiaDAOFactoryException
	 *             exceção lançada quando não for possível criar a instanica
	 */
	private Object createNewInstance(String clazz) throws JPericiaDAOFactoryException {
		Object obj = null;
		try {
			obj = this.getClass(clazz).newInstance();
		} catch (InstantiationException e) {
			logger.fatal(e.getMessage(), e);
			throw new JPericiaDAOFactoryException(e);
		} catch (IllegalAccessException e) {
			logger.fatal(e.getMessage(), e);
			throw new JPericiaDAOFactoryException(e);
		}
		return obj;
	}

	
	
	
}
