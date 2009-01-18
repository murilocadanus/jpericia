package com.vbkn.titan.dao.cliente;

import com.vbkn.titan.ejb.exception.DAOException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 * 
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface ClienteDAO {
	
	public void salvar(Cliente cliente) throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
