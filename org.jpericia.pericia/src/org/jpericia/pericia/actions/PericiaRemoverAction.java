package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.views.PericiaView;

public class PericiaRemoverAction extends Action
{
	private PericiaView periciaView;
	
	public PericiaRemoverAction(PericiaView periciaView, String text)
	{
		super(text);
		this.periciaView = periciaView;
	}
	
	public void run()
	{
		try
		{
			PericiaDelegate.getInstance().remover(periciaView.getPericia());
			periciaView.getViewer().setInput(PericiaDelegate.getInstance().pesquisar());
			periciaView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
