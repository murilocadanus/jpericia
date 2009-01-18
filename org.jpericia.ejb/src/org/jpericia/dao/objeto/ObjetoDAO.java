package org.jpericia.dao.objeto;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface ObjetoDAO {
	
	public void inserir(Objeto objeto) throws DAOException;
	
	public void atualizar(Objeto objeto) throws DAOException;
	
	public void remover(Objeto objeto) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;

}
