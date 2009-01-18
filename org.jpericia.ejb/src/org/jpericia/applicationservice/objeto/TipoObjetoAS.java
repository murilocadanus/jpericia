package org.jpericia.applicationservice.objeto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.objeto.TipoObjetoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


/**
 * @author Marlus Cadanus da Costa
 */
public class TipoObjetoAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(TipoObjetoAS.class);

	private static TipoObjetoAS me;

	private TipoObjetoAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new TipoObjetoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static TipoObjetoAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(TipoObjeto tipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoBO.getInstance().inserir(tipoObjeto);
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
	public void atualizar(TipoObjeto tipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoBO.getInstance().atualizar(tipoObjeto);
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
	public void remover(TipoObjeto tipoObjeto) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoBO.getInstance().remover(tipoObjeto);
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
			ArrayList<AbstractEntity> pesquisa = TipoObjetoBO.getInstance().pesquisar();
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
			retorno = TipoObjetoBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
