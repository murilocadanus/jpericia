package org.jpericia.applicationservice.organizacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.organizacao.TipoOrganizacaoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


/**
 * @author Marlus Cadanus da Costa
 */
public class TipoOrganizacaoAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(TipoOrganizacaoAS.class);

	private static TipoOrganizacaoAS me;

	private TipoOrganizacaoAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new TipoOrganizacaoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static TipoOrganizacaoAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(TipoOrganizacao tipoOrganizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoOrganizacaoBO.getInstance().inserir(tipoOrganizacao);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException e) {
			this.rollbackTransaction(this.getClass() + ".salvar()");
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", e);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeSession();
		}
	}
	
	/**
	 * Atualizar
	 * @throws ApplicationServiceException
	 */
	public void atualizar(TipoOrganizacao tipoOrganizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoOrganizacaoBO.getInstance().atualizar(tipoOrganizacao);
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
	public void remover(TipoOrganizacao tipoOrganizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoOrganizacaoBO.getInstance().remover(tipoOrganizacao);
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
			ArrayList<AbstractEntity> pesquisa = TipoOrganizacaoBO.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
	
	/**
	 * Consultar Cliente
	 * @throws ApplicationServiceException
	 */
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws ApplicationServiceException{
		PaginaTO retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			retorno = TipoOrganizacaoBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
