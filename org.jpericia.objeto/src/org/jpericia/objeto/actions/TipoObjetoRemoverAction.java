package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.TipoObjetoDelegate;
import org.jpericia.objeto.views.TipoObjetoView;

public class TipoObjetoRemoverAction extends Action
{
	private TipoObjetoView tipoObjetoView;
	
	public TipoObjetoRemoverAction(TipoObjetoView tipoObjetoView, String text)
	{
		super(text);
		this.tipoObjetoView = tipoObjetoView;
	}
	
	public void run()
	{
		try
		{
			TipoObjetoDelegate.getInstance().remover(tipoObjetoView.getTipoObjeto());
			tipoObjetoView.getViewer().setInput(TipoObjetoDelegate.getInstance().pesquisar());
			tipoObjetoView.getViewer().refresh();
			
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
