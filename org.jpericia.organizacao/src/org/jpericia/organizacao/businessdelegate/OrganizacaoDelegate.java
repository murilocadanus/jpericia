package org.jpericia.organizacao.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.sessionfacade.organizacao.OrganizacaoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.organizacao.views.listeners.OrganizacaoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class OrganizacaoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(OrganizacaoDelegate.class);
	
	private static OrganizacaoDelegate me;
	
	public OrganizacaoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new OrganizacaoDelegate();
		}
	}
	
	public static OrganizacaoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir Organizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(Organizacao organizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(organizacao);
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
	 * Atualizar Organizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Organizacao organizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(organizacao);
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
	 * Remover Organizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(Organizacao organizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(organizacao);
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
		AbstractResultList returnValue = new OrganizacaoListener();

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
	private OrganizacaoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (OrganizacaoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(OrganizacaoSessionFacade.class);
			return (OrganizacaoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							OrganizacaoSessionFacadeRemote.class,
							"OrganizacaoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
