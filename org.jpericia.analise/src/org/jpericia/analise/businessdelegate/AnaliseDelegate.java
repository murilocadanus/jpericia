package org.jpericia.analise.businessdelegate;

import java.util.ArrayList;

import org.jpericia.analise.views.listeners.AnaliseListener;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.sessionfacade.analise.AnaliseSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class AnaliseDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(AnaliseDelegate.class);
	
	private static AnaliseDelegate me;
	
	public AnaliseDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new AnaliseDelegate();
		}
	}
	
	public static AnaliseDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir Analise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(Analise analise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(analise);
			// logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Analise",
					sfe);
		}
	}

	/**
	 * Atualizar Analise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Analise analise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(analise);
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
	 * Remover Analise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(Analise analise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(analise);
			// logger.debug("Fim " + this.getClass().getName() + ".remover()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao",
					sfe);
		}
	}

	/**
	 * Consulta
	 * 
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
			
			ArrayList<AbstractEntity> retorno = null;
			AbstractResultList returnValue = new AnaliseListener();
			
			try 
			{
				retorno = this.getFacade().pesquisar();
				returnValue.addAll(retorno);	
			} 
			catch (SessionFacadeException sfe) 
			{
				throw new BusinessDelegateException("Erro consultando analise", sfe);
			}
			
			return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private AnaliseSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (AnaliseSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(AnaliseSessionFacade.class);
			return (AnaliseSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							AnaliseSessionFacadeRemote.class,
							"AnaliseSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
