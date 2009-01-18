package org.jpericia.sessionfacade.perito;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.perito.PeritoAS;
import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.common.sessionfacade.perito.PeritoSessionFacadeRemote;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.sessionfacade.AbstractSessionFacade;


/**
 * @author Marlus Cadanus da Costa
 * Classe base para todos as classes que implementam o padrão
 * J2EE Session Facade
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PeritoSessionFacade extends AbstractSessionFacade implements PeritoSessionFacadeRemote {
	
	private static Logger logger = Logger.getLogger(PeritoSessionFacade.class);
	
	@TransactionAttribute(NEVER)
	public void atualizar(Perito Perito) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoAS.getInstance().atualizar(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro atualizar", ase);
		}
	}
	
	@TransactionAttribute(REQUIRES_NEW)
	public void inserir(Perito Perito) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoAS.getInstance().inserir(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro inserir", ase);
		}
	}
	
	public void remover(Perito Perito) throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			PeritoAS.getInstance().remover(Perito);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro remover", ase);
		}
	}
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException {
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = PeritoAS.getInstance().pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".salvar()");
			throw new SessionFacadeException("Erro pesquisar", ase);
		}
	}

	//@Override
	public Perito autenticar(Perito perito) throws SessionFacadeException {
		Perito retorno = null;
		try {
			logger.debug("Inicio " + this.getClass().getName() + ".autenticar()");
			retorno = PeritoAS.getInstance().autenticar(perito);
			logger.debug("Fim " + this.getClass().getName() + ".autenticar()");
		} catch (ApplicationServiceException ase) {
			logger.error(this.getClass().getName() + ".consultar()");
			throw new SessionFacadeException("Erro autenticar", ase);
		}
		return retorno;
	}

}
