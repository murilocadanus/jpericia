package org.jpericia.businessobject.organizacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.organizacao.TipoOrganizacaoDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class TipoOrganizacaoBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(TipoOrganizacaoBO.class);
	
	private static TipoOrganizacaoBO me;

	private TipoOrganizacaoBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new TipoOrganizacaoBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static TipoOrganizacaoBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(TipoOrganizacao tipoOrganizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			TipoOrganizacaoDAO tipoOrganizacaoDAO = (TipoOrganizacaoDAO)factory.getDao(TipoOrganizacaoDAO.class);
			tipoOrganizacaoDAO.inserir(tipoOrganizacao);
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
	public void atualizar(TipoOrganizacao tipoOrganizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			TipoOrganizacaoDAO tipoOrganizacaoDAO = (TipoOrganizacaoDAO)factory.getDao(TipoOrganizacaoDAO.class);
			tipoOrganizacaoDAO.atualizar(tipoOrganizacao);
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
	public void remover(TipoOrganizacao tipoOrganizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			TipoOrganizacaoDAO tipoOrganizacaoDAO = (TipoOrganizacaoDAO)factory.getDao(TipoOrganizacaoDAO.class);
			tipoOrganizacaoDAO.remover(tipoOrganizacao);
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
			TipoOrganizacaoDAO tipoOrganizacaoDAO = (TipoOrganizacaoDAO)factory.getDao(TipoOrganizacaoDAO.class);
			ArrayList<AbstractEntity> pesquisa = tipoOrganizacaoDAO.pesquisar();
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
			TipoOrganizacaoDAO tipoOrganizacaoDAO = (TipoOrganizacaoDAO)factory.getDao(TipoOrganizacaoDAO.class);
			retorno = tipoOrganizacaoDAO.consultar(criterios);
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
