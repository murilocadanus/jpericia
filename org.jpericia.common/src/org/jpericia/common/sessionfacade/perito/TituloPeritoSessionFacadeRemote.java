package org.jpericia.common.sessionfacade.perito;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


@Remote
public interface TituloPeritoSessionFacadeRemote {
	
	public void inserir(TituloPerito tituloPerito) throws SessionFacadeException;
	
	public void atualizar(TituloPerito tituloPerito) throws SessionFacadeException;
	
	public void remover(TituloPerito tituloPerito) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
