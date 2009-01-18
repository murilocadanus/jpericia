package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.views.CenaView;
import org.jpericia.analise.views.preference.CenaPreference;

public class CenaEditarAction extends Action
{
	private CenaView view;
	
    public CenaEditarAction(CenaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        CenaPreference dlg = new CenaPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
