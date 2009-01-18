package org.jpericia.businessobject.organizacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.organizacao.OrganizacaoDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class OrganizacaoBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(OrganizacaoBO.class);
	
	private static OrganizacaoBO me;

	private OrganizacaoBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new OrganizacaoBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static OrganizacaoBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Organizacao organizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			OrganizacaoDAO organizacaoDAO = (OrganizacaoDAO)factory.getDao(OrganizacaoDAO.class);
			organizacaoDAO.inserir(organizacao);
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
	public void atualizar(Organizacao organizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			OrganizacaoDAO organizacaoDAO = (OrganizacaoDAO)factory.getDao(OrganizacaoDAO.class);
			organizacaoDAO.atualizar(organizacao);
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
	public void remover(Organizacao organizacao) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			OrganizacaoDAO organizacaoDAO = (OrganizacaoDAO)factory.getDao(OrganizacaoDAO.class);
			organizacaoDAO.remover(organizacao);
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
			OrganizacaoDAO organizacaoDAO = (OrganizacaoDAO)factory.getDao(OrganizacaoDAO.class);
			ArrayList<AbstractEntity> pesquisa = organizacaoDAO.pesquisar();
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
			OrganizacaoDAO organizacaoDAO = (OrganizacaoDAO)factory.getDao(OrganizacaoDAO.class);
			retorno = organizacaoDAO.consultar(criterios);
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
