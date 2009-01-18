package org.jpericia.perito.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.perito.views.FuncaoPeritoView;
import org.jpericia.perito.views.preference.FuncaoPeritoPreference;

public class FuncaoPeritoEditarAction extends Action
{
	private FuncaoPeritoView view;
	
    public FuncaoPeritoEditarAction(FuncaoPeritoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        FuncaoPeritoPreference dlg = new FuncaoPeritoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
