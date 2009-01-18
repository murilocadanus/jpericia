package com.vbkn.titan.businessobject;

import org.apache.log4j.Logger;

import com.vbkn.titan.dao.TitanDAOFactory;
import com.vbkn.titan.dao.TitanDAOFactoryImpl;
import com.vbkn.titan.ejb.exception.TitanDAOFactoryException;

/**
 * @author Valter Bruno Konrad Neto
 * Classe base para todos as classes que implementam o padrão
 * J2EE Business Object
 */
public abstract class AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(AbstractBusinessObject.class);
	
	protected TitanDAOFactory getDaoFactory() throws TitanDAOFactoryException {
		logger.debug("Obtendo daoFactory");
		TitanDAOFactory daoFactory = (TitanDAOFactory) new TitanDAOFactoryImpl().getDAOFactory(TitanDAOFactory.DEFAULT_FACTORY_NAME);
        return daoFactory;
    }

}
