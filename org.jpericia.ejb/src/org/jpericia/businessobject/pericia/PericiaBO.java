package org.jpericia.businessobject.pericia;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessobject.AbstractBusinessObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.JPericiaDAOFactory;
import org.jpericia.dao.pericia.PericiaDAO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.ejb.exception.DAOException;
import org.jpericia.ejb.exception.JPericiaDAOFactoryException;


public class PericiaBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(PericiaBO.class);
	
	private static PericiaBO me;

	private PericiaBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new PericiaBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static PericiaBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Pericia pericia) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			periciaDAO.inserir(pericia);
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
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void atualizar(Pericia pericia) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			periciaDAO.atualizar(pericia);
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
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void remover(Pericia pericia) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			periciaDAO.remover(pericia);
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
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			ArrayList<AbstractEntity> pesquisa = periciaDAO.pesquisar();
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
	 * Pesquisar Analises
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnalises()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			ArrayList<AbstractEntity> pesquisa = periciaDAO.pesquisarAnalises(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
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
	 * Pesquisar Analises
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarEvidencias()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			ArrayList<AbstractEntity> pesquisa = periciaDAO.pesquisarEvidencias(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarEvidencias()");
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
	 * Pesquisar Analises
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnalises()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			ArrayList<AbstractEntity> pesquisa = periciaDAO.pesquisarAnexos(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
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
	 * Pesquisar Analises
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarCenas()");
			JPericiaDAOFactory factory = this.getDaoFactory();
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			ArrayList<AbstractEntity> pesquisa = periciaDAO.pesquisarCenas(analise);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarCenas()");
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
			PericiaDAO periciaDAO = (PericiaDAO)factory.getDao(PericiaDAO.class);
			retorno = periciaDAO.consultar(criterios);
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
