package org.jpericia.perito.businessdelegate;

import java.util.ArrayList;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.common.sessionfacade.perito.FuncaoPeritoSessionFacadeRemote;
import org.jpericia.common.sessionfacade.perito.PeritoSessionFacadeRemote;
import org.jpericia.core.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.exception.ServiceLocatorException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.util.ServiceLocator;
import org.jpericia.perito.views.listeners.PeritoListener;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class PeritoDelegate extends AbstractBusinessDelegate {
	
	//private static Logger logger = Logger.getLogger(FuncaoPeritoDelegate.class);
	
	private static PeritoDelegate me;
	
	public PeritoDelegate()
	{
	}
	
	private static synchronized void loadInstance()
	{
		if(me == null)
		{
			me = new PeritoDelegate();
		}
	}
	
	public static PeritoDelegate getInstance()
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
	public void inserir(Perito perito) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try {
			this.getFacade().inserir(perito);
			//logger.debug("Fim " + this.getClass().getName() + ".inserir()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Atualizar FuncaoPerito
	 * @throws BusinessDelegateException
	 */
	public void atualizar(Perito perito) throws BusinessDelegateException {
		// logger.debug("Inicio " + this.getClass().getName() + ".inserir()");
		try
		{
			this.getFacade().atualizar(perito);
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
	 * @throws BusinessDelegateException
	 */
	public void remover(Perito perito) throws BusinessDelegateException {
		//logger.debug("Inicio " + this.getClass().getName() + ".remover()");
		try {
			this.getFacade().remover(perito);
			//logger.debug("Fim " + this.getClass().getName() + ".remover()");
		} catch (SessionFacadeException sfe) {
			//logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
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

/*		TituloPerito tituloPerito = new TituloPerito();
		tituloPerito.setCodigo(new Long(1));
		tituloPerito.setTitulo("Dr.");
		
		TituloPerito tituloPerito2 = new TituloPerito();
		tituloPerito2.setCodigo(new Long(1));
		tituloPerito2.setTitulo("Mr.");
		
		FuncaoPerito funcaoPerito = new FuncaoPerito();
		funcaoPerito.setCodigo(new Long(1));
		funcaoPerito.setFuncao("Físico");
		
		FuncaoPerito funcaoPerito2 = new FuncaoPerito();
		funcaoPerito2.setCodigo(new Long(2));
		funcaoPerito2.setFuncao("Químico");
		
		Perito p1 = new Perito();
		p1.setCodigo(new Long(1));
		p1.setUsuario("murilo");
		p1.setNome("Murilo");
		p1.setTituloPerito(tituloPerito);
		p1.setFuncaoPerito(funcaoPerito);
		p1.setSenha("qwe123");
		
		p1.setNome("Murilo Cadanus da Costa");
		p1.setTelefone(new Integer(1));
		p1.setCidade("Curitiba");
		p1.setBairro("São Loureço");
		p1.setEndereco("Nilo Peçanha");
		p1.setNumero(new Integer(1));
		p1.setLogradouro("Rua");
		
		Perito p2 = new Perito();
		p2.setCodigo(new Long(2));
		p2.setUsuario("marlus");
		p2.setNome("Marlus");
		p2.setTituloPerito(tituloPerito2);
		p2.setFuncaoPerito(funcaoPerito2);
		p2.setSenha("123qwe");
		
		p2.setNome("Marlus Cadanus da Costa");
		p2.setTelefone(new Integer(1));
		p2.setCelular(new Integer(1));
		p2.setCidade("Curitiba");
		p2.setBairro("São Loureço");
		p2.setEndereco("Nilo Peçanha");
		p2.setNumero(new Integer(1));
		p2.setLogradouro("Rua");*/
		
		//AbstractResultList returnValue = new PeritoListener();

		//returnValue.add(p1);
		//returnValue.add(p2);
		
		ArrayList<AbstractEntity> retorno = null;
		AbstractResultList returnValue = new PeritoListener();
		
		try 
		{
			retorno = this.getFacade().pesquisar();
			returnValue.addAll(retorno);	
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Perito", sfe);
		}
		
		return returnValue;
	}
	
	public Perito autenticar(Perito perito) throws BusinessDelegateException
	{
		try 
		{
			Perito peritoValido = this.getFacade().autenticar(perito);
			return peritoValido;
		} 
		catch (SessionFacadeException sfe) 
		{
			throw new BusinessDelegateException("Erro consultando Perito", sfe);
		}		
	}
		
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private PeritoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (PeritoSessionFacadeRemote) ServiceLocator.getInstance().getHome(PeritoSessionFacadeRemote.class, "PeritoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
