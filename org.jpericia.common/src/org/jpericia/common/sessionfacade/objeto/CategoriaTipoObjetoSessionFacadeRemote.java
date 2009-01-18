package org.jpericia.common.sessionfacade.objeto;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


@Remote
public interface CategoriaTipoObjetoSessionFacadeRemote {
	
	public void inserir(CategoriaTipoObjeto categoriaTipoObjeto) throws SessionFacadeException;
	
	public void atualizar(CategoriaTipoObjeto categoriaTipoObjeto) throws SessionFacadeException;
	
	public void remover(CategoriaTipoObjeto categoriaTipoObjeto) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
