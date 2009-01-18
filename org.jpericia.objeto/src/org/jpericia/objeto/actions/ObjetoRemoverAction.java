package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.ObjetoDelegate;
import org.jpericia.objeto.views.ObjetoView;

public class ObjetoRemoverAction extends Action
{
	private ObjetoView objetoView;
	
	public ObjetoRemoverAction(ObjetoView objetoView, String text)
	{
		super(text);
		this.objetoView = objetoView;
	}
	
	public void run()
	{
		try
		{
			ObjetoDelegate.getInstance().remover(objetoView.getObjeto());
			objetoView.getViewer().setInput(ObjetoDelegate.getInstance().pesquisar());
			objetoView.getViewer().refresh();
			
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
