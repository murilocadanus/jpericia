package org.jpericia.analise.businessdelegate;

import java.util.ArrayList;

import org.jpericia.analise.views.listeners.TipoAnaliseListener;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.common.sessionfacade.analise.TipoAnaliseSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TipoAnaliseDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(TipoAnaliseDelegate.class);
	
	private static TipoAnaliseDelegate me;
	
	public TipoAnaliseDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new TipoAnaliseDelegate();
		}
	}
	
	public static TipoAnaliseDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir TipoAnalise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(TipoAnalise tipoAnalise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(tipoAnalise);
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
	 * Atualizar TipoAnalise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(TipoAnalise tipoAnalise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(tipoAnalise);
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
	 * Remover TipoAnalise
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(TipoAnalise tipoAnalise)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(tipoAnalise);
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
			AbstractResultList returnValue = new TipoAnaliseListener();
			
			try 
			{
				retorno = this.getFacade().pesquisar();
				returnValue.addAll(retorno);	
			} 
			catch (SessionFacadeException sfe) 
			{
				throw new BusinessDelegateException("Erro consultando o Tipo analise", sfe);
			}
			
			return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private TipoAnaliseSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (TipoAnaliseSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(TipoAnaliseSessionFacade.class);
			return (TipoAnaliseSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							TipoAnaliseSessionFacadeRemote.class,
							"TipoAnaliseSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
