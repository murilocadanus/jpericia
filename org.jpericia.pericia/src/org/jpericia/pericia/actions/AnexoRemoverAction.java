package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.pericia.bussinessdelegate.AnexoDelegate;
import org.jpericia.pericia.views.AnexoView;

public class AnexoRemoverAction extends Action
{
	private AnexoView anexoView;
	
	public AnexoRemoverAction(AnexoView anexoView, String text)
	{
		super(text);
		this.anexoView = anexoView;
	}
	
	public void run()
	{
		try
		{
			AnexoDelegate.getInstance().remover(anexoView.getAnexo());
			anexoView.getViewer().setInput(AnexoDelegate.getInstance().pesquisar());
			anexoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
