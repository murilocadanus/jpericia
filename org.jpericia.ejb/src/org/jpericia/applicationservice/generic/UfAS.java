package org.jpericia.applicationservice.generic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.generic.UfBO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


/**
 * @author Marlus Cadanus da Costa
 */
public class UfAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(UfAS.class);

	private static UfAS me;

	private UfAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new UfAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static UfAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	
	/**
	 * Pesquisar
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = UfBO.getInstance().pesquisar();
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
			retorno = UfBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
