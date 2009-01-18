package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.PeritoDelegate;
import org.jpericia.perito.views.PeritoView;

public class PeritoRemoverAction extends Action
{
	private PeritoView peritoView;
	
	public PeritoRemoverAction(PeritoView peritoView, String text)
	{
		super(text);
		this.peritoView = peritoView;
	}
	
	public void run()
	{
		try
		{
			PeritoDelegate.getInstance().remover(peritoView.getPerito());
			peritoView.getViewer().setInput(PeritoDelegate.getInstance().pesquisar());
			peritoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
