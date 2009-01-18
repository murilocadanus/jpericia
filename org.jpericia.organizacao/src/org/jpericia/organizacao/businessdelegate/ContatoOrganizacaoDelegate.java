package org.jpericia.organizacao.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.sessionfacade.organizacao.ContatoOrganizacaoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.organizacao.views.listeners.ContatoOrganizacaoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class ContatoOrganizacaoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(ContatoOrganizacaoDelegate.class);
	
	private static ContatoOrganizacaoDelegate me;
	
	public ContatoOrganizacaoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new ContatoOrganizacaoDelegate();
		}
	}
	
	public static ContatoOrganizacaoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir ContatoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(ContatoOrganizacao contatoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(contatoOrganizacao);
			// logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Contato Organizacao",
					sfe);
		}
	}

	/**
	 * Atualizar ContatoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(ContatoOrganizacao contatoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(contatoOrganizacao);
			// logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Contato Organizacao",
					sfe);
		}
	}

	/**
	 * Remover ContatoOrganizacao
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(ContatoOrganizacao contatoOrganizacao)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(contatoOrganizacao);
			// logger.debug("Fim " + this.getClass().getName() + ".remover()");
		}
		catch (SessionFacadeException sfe)
		{
			// logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Contato Organizacao",
					sfe);
		}
	}

	/**
	 * Consulta de Contato Organizacoes
	 * 
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new ContatoOrganizacaoListener();

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
	private ContatoOrganizacaoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (OrganizacaoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(OrganizacaoSessionFacade.class);
			return (ContatoOrganizacaoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							ContatoOrganizacaoSessionFacadeRemote.class,
							"ContatoOrganizacaoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
