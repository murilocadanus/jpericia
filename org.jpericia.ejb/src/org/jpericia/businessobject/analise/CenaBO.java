package org.jpericia.businessobject.analise;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.analise.CenaDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


/**
 * @author Marlus Cadanus da Costa
 */
public class CenaBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(CenaBO.class);
	
	private static CenaBO me;

	private CenaBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new CenaBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static CenaBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Cena cena) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CenaDAO cenaDAO = (CenaDAO)factory.getDao(CenaDAO.class);
			cenaDAO.inserir(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
	
	/**
	 * Atualizar
	 * @throws ApplicationServiceException
	 */
	public void atualizar(Cena cena) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CenaDAO cenaDAO = (CenaDAO)factory.getDao(CenaDAO.class);
			cenaDAO.atualizar(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
	
	/**
	 * Remover
	 * @throws ApplicationServiceException
	 */
	public void remover(Cena cena) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CenaDAO cenaDAO = (CenaDAO)factory.getDao(CenaDAO.class);
			cenaDAO.remover(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CenaDAO cenaDAO = (CenaDAO)factory.getDao(CenaDAO.class);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return cenaDAO.pesquisar();
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
}
