package com.vbkn.titan.sessionfacade.cliente;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import com.vbkn.titan.applicationservice.cliente.ClienteAS;
import com.vbkn.titan.ejb.exception.ApplicationServiceException;
import com.vbkn.titan.ejb.exception.SessionFacadeException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.sessionfacade.AbstractSessionFacade;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 * Classe base para todos as classes que implementam o padrão
 * J2EE Session Facade
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ClienteSessionFacade extends AbstractSessionFacade implements ClienteSessionFacadeRemote {
	
	private static Logger logger = Logger.getLogger(ClienteSessionFacade.class);
	
	@TransactionAttribute(NEVER)
	public void salvar(Cliente cliente) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ClienteAS.getInstance().salvar(cliente);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	@TransactionAttribute(REQUIRES_NEW)
	public void salvarCMT(Cliente cliente) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ClienteAS.getInstance().salvarCMT(cliente);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}

	@Override
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException {
		PaginaTO retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			retorno = ClienteAS.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new SessionFacadeException("Erro consultando Cliente", ase);
		}
		return retorno;
	}
	
	

}
