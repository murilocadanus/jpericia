package org.jpericia.dao.objeto;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface CategoriaTipoObjetoDAO {
	
	public void inserir(CategoriaTipoObjeto tituloPerito) throws DAOException;
	
	public void atualizar(CategoriaTipoObjeto tituloPerito) throws DAOException;
	
	public void remover(CategoriaTipoObjeto categoriaTipoObjeto) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
