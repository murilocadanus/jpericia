package org.jpericia.analise.businessdelegate;

import java.util.ArrayList;

import org.jpericia.analise.views.listeners.AnaliseListener;
import org.jpericia.analise.views.listeners.CenaListener;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.common.sessionfacade.analise.AnaliseSessionFacadeRemote;
import org.jpericia.common.sessionfacade.analise.CenaSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class CenaDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(CenaDelegate.class);
	
	private static CenaDelegate me;
	
	public CenaDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new CenaDelegate();
		}
	}
	
	public static CenaDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Atualizar Cena
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Cena cena)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(cena);
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
	 * Remover Cena
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(Cena cena)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(cena);
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
			AbstractResultList returnValue = new CenaListener();
			
			try 
			{
				retorno = this.getFacade().pesquisar();
				returnValue.addAll(retorno);	
			} 
			catch (SessionFacadeException sfe) 
			{
				throw new BusinessDelegateException("Erro consultando cena", sfe);
			}
			
			return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private CenaSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (CenaSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(CenaSessionFacade.class);
			return (CenaSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							CenaSessionFacadeRemote.class,
							"CenaSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
