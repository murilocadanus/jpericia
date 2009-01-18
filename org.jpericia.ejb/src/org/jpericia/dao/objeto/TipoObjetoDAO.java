package org.jpericia.dao.objeto;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;

/**
 * Classe que implementa o padrão J2EE Data Access Object
 */

public interface TipoObjetoDAO
{

	public void inserir(TipoObjeto tituloPerito) throws DAOException;

	public void atualizar(TipoObjeto tituloPerito) throws DAOException;

	public void remover(TipoObjeto tipoObjeto) throws DAOException;

	public ArrayList<AbstractEntity> pesquisar() throws DAOException;

	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
