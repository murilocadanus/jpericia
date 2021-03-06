package org.jpericia.dao.perito;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface FuncaoPeritoDAO {
	
	public void inserir(FuncaoPerito funcaoPerito) throws DAOException;
	
	public void atualizar(FuncaoPerito funcaoPerito) throws DAOException;
	
	public void remover(FuncaoPerito funcaoPerito) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
