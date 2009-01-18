package org.jpericia.applicationservice.objeto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jpericia.applicationservice.AbstractApplicationService;
import org.jpericia.businessobject.objeto.ObjetoBO;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.ejb.exception.ApplicationServiceException;
import org.jpericia.ejb.exception.BusinessObjectException;

/**
 * @author Marlus Cadanus da Costa
 */
public class ObjetoAS extends AbstractApplicationService
{

	private static Logger logger = Logger.getLogger(ObjetoAS.class);

	private static ObjetoAS me;

	private ObjetoAS()
	{
	}

	private static synchronized void loadInstance()
	{
		if (me == null)
		{
			me = new ObjetoAS();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static ObjetoAS getInstance()
	{
		if (me == null)
		{
			loadInstance();
		}
		return me;
	}

	/**
	 * Inserir
	 * 
	 * @throws ApplicationServiceException
	 */
	public void inserir(Objeto objeto) throws ApplicationServiceException
	{
		try
		{
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ObjetoBO.getInstance().inserir(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		}
		catch (BusinessObjectException e)
		{
			this.rollbackTransaction(this.getClass() + ".salvar()");
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException(
					"Ocorreu um erro ao salvar Cliente.", e);
		}
		finally
		{
			this.closeSession();
		}
	}

	/**
	 * Atualizar
	 * 
	 * @throws ApplicationServiceException
	 */
	public void atualizar(Objeto objeto) throws ApplicationServiceException
	{
		try
		{
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ObjetoBO.getInstance().atualizar(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		}
		catch (BusinessObjectException boe)
		{
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException(
					"Ocorreu um erro ao salvar Cliente.", boe);
		}
	}

	/**
	 * Remover
	 * 
	 * @throws ApplicationServiceException
	 */
	public void remover(Objeto objeto) throws ApplicationServiceException
	{
		try
		{
			this.beginTransaction();
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ObjetoBO.getInstance().remover(objeto);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			this.commitTransaction();
		}
		catch (BusinessObjectException boe)
		{
			logger.error(this.getClass().getName() + ".salvar()");
			this.rollbackTransaction("remover");
			throw new ApplicationServiceException(
					"Ocorreu um erro ao salvar Cliente.", boe);
		}
	}

	/**
	 * Pesquisar
	 * 
	 * @throws ApplicationServiceException
	 */
	public ArrayList<AbstractEntity> pesquisar()
			throws ApplicationServiceException
	{
		try
		{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			ArrayList<AbstractEntity> pesquisa = ObjetoBO.getInstance()
					.pesquisar();
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
			return pesquisa;
		}
		catch (BusinessObjectException boe)
		{
			logger.error(this.getClass().getName() + ".salvar()");
			throw new ApplicationServiceException(
					"Ocorreu um erro ao salvar Cliente.", boe);
		}
	}

}
