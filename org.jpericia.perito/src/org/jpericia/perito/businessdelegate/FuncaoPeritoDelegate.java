package org.jpericia.perito.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.sessionfacade.perito.FuncaoPeritoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.perito.views.listeners.FuncaoPeritoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class FuncaoPeritoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(FuncaoPeritoDelegate.class);
	
	private static FuncaoPeritoDelegate me;
	
	public FuncaoPeritoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new FuncaoPeritoDelegate();
		}
	}
	
	public static FuncaoPeritoDelegate getInstance()
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
	public void inserir(FuncaoPerito funcaoPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(funcaoPerito);
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
	public void atualizar(FuncaoPerito funcaoPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(funcaoPerito);
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
	public void remover(FuncaoPerito funcaoPerito)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(funcaoPerito);
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
	/*
	 * public ArrayList consultar() throws BusinessDelegateException {
	 * //logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
	 * PaginaTO retorno = null; try { retorno = this.getFacade().consultar();
	 * //logger.debug("Fim " + this.getClass().getName() + ".salvar()"); } catch
	 * (SessionFacadeException sfe) { //logger.error(this.getClass().getName() +
	 * ".teste()"); throw new BusinessDelegateException("Erro consultando
	 * Organizacao", sfe); } return retorno; }
	 */

	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
/*		FuncaoPerito funcaoPerito = new FuncaoPerito();
		funcaoPerito.setCodigo(new Long(1));
		funcaoPerito.setFuncao("Físico");
		
		FuncaoPerito funcaoPerito2 = new FuncaoPerito();
		funcaoPerito2.setCodigo(new Long(2));
		funcaoPerito2.setFuncao("Químico");
		
		AbstractResultList returnValue = new FuncaoPeritoListener();
		returnValue.add(funcaoPerito);
		returnValue.add(funcaoPerito2);
		
		return returnValue;*/
		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new FuncaoPeritoListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando a Funcao Perito", sfe);
		}
		
		return returnValue;
		
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private FuncaoPeritoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (FuncaoPeritoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(FuncaoPeritoSessionFacade.class);
			return (FuncaoPeritoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							FuncaoPeritoSessionFacadeRemote.class,
							"FuncaoPeritoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
