package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.FuncaoPeritoDelegate;
import org.jpericia.perito.views.FuncaoPeritoView;

public class FuncaoPeritoRemoverAction extends Action
{
	private FuncaoPeritoView funcaoPeritoView;
	
	public FuncaoPeritoRemoverAction(FuncaoPeritoView funcaoPeritoView, String text)
	{
		super(text);
		this.funcaoPeritoView = funcaoPeritoView;
	}
	
	public void run()
	{
		try
		{
			FuncaoPeritoDelegate.getInstance().remover(funcaoPeritoView.getFuncaoPerito());
			funcaoPeritoView.getViewer().setInput(FuncaoPeritoDelegate.getInstance().pesquisar());
			funcaoPeritoView.getViewer().refresh();
			
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
