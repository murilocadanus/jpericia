package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.TipoOrganizacaoDelegate;
import org.jpericia.organizacao.views.TipoOrganizacaoView;

public class TipoOrganizacaoRemoverAction extends Action
{
	private TipoOrganizacaoView tipoOrganizacaoView;
	
	public TipoOrganizacaoRemoverAction(TipoOrganizacaoView tipoOrganizacaoView, String text)
	{
		super(text);
		this.tipoOrganizacaoView = tipoOrganizacaoView;
	}
	
	public void run()
	{
		try
		{
			TipoOrganizacaoDelegate.getInstance().remover(tipoOrganizacaoView.getTipoOrganizacao());
			tipoOrganizacaoView.getViewer().setInput(TipoOrganizacaoDelegate.getInstance().pesquisar());
			tipoOrganizacaoView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
