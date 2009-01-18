package org.jpericia.dao.organizacao;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface ContatoOrganizacaoDAO {
	
	public void inserir(ContatoOrganizacao contatoOrganizacao) throws DAOException;
	
	public void atualizar(ContatoOrganizacao contatoOrganizacao) throws DAOException;
	
	public void remover(ContatoOrganizacao contatoOrganizacao) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
