package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.perito.views.TituloPeritoView;
import org.jpericia.perito.views.preference.TituloPeritoPreference;

public class TituloPeritoEditarAction extends Action
{
	private TituloPeritoView view;
	
    public TituloPeritoEditarAction(TituloPeritoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        TituloPeritoPreference dlg = new TituloPeritoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
