package org.jpericia.businessdelegate.organizacao;

import org.apache.log4j.Logger;
import org.jpericia.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.sessionfacade.organizacao.OrganizacaoSessionFacadeRemote;
import org.jpericia.exception.BusinessDelegateException;
import org.jpericia.exception.ServiceLocatorException;
import org.jpericia.util.ServiceLocator;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class OrganizacaoDelegate extends AbstractBusinessDelegate {

	private static Logger logger = Logger.getLogger(OrganizacaoDelegate.class);
	
	/**
	 * Salvar Organizacao
	 * @throws BusinessDelegateException
	 */
	public void inserir(Organizacao organizacao) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		try {
			this.getFacade().inserir(organizacao);
			//this.getFacade().salvarCMT(organizacao);
			//this.getFacadeLocal().salvar(organizacao);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando Organizacao", sfe);
		}
	}
	
	/**
	 * Consulta de Organizacoes
	 * @throws BusinessDelegateException
	 */
/*	public PaginaTO consultar(CriterioPesquisaTO criterios) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		PaginaTO retorno = null;
		try {
			retorno = this.getFacade().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro consultando Organizacao", sfe);
		}
		return retorno;
	}*/
	
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private OrganizacaoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (OrganizacaoSessionFacadeRemote) ServiceLocator.getInstance().getHome(OrganizacaoSessionFacadeRemote.class, "OrganizacaoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
