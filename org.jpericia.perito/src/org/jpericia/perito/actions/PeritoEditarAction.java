package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.perito.views.PeritoView;
import org.jpericia.perito.views.preference.PeritoPreference;

public class PeritoEditarAction extends Action
{
	private PeritoView view;
	
    public PeritoEditarAction(PeritoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        PeritoPreference dlg = new PeritoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
