package org.jpericia.applicationservice.pericia;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.pericia.PericiaBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


public class PericiaAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(PericiaAS.class);

	private static PericiaAS me;

	private PericiaAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new PericiaAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static PericiaAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Pericia pericia) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaBO.getInstance().inserir(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException e) {
			this.rollbackTransaction(this.getClass() + ".salvar()");
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", e);
		} finally{
			this.closeSession();
		}
	}
	
	/**
	 * Atualizar
	 * @throws ApplicationServiceException
	 */
	public void atualizar(Pericia pericia) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaBO.getInstance().atualizar(pericia);
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
	public void remover(Pericia pericia) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaBO.getInstance().remover(pericia);
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
			ArrayList<AbstractEntity> pesquisa = PericiaBO.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}

	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnalises()");
			ArrayList<AbstractEntity> pesquisa = PericiaBO.getInstance().pesquisarAnalises(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}	

	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarEvidencias()");
			ArrayList<AbstractEntity> pesquisa = PericiaBO.getInstance().pesquisarEvidencias(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarEvidencias()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".pesquisarEvidencias()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnalises()");
			ArrayList<AbstractEntity> pesquisa = PericiaBO.getInstance().pesquisarAnexos(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}	

	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarCenas()");
			ArrayList<AbstractEntity> pesquisa = PericiaBO.getInstance().pesquisarCenas(analise);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarCenas()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".pesquisarCenas()");
			throw new ApplicationServiceException("Ocorreu um erro so pesquisar Cenas.", boe);
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
			retorno = PericiaBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
