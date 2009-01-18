package org.jpericia.perito.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.common.sessionfacade.perito.FuncaoPeritoSessionFacadeRemote;
import org.jpericia.common.sessionfacade.perito.TituloPeritoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.perito.views.listeners.FuncaoPeritoListener;
import org.jpericia.perito.views.listeners.TituloPeritoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TituloPeritoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(FuncaoPeritoDelegate.class);
	
	private static TituloPeritoDelegate me;
	
	public TituloPeritoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new TituloPeritoDelegate();
		}
	}
	
	public static TituloPeritoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir FuncaoPerito
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(TituloPerito tituloPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(tituloPerito);
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
	 * Atualizar FuncaoPerito
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(TituloPerito tituloPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(tituloPerito);
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
	 * Remover FuncaoPerito
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(TituloPerito tituloPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(tituloPerito);
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
	 * Consulta de Organizacoes
	 * 
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
			
			ArrayList<AbstractEntity> retorno = null;
			AbstractResultList returnValue = new TituloPeritoListener();
			
			try 
			{
				retorno = this.getFacade().pesquisar();
				returnValue.addAll(retorno);	
			} 
			catch (SessionFacadeException sfe) 
			{
				throw new BusinessDelegateException("Erro consultando o Titulo Perito", sfe);
			}
			
			return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private TituloPeritoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (FuncaoPeritoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(FuncaoPeritoSessionFacade.class);
			return (TituloPeritoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							TituloPeritoSessionFacadeRemote.class,
							"TituloPeritoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
