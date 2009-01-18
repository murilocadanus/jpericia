package org.jpericia.businessdelegate.organizacao;

import org.apache.log4j.Logger;
import org.jpericia.businessdelegate.AbstractBusinessDelegate;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.common.sessionfacade.organizacao.TipoOrganizacaoSessionFacadeRemote;
import org.jpericia.exception.BusinessDelegateException;
import org.jpericia.exception.ServiceLocatorException;
import org.jpericia.util.ServiceLocator;


/**
 * @author Marlus Cadanus da Costa
 */

@SuppressWarnings("all")
public class TipoOrganizacaoDelegate extends AbstractBusinessDelegate {

	private static Logger logger = Logger.getLogger(TipoOrganizacaoDelegate.class);
	
	/**
	 * Salvar TipoOrganizacao
	 * @throws BusinessDelegateException
	 */
	public void inserir(TipoOrganizacao tipoOrganizacao) throws BusinessDelegateException {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		try {
			this.getFacade().inserir(tipoOrganizacao);
			//this.getFacade().salvarCMT(tipoOrganizacao);
			//this.getFacadeLocal().salvar(tipoOrganizacao);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (SessionFacadeException sfe) {
			logger.error(this.getClass().getName() + ".teste()");
			throw new BusinessDelegateException("Erro salvando TipoOrganizacao", sfe);
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
			throw new BusinessDelegateException("Erro consultando TipoOrganizacao", sfe);
		}
		return retorno;
	}*/
	
	/**
	 * Obtem stub do session facade
	 * @return
	 * @throws BusinessDelegateException
	 */
	private TipoOrganizacaoSessionFacadeRemote getFacade() throws BusinessDelegateException{
		try {
			return (TipoOrganizacaoSessionFacadeRemote) ServiceLocator.getInstance().getHome(TipoOrganizacaoSessionFacadeRemote.class, "TipoOrganizacaoSessionFacade");
		} catch (ServiceLocatorException e) {
			throw new BusinessDelegateException("Erro obtendo facade", e);
		}
	}
}
