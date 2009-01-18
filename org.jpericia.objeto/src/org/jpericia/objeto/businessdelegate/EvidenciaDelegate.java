package org.jpericia.objeto.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.common.sessionfacade.objeto.EvidenciaSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.objeto.views.listeners.EvidenciaListener;

/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class EvidenciaDelegate extends AbstractBusinessDelegate
{

	// private static Logger logger =
	// Logger.getLogger(EvidenciaDelegate.class);
	
	private static EvidenciaDelegate me;
	
	public EvidenciaDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new EvidenciaDelegate();
		}
	}
	
	public static EvidenciaDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir Evidencia
	 * 
	 * @throws BusinessDelegateException
	 */
	public void inserir(Evidencia evidencia)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().inserir(evidencia);
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
	 * Atualizar Evidencia
	 * 
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Evidencia evidencia)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(evidencia);
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
	 * Remover Evidencia
	 * 
	 * @throws BusinessDelegateException
	 */
	public void remover(Evidencia evidencia)
			throws BusinessDelegateException
	{
		// logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try
		{
			this.getFacade().remover(evidencia);
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
		AbstractResultList returnValue = new EvidenciaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando a evidencia", sfe);
		}
		
		return returnValue;
		
	}

	/**
	 * Obtem stub do session facade
	 * 
	 * @return
	 * @throws BusinessDelegateException
	 */
	private EvidenciaSessionFacadeRemote getFacade()
			throws BusinessDelegateException
	{
		try
		{
			return (EvidenciaSessionFacadeRemote) ServiceLocator
					.getInstance().getHome(
							EvidenciaSessionFacadeRemote.class,
							"EvidenciaSessionFacade");
		}
		catch (ServiceLocatorException e)
		{
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}

}
