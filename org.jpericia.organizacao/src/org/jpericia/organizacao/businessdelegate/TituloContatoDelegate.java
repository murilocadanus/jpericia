package org.jpericia.organizacao.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.common.sessionfacade.organizacao.TituloContatoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.organizacao.views.listeners.TituloContatoListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TituloContatoDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(TituloContatoDelegate.class);
	
	private static TituloContatoDelegate me;
	
	public TituloContatoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new TituloContatoDelegate();
		}
	}
	
	public static TituloContatoDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir TituloContato
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(TituloContato tituloContato)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(tituloContato);
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
	 * Atualizar TituloContato
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(TituloContato tituloContato)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(tituloContato);
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
	 * Remover TituloContato
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(TituloContato tituloContato)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(tituloContato);
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
		AbstractResultList returnValue = new TituloContatoListener();

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
	private TituloContatoSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			// return (TituloContatoSessionFacadeRemote)
			// ServiceLocator.getInstance().getHome(TituloContatoSessionFacade.class);
			return (TituloContatoSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							TituloContatoSessionFacadeRemote.class,
							"TituloContatoSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
