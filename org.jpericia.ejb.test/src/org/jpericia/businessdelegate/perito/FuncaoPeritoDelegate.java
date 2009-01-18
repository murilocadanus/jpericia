package org.jpericia.businessdelegate.perito;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.sessionfacade.perito.FuncaoPeritoSessionFacadeRemote;
import org.jpericia.exception.BusinessDelegateException;
import org.jpericia.exception.ServiceLocatorException;
import org.jpericia.util.ServiceLocator;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class FuncaoPeritoDelegate extends AbstractBusinessDelegate {

	private static Logger logger = Logger.getLogger(FuncaoPeritoDelegate.class);
	
	/**
	 * Inserir FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void inserir(FuncaoPerito funcaoPerito) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().inserir(funcaoPerito);
			logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Atualizar FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void atualizar(FuncaoPerito funcaoPerito) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().atualizar(funcaoPerito);
			logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}	

	/**
	 * Remover FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void remover(FuncaoPerito funcaoPerito) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try {
			this.getFacade().remover(funcaoPerito);
			logger.debug("Fim " + this.getClass().getName() + ".remover()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}	
	
	/**
	 * Consulta de Organizacoes
	 * @throws BusinessDelegateException
	 */
	public ArrayList<AbstractEntity> pesquisar() throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		ArrayList<AbstractEntity> retorno = null;
		try {
			retorno = this.getFacade().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro consultando Organizacao", sfe);
		}
		return retorno;
	}
	
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private FuncaoPeritoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (FuncaoPeritoSessionFacadeRemote) ServiceLocator.getInstance().getHome(FuncaoPeritoSessionFacadeRemote.class, "FuncaoPeritoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
