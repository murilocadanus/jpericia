package com.vbkn.titan.applicationservice.cliente;

import org.apache.log4j.Logger;

import com.vbkn.titan.applicationservice.AbstractApplicationService;
import com.vbkn.titan.businessobject.cliente.ClienteBO;
import com.vbkn.titan.ejb.exception.ApplicationServiceException;
import com.vbkn.titan.ejb.exception.BusinessObjectException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */
public class ClienteAS extends AbstractApplicationService {
	
	private static Logger logger = Logger.getLogger(ClienteAS.class);

	private static ClienteAS me;

	private ClienteAS() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new ClienteAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static ClienteAS getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Salvar
	 * @throws ApplicationServiceException
	 */
	public void salvar(Cliente cliente) throws ApplicationServiceException{
		try {
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ClienteBO.getInstance().salvar(cliente);
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
	 * Salvar
	 * @throws ApplicationServiceException
	 */
	public void salvarCMT(Cliente cliente) throws ApplicationServiceException{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ClienteBO.getInstance().salvar(cliente);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
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
			retorno = ClienteBO.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (BusinessObjectException boe) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new ApplicationServiceException("Ocorreu um erro ao consultar Cliente.", boe);
		} 
		return retorno;
	}

}
