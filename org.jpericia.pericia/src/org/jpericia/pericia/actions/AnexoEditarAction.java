package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.pericia.views.AnexoView;
import org.jpericia.pericia.views.preference.AnexoPreference;

public class AnexoEditarAction extends Action
{
	private AnexoView view;
	
    public AnexoEditarAction(AnexoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        AnexoPreference dlg = new AnexoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
