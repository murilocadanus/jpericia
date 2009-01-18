package org.jpericia.organizacao.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.common.sessionfacade.organizacao.TipoOrganizacaoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.organizacao.views.listeners.TipoOrganizacaoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TipoOrganizacaoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(TipoOrganizacaoDelegate.class);
	
	private static TipoOrganizacaoDelegate me;
	
	public TipoOrganizacaoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new TipoOrganizacaoDelegate();
		}
	}
	
	public static TipoOrganizacaoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir TipoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(TipoOrganizacao tipoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(tipoOrganizacao);
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
	 * Atualizar TipoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(TipoOrganizacao tipoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(tipoOrganizacao);
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
	 * Remover TipoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(TipoOrganizacao tipoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(tipoOrganizacao);
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
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new TipoOrganizacaoListener();

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
	private TipoOrganizacaoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (TipoOrganizacaoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(TipoOrganizacaoSessionFacade.class);
			return (TipoOrganizacaoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							TipoOrganizacaoSessionFacadeRemote.class,
							"TipoOrganizacaoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
