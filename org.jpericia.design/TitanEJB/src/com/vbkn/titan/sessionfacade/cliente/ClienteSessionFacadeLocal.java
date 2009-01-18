package com.vbkn.titan.sessionfacade.cliente;

import javax.ejb.Local;

import com.vbkn.titan.ejb.exception.SessionFacadeException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */

@Local
public interface ClienteSessionFacadeLocal {
	
	public void salvar(Cliente cliente) throws SessionFacadeException;
	
	public void salvarCMT(Cliente cliente) throws SessionFacadeException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws SessionFacadeException;

}
