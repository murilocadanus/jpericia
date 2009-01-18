package org.jpericia.objeto.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.sessionfacade.objeto.CategoriaTipoObjetoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.objeto.views.listeners.CategoriaTipoObjetoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class CategoriaTipoObjetoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(CategoriaTipoObjetoDelegate.class);
	
	private static CategoriaTipoObjetoDelegate me;
	
	public CategoriaTipoObjetoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new CategoriaTipoObjetoDelegate();
		}
	}
	
	public static CategoriaTipoObjetoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir CategoriaTipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(CategoriaTipoObjeto categoriaTipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(categoriaTipoObjeto);
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
	 * Atualizar CategoriaTipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(CategoriaTipoObjeto categoriaTipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(categoriaTipoObjeto);
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
	 * Remover CategoriaTipoObjeto
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(CategoriaTipoObjeto categoriaTipoObjeto)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(categoriaTipoObjeto);
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
		AbstractResultList returnValue = new CategoriaTipoObjetoListener();
		
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
	private CategoriaTipoObjetoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			return (CategoriaTipoObjetoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							CategoriaTipoObjetoSessionFacadeRemote.class,
							"CategoriaTipoObjetoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
