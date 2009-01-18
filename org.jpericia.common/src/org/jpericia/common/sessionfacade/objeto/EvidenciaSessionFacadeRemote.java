package org.jpericia.common.sessionfacade.objeto;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


@Remote
public interface EvidenciaSessionFacadeRemote {
	
	public void inserir(Evidencia evidencia) throws SessionFacadeException;
	
	public void atualizar(Evidencia evidencia) throws SessionFacadeException;
	
	public void remover(Evidencia evidencia) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
