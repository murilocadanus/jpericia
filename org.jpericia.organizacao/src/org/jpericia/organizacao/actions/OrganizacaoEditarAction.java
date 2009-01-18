package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.organizacao.views.OrganizacaoView;
import org.jpericia.organizacao.views.preference.OrganizacaoPreference;

public class OrganizacaoEditarAction extends Action
{
	private OrganizacaoView view;
	
    public OrganizacaoEditarAction(OrganizacaoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        OrganizacaoPreference dlg = new OrganizacaoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
