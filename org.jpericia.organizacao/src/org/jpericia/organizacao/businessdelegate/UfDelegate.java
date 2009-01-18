package org.jpericia.organizacao.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.sessionfacade.generic.UfSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.organizacao.views.listeners.UfListener;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class UfDelegate extends AbstractBusinessDelegate {
	
	//private static Logger logger = Logger.getLogger(FuncaoPeritoDelegate.class);
	
	private static UfDelegate me;
	
	public UfDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new UfDelegate();
		}
	}
	
	public static UfDelegate getInstance()
	{
		if(me == null)
		{
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Consulta de Organizacoes
	 * @throws BusinessDelegateException
	 */
	/*public ArrayList consultar() throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		PaginaTO retorno = null;
		try {
			retorno = this.getFacade().consultar();
			//logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro consultando Organizacao", sfe);
		}
		return retorno;
	}*/
	
	public AbstractResultList pesquisar() throws BusinessDelegateException
	{		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new UfListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando uf", sfe);
		}
		
		return returnValue;
	}
		
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private UfSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (UfSessionFacadeRemote) ServiceLocator.getInstance().getHome(UfSessionFacadeRemote.class, "UfSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
