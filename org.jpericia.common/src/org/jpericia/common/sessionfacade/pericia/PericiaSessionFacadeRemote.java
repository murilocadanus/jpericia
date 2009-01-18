package org.jpericia.common.sessionfacade.pericia;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


/**
 * @author Marlus Cadanus da Costa
 */

@Remote
public interface PericiaSessionFacadeRemote {
	
	public void inserir(Pericia pericia) throws SessionFacadeException;
	
	public void atualizar(Pericia pericia) throws SessionFacadeException;
	
	public void remover(Pericia pericia) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
