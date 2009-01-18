package org.jpericia.applicationservice.perito;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.perito.PeritoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;


/**
 * @author Marlus Cadanus da Costa
 */
public class PeritoAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(PeritoAS.class);

	private static PeritoAS me;

	private PeritoAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new PeritoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static PeritoAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir
	 * @throws ApplicationServiceException
	 */
	public void inserir(Perito Perito) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoBO.getInstance().inserir(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException e) {
			this.rollbackTransaction(this.getClass() + ".salvar()");
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar.", e);
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
	public void atualizar(Perito Perito) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoBO.getInstance().atualizar(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao salvar.", boe);
		} 
	}
	
	/**
	 * Remover
	 * @throws ApplicationServiceException
	 */
	public void remover(Perito Perito) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoBO.getInstance().remover(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao remover.", boe);
		} 
	}
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = PeritoBO.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException("Ocorreu um erro ao pesquisar.", boe);
		} 
	}
		
	/**
	 * Consultar
	 * @throws ApplicationServiceException
	 */
	public Perito autenticar(Perito perito) throws ApplicationServiceException{
		Perito retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".autenticar()");
			retorno = PeritoBO.getInstance().autenticar(perito);
			logger.debug("Fim " + this.getClass().getName() + ".autenticar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".autenticar()");
			throw new ApplicationServiceException("Ocorreu um erro ao autenticar.", boe);
		} 
		return retorno;
	}

}
