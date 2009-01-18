package org.jpericia.common.sessionfacade.perito;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


@Remote
public interface FuncaoPeritoSessionFacadeRemote {
	
	public void inserir(FuncaoPerito funcaoPerito) throws SessionFacadeException;
	
	public void atualizar(FuncaoPerito funcaoPerito) throws SessionFacadeException;
	
	public void remover(FuncaoPerito funcaoPerito) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
