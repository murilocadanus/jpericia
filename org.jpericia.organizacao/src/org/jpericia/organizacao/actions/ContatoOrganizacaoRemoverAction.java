package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.ContatoOrganizacaoDelegate;
import org.jpericia.organizacao.views.ContatoOrganizacaoView;

public class ContatoOrganizacaoRemoverAction extends Action
{
	private ContatoOrganizacaoView contatoOrganizacaoView;
	
	public ContatoOrganizacaoRemoverAction(ContatoOrganizacaoView contatoOrganizacaoView, String text)
	{
		super(text);
		this.contatoOrganizacaoView = contatoOrganizacaoView;
	}
	
	public void run()
	{
		try
		{
			ContatoOrganizacaoDelegate.getInstance().remover(contatoOrganizacaoView.getContatoOrganizacao());
			contatoOrganizacaoView.getViewer().setInput(ContatoOrganizacaoDelegate.getInstance().pesquisar());
			contatoOrganizacaoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
