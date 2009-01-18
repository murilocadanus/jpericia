package org.jpericia.sessionfacade.analise;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.analise.AnaliseAS;
import org.jpericia.applicationservice.analise.CenaAS;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.common.sessionfacade.analise.CenaSessionFacadeRemote;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.sessionfacade.AbstractSessionFacade;


/**
 * @author Marlus Cadanus da Costa
 * Classe base para todos as classes que implementam o padrão
 * J2EE Session Facade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CenaSessionFacade extends AbstractSessionFacade implements CenaSessionFacadeRemote {
	
	private static Logger logger = Logger.getLogger(CenaSessionFacade.class);
	
	@TransactionAttribute(NEVER)
	public void atualizar(Cena cena) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaAS.getInstance().atualizar(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	@TransactionAttribute(REQUIRES_NEW)
	public void inserir(Cena cena) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaAS.getInstance().inserir(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public void remover(Cena cena) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			CenaAS.getInstance().remover(cena);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = CenaAS.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro pesquisar", ase);
		}
	}

	public ArrayList<AbstractEntity> pesquisarAnalise()
			throws SessionFacadeException
	{
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = AnaliseAS.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro salvando Cliente", ase);
		}
	}
}
