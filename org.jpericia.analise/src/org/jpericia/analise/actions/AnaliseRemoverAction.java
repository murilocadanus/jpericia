package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.analise.views.AnaliseView;
import org.jpericia.core.exception.BusinessDelegateException;

public class AnaliseRemoverAction extends Action
{
	private AnaliseView analiseView;
	
	public AnaliseRemoverAction(AnaliseView analiseView, String text)
	{
		super(text);
		this.analiseView = analiseView;
	}
	
	public void run()
	{
		try
		{
			AnaliseDelegate.getInstance().remover(analiseView.getAnalise());
			analiseView.getViewer().setInput(AnaliseDelegate.getInstance().pesquisar());
			analiseView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
