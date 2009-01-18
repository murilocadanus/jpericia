package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.organizacao.views.TipoOrganizacaoView;
import org.jpericia.organizacao.views.preference.TipoOrganizacaoPreference;

public class TipoOrganizacaoEditarAction extends Action
{
	private TipoOrganizacaoView view;
	
    public TipoOrganizacaoEditarAction(TipoOrganizacaoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        TipoOrganizacaoPreference dlg = new TipoOrganizacaoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
