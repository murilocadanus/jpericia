package org.jpericia.common.sessionfacade.pericia;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.pericia.Laudo;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


/**
 * @author Marlus Cadanus da Costa
 */

@Remote
public interface LaudoSessionFacadeRemote {
	
	public void inserir(Laudo pericia) throws SessionFacadeException;
	
	public void atualizar(Laudo pericia) throws SessionFacadeException;
	
	public void remover(Laudo pericia) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
