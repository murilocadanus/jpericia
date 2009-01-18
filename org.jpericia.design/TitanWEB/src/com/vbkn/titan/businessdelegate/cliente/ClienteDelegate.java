package com.vbkn.titan.businessdelegate.cliente;

import org.apache.log4j.Logger;

import com.vbkn.titan.businessdelegate.AbstractBusinessDelegate;
import com.vbkn.titan.ejb.exception.SessionFacadeException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.exception.BusinessDelegateException;
import com.vbkn.titan.exception.ServiceLocatorException;
import com.vbkn.titan.sessionfacade.cliente.ClienteSessionFacade;
import com.vbkn.titan.sessionfacade.cliente.ClienteSessionFacadeLocal;
import com.vbkn.titan.sessionfacade.cliente.ClienteSessionFacadeRemote;
import com.vbkn.titan.util.ServiceLocator;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */

@SuppressWarnings("all")
public class ClienteDelegate extends AbstractBusinessDelegate {

	private static Logger logger = Logger.getLogger(ClienteDelegate.class);
	
	/**
	 * Salvar Cliente
	 * @throws BusinessDelegateException
	 */
	public void salvar(Cliente cliente) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		try {
			this.getFacade().salvar(cliente);
			//this.getFacade().salvarCMT(cliente);
			//this.getFacadeLocal().salvar(cliente);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Cliente", sfe);
		}
	}
	
	/**
	 * Consulta de Clientes
	 * @throws BusinessDelegateException
	 */
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		PaginaTO retorno = null;
		try {
			retorno = this.getFacade().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro consultando Cliente", sfe);
		}
		return retorno;
	}
	
	/**
	 * Obtem stub do session façade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private ClienteSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (ClienteSessionFacadeRemote) ServiceLocator.getInstance().getHome(ClienteSessionFacade.class);
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
	
	/**
	 * Obtem stub do session façade Local
	 * @return
	 * @throws BusinessDelegateException
	 */
	private ClienteSessionFacadeLocal getFacadeLocal() throws BusinessDelegateException{
		try {
			return (ClienteSessionFacadeLocal) ServiceLocator.getInstance().getLocalHome(ClienteSessionFacade.class);
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
