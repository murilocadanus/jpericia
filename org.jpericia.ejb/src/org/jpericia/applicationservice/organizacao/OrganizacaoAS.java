package org.jpericia.applicationservice.organizacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.organizacao.OrganizacaoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


/**
 * @author Marlus Cadanus da Costa
 */
public class OrganizacaoAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(OrganizacaoAS.class);

	private static OrganizacaoAS me;

	private OrganizacaoAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new OrganizacaoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static OrganizacaoAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Organizacao organizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			OrganizacaoBO.getInstance().inserir(organizacao);
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
	public void atualizar(Organizacao organizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			OrganizacaoBO.getInstance().atualizar(organizacao);
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
	public void remover(Organizacao organizacao) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			OrganizacaoBO.getInstance().remover(organizacao);
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
			ArrayList<AbstractEntity> pesquisa = OrganizacaoBO.getInstance().pesquisar();
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
			retorno = OrganizacaoBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
