package org.jpericia.common.sessionfacade.perito;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;


@Remote
public interface PeritoSessionFacadeRemote {
	
	public void inserir(Perito Perito) throws SessionFacadeException;
	
	public void atualizar(Perito Perito) throws SessionFacadeException;
	
	public void remover(Perito Perito) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;
	
	public Perito autenticar(Perito perito) throws SessionFacadeException;

}
