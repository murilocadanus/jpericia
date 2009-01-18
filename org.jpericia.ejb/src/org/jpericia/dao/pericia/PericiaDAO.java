package org.jpericia.dao.pericia;

import java.util.ArrayList;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.ejb.exception.DAOException;


/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

public interface PericiaDAO {
	
	public void inserir(Pericia pericia) throws DAOException;
	
	public void atualizar(Pericia pericia) throws DAOException;
	
	public void remover(Pericia pericia) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws DAOException;
	
	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws DAOException;
	
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException;

}
