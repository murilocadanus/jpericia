package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.views.AnaliseView;
import org.jpericia.analise.views.preference.AnalisePreference;

public class AnaliseFinalizarAction extends Action
{
	private AnaliseView view;
	
    public AnaliseFinalizarAction(AnaliseView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        AnalisePreference dlg = new AnalisePreference(view, view.getViewSite().getShell(), true);
        dlg.open();
    }
}
