package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.pericia.views.PericiaView;
import org.jpericia.pericia.views.preference.PericiaPreference;

public class PericiaEditarAction extends Action
{
	private PericiaView view;
	
    public PericiaEditarAction(PericiaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        PericiaPreference dlg = new PericiaPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
