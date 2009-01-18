package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.TituloContatoDelegate;
import org.jpericia.organizacao.views.TituloContatoView;

public class TituloContatoRemoverAction extends Action
{
	private TituloContatoView tituloContatoView;
	
	public TituloContatoRemoverAction(TituloContatoView tituloContatoView, String text)
	{
		super(text);
		this.tituloContatoView = tituloContatoView;
	}
	
	public void run()
	{
		try
		{
			TituloContatoDelegate.getInstance().remover(tituloContatoView.getTituloContato());
			tituloContatoView.getViewer().setInput(TituloContatoDelegate.getInstance().pesquisar());
			tituloContatoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
