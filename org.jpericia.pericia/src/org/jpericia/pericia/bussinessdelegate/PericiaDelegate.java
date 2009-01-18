package org.jpericia.pericia.bussinessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.sessionfacade.pericia.PericiaSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.pericia.views.listeners.PericiaListener;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class PericiaDelegate extends AbstractBusinessDelegate {
	
	//private static Logger logger = Logger.getLogger(FuncaoPericiaDelegate.class);
	
	private static PericiaDelegate me;
	
	public PericiaDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new PericiaDelegate();
		}
	}
	
	public static PericiaDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Inserir FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void inserir(Pericia pericia) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().inserir(pericia);
			//logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Atualizar Pericia
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Pericia pericia) throws BusinessDelegateException {
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(pericia);
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
	 * Remover Pericia
	 * @throws BusinessDelegateException
	 */
	public void remover(Pericia pericia) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try {
			this.getFacade().remover(pericia);
			//logger.debug("Fim " + this.getClass().getName() + ".remover()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}	
	
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PericiaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Pericia", sfe);
		}	
		return returnValue;
	}
		
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisarAnalises(Pericia pericia) throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PericiaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisarAnalises(pericia);
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Pericia", sfe);
		}	
		return returnValue;
	}	

	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisarEvidencias(Objeto objeto) throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PericiaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisarEvidencias(objeto);
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Pericia", sfe);
		}	
		return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisarAnexos(Pericia pericia) throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PericiaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisarAnexos(pericia);
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Pericia", sfe);
		}	
		return returnValue;
	}

	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	public AbstractResultList pesquisarCenas(Analise analise) throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PericiaListener();
		
		try 
		{
			retorno = this.getFacade().pesquisarCenas(analise);
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Pericia", sfe);
		}	
		return returnValue;
	}	
	
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private PericiaSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (PericiaSessionFacadeRemote) ServiceLocator.getInstance().getHome(PericiaSessionFacadeRemote.class, "PericiaSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
