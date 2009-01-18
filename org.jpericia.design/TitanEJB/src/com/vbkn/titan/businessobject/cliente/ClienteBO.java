package com.vbkn.titan.businessobject.cliente;

import org.apache.log4j.Logger;

import com.vbkn.titan.businessobject.AbstractBusinessObject;
import com.vbkn.titan.dao.TitanDAOFactory;
import com.vbkn.titan.dao.cliente.ClienteDAO;
import com.vbkn.titan.ejb.exception.ApplicationServiceException;
import com.vbkn.titan.ejb.exception.BusinessObjectException;
import com.vbkn.titan.ejb.exception.DAOException;
import com.vbkn.titan.ejb.exception.TitanDAOFactoryException;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */
public class ClienteBO extends AbstractBusinessObject {
	
	private static Logger logger = Logger.getLogger(ClienteBO.class);
	
	private static ClienteBO me;

	private ClienteBO() {
	}

	private static synchronized void loadInstance() {
		if (me == null) {
			me = new ClienteBO();
		}
	}

	/**
	 * @return ClienteAS
	 */
	public static ClienteBO getInstance() {
		if (me == null) {
			loadInstance();
		}
		return me;
	}
	
	/**
	 * Salvar
	 * @throws ApplicationServiceException
	 */
	public void salvar(Cliente cliente) throws BusinessObjectException{
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
			TitanDAOFactory factory = this.getDaoFactory();
			ClienteDAO clienteDAO = (ClienteDAO)factory.getDao(ClienteDAO.class);
			clienteDAO.salvar(cliente);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		}catch (TitanDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro salvando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro salvando Cliente", de);
		}
	}
	
	/**
	 * Consultar Cliente
	 * @throws ApplicationServiceException
	 */
	public PaginaTO consultar(CriterioPesquisaTO criterios) throws BusinessObjectException{
		PaginaTO retorno = null;
		try{
			logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
			TitanDAOFactory factory = this.getDaoFactory();
			ClienteDAO clienteDAO = (ClienteDAO)factory.getDao(ClienteDAO.class);
			retorno = clienteDAO.consultar(criterios);
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		}catch (TitanDAOFactoryException tdfe) {
			logger.fatal(tdfe.getMessage(), tdfe);
			throw new BusinessObjectException("Erro consultando Cliente", tdfe);
		}catch (DAOException de){
			logger.fatal(de.getMessage(), de);
			throw new BusinessObjectException("Erro consultando Cliente", de);
		}
		return retorno;
	}

}
