package org.jpericia.applicationservice.objeto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.objeto.CategoriaTipoObjetoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


/**
 * @author Marlus Cadanus da Costa
 */
public class CategoriaTipoObjetoAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(CategoriaTipoObjetoAS.class);

	private static CategoriaTipoObjetoAS me;

	private CategoriaTipoObjetoAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new CategoriaTipoObjetoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static CategoriaTipoObjetoAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(CategoriaTipoObjeto categoriaTipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CategoriaTipoObjetoBO.getInstance().inserir(categoriaTipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException e) {
			this.rollbackTransaction(this.getClass() + ".salvar()");
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeSession();
		}
	}
	
	/**
	 * Atualizar
	 * @throws ApplicationServiceException
	 */
	public void atualizar(CategoriaTipoObjeto categoriaTipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CategoriaTipoObjetoBO.getInstance().atualizar(categoriaTipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
	
	/**
	 * Remover
	 * @throws ApplicationServiceException
	 */
	public void remover(CategoriaTipoObjeto categoriaTipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CategoriaTipoObjetoBO.getInstance().remover(categoriaTipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = CategoriaTipoObjetoBO.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
		
	/**
	 * Consultar
	 * @throws ApplicationServiceException
	 */
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws ApplicationServiceException{
		PaginaTO retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			retorno = CategoriaTipoObjetoBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
