package org.jpericia.pericia.bussinessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.common.sessionfacade.pericia.AnexoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.pericia.views.listeners.AnexoListener;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class AnexoDelegate extends AbstractBusinessDelegate {
	
	//private static Logger logger = Logger.getLogger(AnexoDelegate.class);
	
	private static AnexoDelegate me;
	
	public AnexoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new AnexoDelegate();
		}
	}
	
	public static AnexoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void inserir(Anexo anexo) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().inserir(anexo);
			//logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Atualizar Anexo
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Anexo anexo) throws BusinessDelegateException {
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(anexo);
			// logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao",
					sfe);
		}
	}	

	/**
	 * Remover Anexo
	 * @throws BusinessDelegateException
	 */
	public void remover(Anexo anexo) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try {
			this.getFacade().remover(anexo);
			//logger.debug("Fim " + this.getClass().getName() + ".remover()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}	
	
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new AnexoListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Anexo", sfe);
		}
		
		return returnValue;
	}
		
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private AnexoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (AnexoSessionFacadeRemote) ServiceLocator.getInstance().getHome(AnexoSessionFacadeRemote.class, "AnexoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
