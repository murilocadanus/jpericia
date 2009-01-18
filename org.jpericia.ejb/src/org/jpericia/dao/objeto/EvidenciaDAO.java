package org.jpericia.dao.objeto;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface EvidenciaDAO {
	
	public void inserir(Evidencia evidencia) throws DAOException;
	
	public void atualizar(Evidencia evidencia) throws DAOException;
	
	public void remover(Evidencia evidencia) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;

}
