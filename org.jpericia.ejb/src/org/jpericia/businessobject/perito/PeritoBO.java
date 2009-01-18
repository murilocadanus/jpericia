package org.jpericia.businessobject.perito;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.perito.PeritoDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class PeritoBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(PeritoBO.class);
	
	private static PeritoBO me;

	private PeritoBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new PeritoBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static PeritoBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Perito Perito) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PeritoDAO PeritoDAO = (PeritoDAO)factory.getDao(PeritoDAO.class);
			PeritoDAO.inserir(Perito);
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
	public void atualizar(Perito Perito) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PeritoDAO PeritoDAO = (PeritoDAO)factory.getDao(PeritoDAO.class);
			PeritoDAO.atualizar(Perito);
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
	public void remover(Perito Perito) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PeritoDAO PeritoDAO = (PeritoDAO)factory.getDao(PeritoDAO.class);
			PeritoDAO.remover(Perito);
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
			PeritoDAO PeritoDAO = (PeritoDAO)factory.getDao(PeritoDAO.class);
			ArrayList<AbstractEntity> pesquisa = PeritoDAO.pesquisar();
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
	 * Consultar
	 * @throws ApplicationServiceException
	 */
	public Perito autenticar(Perito perito) throws BusinessObjectException{
		Perito retorno = null;
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".autenticar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PeritoDAO PeritoDAO = (PeritoDAO)factory.getDao(PeritoDAO.class);
			retorno = PeritoDAO.autenticar(perito);
			logger.debug("Fim " + this.getClass().getName() + ".autenticar()");
		}catch (JPericiaDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro autenticando", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro autenticando", de);
		}
		return retorno;
	}

}
