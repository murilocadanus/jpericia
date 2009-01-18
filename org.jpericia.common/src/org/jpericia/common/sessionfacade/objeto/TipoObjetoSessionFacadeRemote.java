package org.jpericia.common.sessionfacade.objeto;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


@Remote
public interface TipoObjetoSessionFacadeRemote {
	
	public void inserir(TipoObjeto tipoObjeto) throws SessionFacadeException;
	
	public void atualizar(TipoObjeto tipoObjeto) throws SessionFacadeException;
	
	public void remover(TipoObjeto tipoObjeto) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
