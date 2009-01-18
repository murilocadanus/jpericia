package org.jpericia.pericia.bussinessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.pericia.Laudo;
import org.jpericia.common.sessionfacade.pericia.LaudoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.pericia.views.listeners.LaudoListener;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class LaudoDelegate extends AbstractBusinessDelegate {
	
	//private static Logger logger = Logger.getLogger(FuncaoLaudoDelegate.class);
	
	private static LaudoDelegate me;
	
	public LaudoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new LaudoDelegate();
		}
	}
	
	public static LaudoDelegate getInstance()
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
	public void inserir(Laudo laudo) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().inserir(laudo);
			//logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Atualizar Laudo
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Laudo laudo) throws BusinessDelegateException {
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(laudo);
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
	 * Remover Laudo
	 * @throws BusinessDelegateException
	 */
	public void remover(Laudo laudo) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try {
			this.getFacade().remover(laudo);
			//logger.debug("Fim " + this.getClass().getName() + ".remover()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}	
	
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new LaudoListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Laudo", sfe);
		}
		
		return returnValue;
	}
		
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private LaudoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (LaudoSessionFacadeRemote) ServiceLocator.getInstance().getHome(LaudoSessionFacadeRemote.class, "LaudoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
