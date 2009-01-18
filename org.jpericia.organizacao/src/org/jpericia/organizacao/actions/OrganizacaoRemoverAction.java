package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.organizacao.views.OrganizacaoView;

public class OrganizacaoRemoverAction extends Action
{
	private OrganizacaoView organizacaoView;
	
	public OrganizacaoRemoverAction(OrganizacaoView organizacaoView, String text)
	{
		super(text);
		this.organizacaoView = organizacaoView;
	}
	
	public void run()
	{
		try
		{
			OrganizacaoDelegate.getInstance().remover(organizacaoView.getOrganizacao());
			organizacaoView.getViewer().setInput(OrganizacaoDelegate.getInstance().pesquisar());
			organizacaoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
