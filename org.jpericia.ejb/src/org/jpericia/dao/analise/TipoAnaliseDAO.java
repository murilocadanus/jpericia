package org.jpericia.dao.analise;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface TipoAnaliseDAO {
	
	public void inserir(TipoAnalise tipoAnalise) throws DAOException;
	
	public void atualizar(TipoAnalise tipoAnalise) throws DAOException;
	
	public void remover(TipoAnalise tipoAnalise) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
