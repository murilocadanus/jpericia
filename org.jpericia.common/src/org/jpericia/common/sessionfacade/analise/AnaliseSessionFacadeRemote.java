package org.jpericia.common.sessionfacade.analise;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;

@Remote
public interface AnaliseSessionFacadeRemote
{

	public void inserir(Analise analise) throws SessionFacadeException;

	public void atualizar(Analise analise) throws SessionFacadeException;

	public void remover(Analise analise) throws SessionFacadeException;

	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;

}
