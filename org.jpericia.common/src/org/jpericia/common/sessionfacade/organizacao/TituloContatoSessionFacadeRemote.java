package org.jpericia.common.sessionfacade.organizacao;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


/**
 * @author Marlus Cadanus da Costa
 */

@Remote
public interface TituloContatoSessionFacadeRemote {
	
	public void inserir(TituloContato tituloContato) throws SessionFacadeException;
	
	public void atualizar(TituloContato tituloContato) throws SessionFacadeException;
	
	public void remover(TituloContato tituloContato) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
