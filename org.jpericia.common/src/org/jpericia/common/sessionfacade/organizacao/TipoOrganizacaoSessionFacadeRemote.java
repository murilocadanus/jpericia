package org.jpericia.common.sessionfacade.organizacao;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;


/**
 * @author Marlus Cadanus da Costa
 */

@Remote
public interface TipoOrganizacaoSessionFacadeRemote {
	
	public void inserir(TipoOrganizacao tipoOrganizacao) throws SessionFacadeException;
	
	public void atualizar(TipoOrganizacao tipoOrganizacao) throws SessionFacadeException;
	
	public void remover(TipoOrganizacao tipoOrganizacao) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
