package org.jpericia.dao.analise;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface CenaDAO {
	
	public void inserir(Cena cena) throws DAOException;
	
	public void atualizar(Cena cena) throws DAOException;
	
	public void remover(Cena cena) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;

}
