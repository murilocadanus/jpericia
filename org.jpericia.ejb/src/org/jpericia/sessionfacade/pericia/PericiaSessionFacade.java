package org.jpericia.sessionfacade.pericia;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.organizacao.OrganizacaoAS;
import org.jpericia.applicationservice.pericia.PericiaAS;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.sessionfacade.pericia.PericiaSessionFacadeRemote;
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
public class PericiaSessionFacade extends AbstractSessionFacade implements PericiaSessionFacadeRemote {
	
	private static Logger logger = Logger.getLogger(PericiaSessionFacade.class);
	
	@TransactionAttribute(NEVER)
	public void inserir(Pericia pericia) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaAS.getInstance().inserir(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	@TransactionAttribute(REQUIRES_NEW)
	public void atualizar(Pericia pericia) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaAS.getInstance().atualizar(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public void remover(Pericia pericia) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PericiaAS.getInstance().remover(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = PericiaAS.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}

	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnalises()");
			ArrayList<AbstractEntity> pesquisa = PericiaAS.getInstance().pesquisarAnalises(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".pesquisarAnalises()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}	

	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarObjetos()");
			ArrayList<AbstractEntity> pesquisa = PericiaAS.getInstance().pesquisarEvidencias(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnalises()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".pesquisarObjetos()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}	
	
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarAnexos()");
			ArrayList<AbstractEntity> pesquisa = PericiaAS.getInstance().pesquisarAnexos(pericia);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarAnexos()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".pesquisarAnexos()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}	

	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".pesquisarCenas()");
			ArrayList<AbstractEntity> pesquisa = PericiaAS.getInstance().pesquisarCenas(analise);
			logger.debug("Fim " + this.getClass().getName() + ".pesquisarCenas()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".pesquisarCenas()");
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
