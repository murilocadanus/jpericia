package org.jpericia.dao.pericia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.util.to.CriterioPesquisaTO;
import org.jpericia.common.util.to.PaginaTO;
import org.jpericia.dao.AbstractDAO;
import org.jpericia.dao.PersistenceUtil;
import org.jpericia.ejb.exception.DAOException;


/**
 * Classe que implementa o padrão
 * J2EE Data Access Object
 */

@SuppressWarnings("unchecked")
public class PericiaDAOJPA extends AbstractDAO implements PericiaDAO {
	
	private static Logger logger = Logger.getLogger(PericiaDAOJPA.class);

	//@Override
	public void inserir(Pericia pericia) throws DAOException {
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			manager.persist(pericia);
			manager.flush();
		}catch(Exception e){
			throw new DAOException("Erro salvar Cliente", e);
		}
	}
	
	public void atualizar(Pericia pericia) throws DAOException {
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			Pericia periciaManaged = manager.merge(pericia);
			
			manager.persist(periciaManaged);
			manager.flush();
		}catch(Exception e){
			throw new DAOException("Erro salvar Cliente", e);
		}
	}
	
	public void remover(Pericia pericia) throws DAOException {
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			Pericia periciaManaged = manager.merge(pericia);
			
			manager.remove(periciaManaged);
			manager.flush();
		}catch(Exception e){
			throw new DAOException("Erro salvar Cliente", e);
		}
	}
	
	public ArrayList<AbstractEntity> pesquisar() throws DAOException {
		logger.debug("Entrou PericiaDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        queryStr.append("from Pericia p order by titulo");
	        Query query = manager.createQuery(queryStr.toString());
	        
	        ArrayList result = (ArrayList)query.getResultList();
	        return result;
	        
		}catch(Exception e){
			throw new DAOException("Erro salvar TituloPeritoDAOJPA", e);
		}
	}

	public ArrayList<AbstractEntity> pesquisarAnalises(Pericia pericia) throws DAOException {
		logger.debug("Entrou PericiaDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        Query query = manager.createQuery("select a from Analise a where a.pericia.codigo = :codigoPericia order by titulo");
	        query.setParameter("codigoPericia", pericia.getCodigo());
	        
	        ArrayList result = (ArrayList)query.getResultList();
	        return result;
	        
		}catch(Exception e){
			throw new DAOException("Erro salvar TituloPeritoDAOJPA", e);
		}
	}	

	public ArrayList<AbstractEntity> pesquisarEvidencias(Objeto objeto) throws DAOException {
		logger.debug("Entrou PericiaDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        Query query = manager.createQuery("select e from Evidencia e where e.objeto.codigo = :codigoObjeto order by titulo");
	        query.setParameter("codigoObjeto", objeto.getCodigo());
	        
	        ArrayList result = (ArrayList)query.getResultList();
	        return result;
	        
		}catch(Exception e){
			throw new DAOException("Erro salvar TituloPeritoDAOJPA", e);
		}
	}		
	
	public ArrayList<AbstractEntity> pesquisarAnexos(Pericia pericia) throws DAOException {
		logger.debug("Entrou PericiaDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        Query query = manager.createQuery("select a from Anexo a where a.pericia.codigo = :codigoPericia order by titulo");
	        query.setParameter("codigoPericia", pericia.getCodigo());
	        
	        ArrayList result = (ArrayList)query.getResultList();
	        return result;
	        
		}catch(Exception e){
			throw new DAOException("Erro salvar TituloPeritoDAOJPA", e);
		}
	}	

	public ArrayList<AbstractEntity> pesquisarCenas(Analise analise) throws DAOException {
		logger.debug("Entrou PericiaDAOJPA");
		EntityManager manager = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        Query query = manager.createQuery("select c from Cena c where c.analise.codigo = :codigoAnalise order by titulo");
	        query.setParameter("codigoAnalise", analise.getCodigo());
	        
	        ArrayList result = (ArrayList)query.getResultList();
	        return result;
	        
		}catch(Exception e){
			throw new DAOException("Erro salvar TituloPeritoDAOJPA", e);
		}
	}		
	
	//@Override
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws DAOException {
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		PaginaTO retorno = null;
		try{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();
			
	        queryStr.append("from Cliente c order by ");
	
	        switch (criterios.getOrdenarPor())
	        {
	            case CriterioPesquisaTO.ORDENAR_POR_CODIGO:
	                queryStr.append(" c.codigo ");
	                break;
	            case CriterioPesquisaTO.ORDENAR_POR_NOME:
	                queryStr.append(" c.nome ");
	                break;
	            default:
	            	queryStr.append(" c.codigo ");
	        }
	        
	        switch (criterios.getOrdem())
	        {
	            case CriterioPesquisaTO.ORDEM_CRESCENTE:
	                queryStr.append(" asc ");
	                break;
	            case CriterioPesquisaTO.ORDEM_DECRESCENTE:
	                queryStr.append(" desc ");
	                break;
	            default:
	            	queryStr.append(" asc ");
	        }
			
	        Query query = manager.createQuery(queryStr.toString());
			
			query.setFirstResult(criterios.getQtdeRegistrosPorPagina() * (criterios.getPagina() - 1));
	        query.setMaxResults(criterios.getQtdeRegistrosPorPagina());
	        
	        List result = query.getResultList();
	        
	        Long qtdeRegistros = (Long) manager.createQuery("select count(*) from Cliente c").getResultList().iterator().next();
			
			retorno = new PaginaTO(criterios);
			retorno.setRegistros(result);
			retorno.setTotalRegistros(qtdeRegistros);
		
		}catch(Exception e){
			throw new DAOException("Erro consultando clientes", e);
		}
		return retorno;
	}
	
	
}
