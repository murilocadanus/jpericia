package org.jpericia.dao.objeto;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.dao.AbstractDAO;
import org.jpericia.dao.PersistenceUtil;
import org.jpericia.ejb.exception.DAOException;

/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão J2EE Data Access Object
 */

@SuppressWarnings("unchecked")
public class ObjetoDAOJPA extends AbstractDAO implements ObjetoDAO
{

	private static Logger logger = Logger.getLogger(ObjetoDAOJPA.class);

	// @Override
	public void inserir(Objeto objeto) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Objeto objetoManaged = manager.merge(objeto);
			
			manager.persist(objetoManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar", e);
		}
	}

	// @Override
	public void atualizar(Objeto objeto) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Objeto objetoManaged = manager.merge(objeto);
			manager.persist(objetoManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar Cliente", e);
		}
	}

	// @Override
	public void remover(Objeto objeto) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Objeto objetoManaged = manager.merge(objeto);
			manager.remove(objetoManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar Cliente", e);
		}
	}

	// @Override
	public ArrayList<AbstractEntity> pesquisar() throws DAOException
	{
		logger.debug("Entrou ObjetoDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();

			queryStr.append("from Objeto o order by titulo");
			Query query = manager.createQuery(queryStr.toString());

			ArrayList result = (ArrayList) query.getResultList();
			return result;

		}
		catch (Exception e)
		{
			throw new DAOException("Erro pesquisar", e);
		}
	}

}
