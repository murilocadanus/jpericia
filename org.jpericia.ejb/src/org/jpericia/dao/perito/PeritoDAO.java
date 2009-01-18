package org.jpericia.dao.perito;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface PeritoDAO {
	
	public void inserir(Perito Perito) throws DAOException;
	
	public void atualizar(Perito Perito) throws DAOException;
	
	public void remover(Perito Perito) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public Perito autenticar(Perito perito) throws DAOException;

}
