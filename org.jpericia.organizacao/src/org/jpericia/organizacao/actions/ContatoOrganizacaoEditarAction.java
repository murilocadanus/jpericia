package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.organizacao.views.ContatoOrganizacaoView;
import org.jpericia.organizacao.views.preference.ContatoOrganizacaoPreference;

public class ContatoOrganizacaoEditarAction extends Action
{
	private ContatoOrganizacaoView view;
	
    public ContatoOrganizacaoEditarAction(ContatoOrganizacaoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        ContatoOrganizacaoPreference dlg = new ContatoOrganizacaoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
