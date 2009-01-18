package org.jpericia.dao.objeto;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.dao.AbstractDAO;
import org.jpericia.dao.PersistenceUtil;
import org.jpericia.ejb.exception.DAOException;

/**
 * @author Marlus Cadanus da Costa
 * 
 * Classe que implementa o padrão J2EE Data Access Object
 */

@SuppressWarnings("unchecked")
public class EvidenciaDAOJPA extends AbstractDAO implements EvidenciaDAO
{

	private static Logger logger = Logger.getLogger(EvidenciaDAOJPA.class);

	// @Override
	public void inserir(Evidencia evidencia) throws DAOException
	{
		logger.debug("Entrou EvidenciaDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			manager.persist(evidencia);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro salvar", e);
		}
	}

	public void atualizar(Evidencia evidencia) throws DAOException
	{
		logger.debug("Entrou EvidenciaDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Evidencia evidenciaManaged = manager.merge(evidencia);
			manager.persist(evidenciaManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro atualizar", e);
		}
	}

	public void remover(Evidencia evidencia) throws DAOException
	{
		logger.debug("Entrou EvidenciaDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			Evidencia evidenciaManaged = manager.merge(evidencia);
			
			manager.remove(evidenciaManaged);
			manager.flush();
		}
		catch (Exception e)
		{
			throw new DAOException("Erro remover", e);
		}
	}

	public ArrayList<AbstractEntity> pesquisar() throws DAOException
	{
		logger.debug("Entrou EvidenciaDAOJPA");
		EntityManager manager = null;
		try
		{
			manager = PersistenceUtil.currentEntityManager();
			StringBuffer queryStr = new StringBuffer();

			queryStr.append("from Evidencia e order by titulo");
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
