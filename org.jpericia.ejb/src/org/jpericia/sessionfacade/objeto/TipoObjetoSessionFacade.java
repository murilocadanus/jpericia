package org.jpericia.sessionfacade.objeto;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.objeto.TipoObjetoAS;
import org.jpericia.applicationservice.organizacao.OrganizacaoAS;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.common.sessionfacade.objeto.TipoObjetoSessionFacadeRemote;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.sessionfacade.AbstractSessionFacade;


/**
 * @author Marlus Cadanus da Costa
 * Classe base para todos as classes que implementam o padrão
 * J2EE Session Facade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TipoObjetoSessionFacade extends AbstractSessionFacade implements TipoObjetoSessionFacadeRemote {
	
	private static Logger logger = Logger.getLogger(TipoObjetoSessionFacade.class);
	
	@TransactionAttribute(NEVER)
	public void atualizar(TipoObjeto tipoObjeto) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoAS.getInstance().atualizar(tipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	@TransactionAttribute(REQUIRES_NEW)
	public void inserir(TipoObjeto tipoObjeto) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoAS.getInstance().inserir(tipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public void remover(TipoObjeto tipoObjeto) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TipoObjetoAS.getInstance().remover(tipoObjeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = TipoObjetoAS.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}

	//@Override
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException {
		PaginaTO retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			retorno = OrganizacaoAS.getInstance().consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new SessionFacadeException("Erro consultando Cliente", ase);
		}
		return retorno;
	}
	
	

}
