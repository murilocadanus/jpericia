package com.vbkn.titan.action.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vbkn.titan.action.BaseAction;
import com.vbkn.titan.businessdelegate.cliente.ClienteDelegate;
import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.exception.BusinessDelegateException;
import com.vbkn.titan.form.cliente.ClienteForm;
import com.vbkn.titan.util.DisplayTagUtil;
import com.vbkn.titan.util.to.CriterioPesquisaTO;
import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */


public class ClienteAction extends BaseAction {
	
	private static Logger logger = Logger.getLogger(ClienteAction.class);
	
	/**
	 * Salvar Clientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward salvar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
		logger.debug("Inicio " + this.getClass().getName() + ".salvar()");
		try{
			ClienteForm clienteForm = (ClienteForm)form;
			Cliente cliente = clienteForm.getCliente();
			ClienteDelegate delegate = new ClienteDelegate();
			delegate.salvar(cliente);
			this.addMessage(request, "cliente.incluido");
			clienteForm.reset(mapping, request);
			logger.debug("Fim " + this.getClass().getName() + ".salvar()");
		}catch (BusinessDelegateException e) {
			this.catchException(request, "cliente.erro", e);
		}
		return mapping.findForward(BaseAction.SUCCESS);
	}
	
	/**
	 * Consulta de clientes
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public ActionForward consultar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception{
		logger.debug("Inicio " + this.getClass().getName() + ".consultar()");
		try{
			ClienteDelegate delegate = new ClienteDelegate();
			
			String tableId = "row";
	        String orderByCodigo = "orderByCodigo";
	        String orderByName = "orderByName";

	        Integer order = DisplayTagUtil.getCurrentOrder(tableId, request);
	        Integer curPage = DisplayTagUtil.getCurrentPage(tableId, request);
	        String orderBy = DisplayTagUtil.getCurrentSortName(tableId, request);
	        
	        CriterioPesquisaTO criterios = new CriterioPesquisaTO();
	        
	        criterios.setOrdem(order.intValue());
	        criterios.setPagina(curPage.intValue());
	        criterios.setQtdeRegistrosPorPagina(this.getQtdeRegistrosPorPagina(request));
	        
	        if (orderByCodigo.equals(orderBy)) {
	            criterios.setOrdenarPor(CriterioPesquisaTO.ORDENAR_POR_CODIGO);
	        } else if (orderByName.equals(orderBy)) {
	            criterios.setOrdenarPor(CriterioPesquisaTO.ORDENAR_POR_NOME);
	        } else {
	            criterios.setOrdenarPor(CriterioPesquisaTO.ORDENAR_POR_CODIGO);
	        }
			
			PaginaTO clientes = delegate.consultar(criterios);
			
			this.salvarPagina(request, clientes);
			
			logger.debug("Fim " + this.getClass().getName() + ".consultar()");
		}catch (BusinessDelegateException e) {
			this.catchException(request, "cliente.erro", e);
		}
		return mapping.findForward("success");
	}

}
