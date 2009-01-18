package org.jpericia.businessobject.objeto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.objeto.CategoriaTipoObjetoDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class CategoriaTipoObjetoBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(CategoriaTipoObjetoBO.class);
	
	private static CategoriaTipoObjetoBO me;

	private CategoriaTipoObjetoBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new CategoriaTipoObjetoBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static CategoriaTipoObjetoBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(CategoriaTipoObjeto categoriaTipoObjeto) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CategoriaTipoObjetoDAO categoriaTipoObjetoDAO = (CategoriaTipoObjetoDAO)factory.getDao(CategoriaTipoObjetoDAO.class);
			categoriaTipoObjetoDAO.inserir(categoriaTipoObjeto);
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
	public void atualizar(CategoriaTipoObjeto categoriaTipoObjeto) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CategoriaTipoObjetoDAO categoriaTipoObjetoDAO = (CategoriaTipoObjetoDAO)factory.getDao(CategoriaTipoObjetoDAO.class);
			categoriaTipoObjetoDAO.atualizar(categoriaTipoObjeto);
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
	public void remover(CategoriaTipoObjeto categoriaTipoObjeto) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CategoriaTipoObjetoDAO categoriaTipoObjetoDAO = (CategoriaTipoObjetoDAO)factory.getDao(CategoriaTipoObjetoDAO.class);
			categoriaTipoObjetoDAO.remover(categoriaTipoObjeto);
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
			CategoriaTipoObjetoDAO categoriaTipoObjetoDAO = (CategoriaTipoObjetoDAO)factory.getDao(CategoriaTipoObjetoDAO.class);
			ArrayList<AbstractEntity> pesquisa = categoriaTipoObjetoDAO.pesquisar();
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
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws BusinessObjectException{
		PaginaTO retorno = null;
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			CategoriaTipoObjetoDAO categoriaTipoObjetoDAO = (CategoriaTipoObjetoDAO)factory.getDao(CategoriaTipoObjetoDAO.class);
			retorno = categoriaTipoObjetoDAO.consultar(criterios);
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
