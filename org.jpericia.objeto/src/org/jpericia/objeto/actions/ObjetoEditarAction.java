package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.objeto.views.ObjetoView;
import org.jpericia.objeto.views.preference.ObjetoPreference;

public class ObjetoEditarAction extends Action
{
	private ObjetoView view;
	
    public ObjetoEditarAction(ObjetoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        ObjetoPreference dlg = new ObjetoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
