package org.jpericia.dao.analise;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.dao.AbstractDAO;
import org.jpericia.dao.PersistenceUtil;
import org.jpericia.ejb.exception.DAOException;

/**
 * Classe que implementa o padrão J2EE Data Access Object
 */

@SuppressWarnings("unchecked")
public class CenaDAOJPA extends AbstractDAO implements CenaDAO
{

	private static Logger logger = Logger.getLogger(CenaDAOJPA.class);

	// @Override
	public void inserir(Cena cena) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			manager.persist(cena);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar Cliente", e);
		}
	}

	// @Override
	public void atualizar(Cena cena) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Cena cenaManaged = manager.merge(cena);
			
			manager.refresh(cenaManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar Cliente", e);
		}
	}

	// @Override
	public void remover(Cena cena) throws DAOException
	{
		logger.debug("Entrou ClienteDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Cena cenaManaged = manager.merge(cena);
			
			manager.remove(cenaManaged);
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
		logger.debug("Entrou CenaDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();

			queryStr.append("from Cena c order by c.titulo");
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
