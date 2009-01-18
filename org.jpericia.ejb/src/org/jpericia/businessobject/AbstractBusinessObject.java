package org.jpericia.businessobject;

import org.apache.log4j.Logger;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.JPericiaDAOFactoryImpl;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


/**
 * Classe base para todos as classes que implementam o padrão
 * J2EE Business Object
 */
public abstract class AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(AbstractBusinessObject.class);
	
	protected JPericiaDAOFactory getDaoFactory() throws JPericiaDAOFactoryException {
		logger.debug("Obtendo daoFactory");
		JPericiaDAOFactory daoFactory = (JPericiaDAOFactory) new JPericiaDAOFactoryImpl().getDAOFactory(JPericiaDAOFactory.DEFAULT_FACTORY_NAME);
        return daoFactory;
    }

}
