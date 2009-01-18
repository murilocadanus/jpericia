package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.TituloPeritoDelegate;
import org.jpericia.perito.views.TituloPeritoView;

public class TituloPeritoRemoverAction extends Action
{
	private TituloPeritoView tituloPeritoView;
	
	public TituloPeritoRemoverAction(TituloPeritoView tituloPeritoView, String text)
	{
		super(text);
		this.tituloPeritoView = tituloPeritoView;
	}
	
	public void run()
	{
		try
		{
			TituloPeritoDelegate.getInstance().remover(tituloPeritoView.getTituloPerito());
			tituloPeritoView.getViewer().setInput(TituloPeritoDelegate.getInstance().pesquisar());
			tituloPeritoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
