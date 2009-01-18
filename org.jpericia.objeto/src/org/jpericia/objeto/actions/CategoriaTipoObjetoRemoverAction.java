package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.CategoriaTipoObjetoDelegate;
import org.jpericia.objeto.views.CategoriaTipoObjetoView;

public class CategoriaTipoObjetoRemoverAction extends Action
{
	private CategoriaTipoObjetoView categoriaTipoObjetoView;
	
	public CategoriaTipoObjetoRemoverAction(CategoriaTipoObjetoView categoriaTipoObjetoView, String text)
	{
		super(text);
		this.categoriaTipoObjetoView = categoriaTipoObjetoView;
	}
	
	public void run()
	{
		try
		{
			CategoriaTipoObjetoDelegate.getInstance().remover(categoriaTipoObjetoView.getCategoriaTipoObjeto());
			categoriaTipoObjetoView.getViewer().setInput(CategoriaTipoObjetoDelegate.getInstance().pesquisar());
			categoriaTipoObjetoView.getViewer().refresh();
			
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
