package org.jpericia.common.sessionfacade.analise;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;

@Remote
public interface TipoAnaliseSessionFacadeRemote
{

	public void inserir(TipoAnalise tipoAnalise) throws SessionFacadeException;

	public void atualizar(TipoAnalise tipoAnalise)
			throws SessionFacadeException;

	public void remover(TipoAnalise tipoAnalise) throws SessionFacadeException;

	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;

	public PaginaTO consultar(CriterioPesquisaTO criterios)
			throws SessionFacadeException;

}
