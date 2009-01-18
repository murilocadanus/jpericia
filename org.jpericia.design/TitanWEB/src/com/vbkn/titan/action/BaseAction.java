package com.vbkn.titan.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.vbkn.titan.util.to.PaginaTO;

/**
 * @author Valter Bruno Konrad Neto
 */
public class BaseAction extends DispatchAction {
	
	private static Logger logger = Logger.getLogger(BaseAction.class);
	
	/**
	 * Nome do parametro que indica a quandidae de resgistros que deve ser exibido numa paginação
	 */
	private static final int QTDE_REGISTROS_POR_PAGINA = 10;
	
	/**
	 * Constante para foward de sucesso
	 */
	protected static final String SUCCESS = "success";
	
	/**
	 * Constante para foward de sucesso
	 */
	protected static final String FAIL = "fail";
	
	/**
	 * Constante para foward que represa a pagina de entrada de dados para a action
	 */
	protected static final String INPUT = "input";
	
	/**
	 * constante para referenciar a lista de resultados de uma consulta
	 */
	public static final String PAGING_LIST = "pagingList";
	
	/**
	 * constante para referenciar o tamanho total da lista pesquisada no banco de dados
	 */
	public static final String PAGING_LIST_SIZE = "pagingListSize";
	
	/**
	 * constante para referencia o tamanho (quantidade de registros) das páginas de consulta
	 */
	public static final String PAGING_PAGE_SIZE = "pagingPageSize";
	
	private ActionMessages messages;
	
	/**
	 * Retorna a quantidade de linhas que devem ser exibidas numa paginação de dados.
	 * @return A quantidade de linhas
	 */
	protected int getQtdeRegistrosPorPagina(HttpServletRequest request){
		int qtdeRegistrosPorPagina = BaseAction.QTDE_REGISTROS_POR_PAGINA;
		return qtdeRegistrosPorPagina;
	}
	
	/**
	 * Salva o resultado de uma pesquisa paginada na resição 
	 *
	 */
	protected void salvarPagina(HttpServletRequest request, PaginaTO pagina){
		request.setAttribute(PAGING_LIST_SIZE, new Integer((int)pagina.getTotalRegistros()));
		request.setAttribute(PAGING_LIST, pagina.getRegistros());
		request.setAttribute(PAGING_PAGE_SIZE, new Integer(this.getQtdeRegistrosPorPagina(request)));
	}
	
	/**
	 * Salva mensagens
	 * @param request
	 * @param message
	 */
	protected void addMessage(HttpServletRequest request, String message){
		this.messages = new ActionMessages();
        ActionMessage actionMessage = new ActionMessage(message);
        messages.add(message, actionMessage);
        this.saveMessages(request, messages);
	}
	
	/**
	 * Para tratar exception
	 * @param request
	 * @param message
	 * @param e
	 */
	protected final void catchException(HttpServletRequest request, String message, Exception e) {
		this.messages = new ActionMessages();
        ActionMessage actionMessage = new ActionMessage(message);
        messages.add(message, actionMessage);
        this.saveMessages(request, messages);
        logger.error("Erro na action", e);
	}
}
