package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.organizacao.views.TituloContatoView;
import org.jpericia.organizacao.views.preference.TituloContatoPreference;

public class TituloContatoEditarAction extends Action
{
	private TituloContatoView view;
	
    public TituloContatoEditarAction(TituloContatoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        TituloContatoPreference dlg = new TituloContatoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
