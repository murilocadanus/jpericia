package org.jpericia.common.sessionfacade.analise;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;

@Remote
public interface CenaSessionFacadeRemote
{

	public void inserir(Cena cena) throws SessionFacadeException;

	public void atualizar(Cena cena) throws SessionFacadeException;

	public void remover(Cena cena) throws SessionFacadeException;

	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisarAnalise() throws SessionFacadeException;

}
