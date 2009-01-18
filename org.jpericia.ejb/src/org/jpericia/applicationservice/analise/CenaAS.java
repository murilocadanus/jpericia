package org.jpericia.applicationservice.analise;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.analise.CenaBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


public class CenaAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(CenaAS.class);

	private static CenaAS me;

	private CenaAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new CenaAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static CenaAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Atualizar
	 * @throws ApplicationServiceException
	 */
	public void atualizar(Cena cena) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaBO.getInstance().atualizar(cena);
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
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Cena cena) throws ApplicationServiceException{
		try {
			beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaBO.getInstance().inserir(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			commitTransaction();
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
	
	/**
	 * Remover
	 * @throws ApplicationServiceException
	 */
	public void remover(Cena cena) throws ApplicationServiceException{
		try {
			beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaBO.getInstance().remover(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			commitTransaction();
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
			ArrayList<AbstractEntity> pesquisa = CenaBO.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar Cliente.", boe);
		} 
	}
}
