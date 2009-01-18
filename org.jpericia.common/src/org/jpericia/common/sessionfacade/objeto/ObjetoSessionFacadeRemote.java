package org.jpericia.common.sessionfacade.objeto;

import java.util.ArrayList;

import javax.ejb.Remote;

import org.jpericia.common.ejb.exception.SessionFacadeException;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;


/**
 * @author Marlus Cadanus da Costa
 */

@Remote
public interface ObjetoSessionFacadeRemote {
	
	public void inserir(Objeto objeto) throws SessionFacadeException;
	
	public void atualizar(Objeto objeto) throws SessionFacadeException;
	
	public void remover(Objeto objeto) throws SessionFacadeException;
	
	public ArrayList<AbstractEntity> pesquisar() throws SessionFacadeException;

}
