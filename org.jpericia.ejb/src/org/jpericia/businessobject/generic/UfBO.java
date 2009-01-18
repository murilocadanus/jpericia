package org.jpericia.businessobject.generic;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.generic.UfDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class UfBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(UfBO.class);
	
	private static UfBO me;

	private UfBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new UfBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static UfBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			UfDAO ufDAO = (UfDAO)factory.getDao(UfDAO.class);
			ArrayList<AbstractEntity> pesquisa = ufDAO.pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
	
	/**
	 * Consultar Cliente
	 * @throws ApplicationServiceException
	 */
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws BusinessObjectException{
		PaginaTO retorno = null;
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			UfDAO ufDAO = (UfDAO)factory.getDao(UfDAO.class);
			retorno = ufDAO.consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro consultando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro consultando Cliente", de);
		}
		return retorno;
	}

}
