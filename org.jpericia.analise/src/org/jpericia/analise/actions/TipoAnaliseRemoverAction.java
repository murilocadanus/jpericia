package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.businessdelegate.TipoAnaliseDelegate;
import org.jpericia.analise.views.TipoAnaliseView;
import org.jpericia.core.exception.BusinessDelegateException;

public class TipoAnaliseRemoverAction extends Action
{
	private TipoAnaliseView tipoAnaliseView;
	
	public TipoAnaliseRemoverAction(TipoAnaliseView tipoAnaliseView, String text)
	{
		super(text);
		this.tipoAnaliseView = tipoAnaliseView;
	}
	
	public void run()
	{
		try
		{
			TipoAnaliseDelegate.getInstance().remover(tipoAnaliseView.getTipoAnalise());
			tipoAnaliseView.getViewer().setInput(TipoAnaliseDelegate.getInstance().pesquisar());
			tipoAnaliseView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
