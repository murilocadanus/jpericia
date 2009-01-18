package org.jpericia.objeto.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.common.sessionfacade.objeto.TipoObjetoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.objeto.views.listeners.TipoObjetoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TipoObjetoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(TipoObjetoDelegate.class);
	
	private static TipoObjetoDelegate me;
	
	public TipoObjetoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new TipoObjetoDelegate();
		}
	}
	
	public static TipoObjetoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir TipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(TipoObjeto tipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(tipoObjeto);
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
	 * Atualizar TipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(TipoObjeto tipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(tipoObjeto);
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
	 * Remover TipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(TipoObjeto tipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(tipoObjeto);
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
		AbstractResultList returnValue = new TipoObjetoListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando a Categoria tipo objeto", sfe);
		}
		
		return returnValue;
		
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private TipoObjetoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			return (TipoObjetoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							TipoObjetoSessionFacadeRemote.class,
							"TipoObjetoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
