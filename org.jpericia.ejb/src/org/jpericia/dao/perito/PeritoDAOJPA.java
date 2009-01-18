package org.jpericia.dao.perito;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.dao.AbstractDAO;
import org.jpericia.dao.PersistenceUtil;
import org.jpericia.ejb.exception.DAOException;

/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão J2EE Data Access Object
 */

@SuppressWarnings("unchecked")
public class PeritoDAOJPA extends AbstractDAO implements PeritoDAO
{

	private static Logger logger = Logger.getLogger(PeritoDAOJPA.class);

	// @Override
	public void inserir(Perito Perito) throws DAOException
	{
		logger.debug("Entrou inserir");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			manager.persist(Perito);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro inserir", e);
		}
	}

	public void atualizar(Perito Perito) throws DAOException
	{
		logger.debug("Entrou atualizar");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Perito PeritoManaged = manager.merge(Perito);

			// manager.refresh(PeritoManaged);
			manager.persist(PeritoManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro atualizar", e);
		}
	}

	public void remover(Perito Perito) throws DAOException
	{
		logger.debug("Entrou remover");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Perito PeritoManaged = manager.merge(Perito);

			manager.remove(PeritoManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro remover", e);
		}
	}

	public ArrayList<AbstractEntity> pesquisar() throws DAOException
	{
		logger.debug("Entrou pesquisar");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();

			queryStr.append("from Perito p order by nome");
			Query query = manager.createQuery(queryStr.toString());

			ArrayList result = (ArrayList) query.getResultList();
			return result;

		}
		catch (Exception e)
		{
			throw new DAOException("Erro pesquisar", e);
		}
	}

	// @Override
	public Perito autenticar(Perito perito) throws DAOException
	{
		logger.debug("Entrou autenticar");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
					
			Query query = manager.createQuery("from Perito p where p.usuario = :usuario and p.senha = :senha");
			query.setParameter("usuario", perito.getUsuario());
			query.setParameter("senha", perito.getSenha());		
			return (Perito)query.getSingleResult();
		}
		catch (NoResultException ex)
		{
			return null;
		}
		catch (Exception e)
		{
			throw new DAOException("Erro consultando clientes", e);
		}
	}
}
