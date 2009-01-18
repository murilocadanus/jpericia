package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.pericia.views.PericiaView;
import org.jpericia.pericia.views.preference.LaudoPreference;

public class LaudoEditarAction extends Action
{
	private PericiaView view;
	
    public LaudoEditarAction(PericiaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {    	
        LaudoPreference dlg = new LaudoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
