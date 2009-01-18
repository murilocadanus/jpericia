package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.businessdelegate.CenaDelegate;
import org.jpericia.analise.views.CenaView;
import org.jpericia.core.exception.BusinessDelegateException;

public class CenaRemoverAction extends Action
{
	private CenaView cenaView;
	
	public CenaRemoverAction(CenaView cenaView, String text)
	{
		super(text);
		this.cenaView = cenaView;
	}
	
	public void run()
	{
		try
		{
			CenaDelegate.getInstance().remover(cenaView.getCena());
			cenaView.getViewer().setInput(CenaDelegate.getInstance().pesquisar());
			cenaView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
