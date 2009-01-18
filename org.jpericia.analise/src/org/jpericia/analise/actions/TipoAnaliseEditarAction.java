package org.jpericia.analise.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.analise.views.TipoAnaliseView;
import org.jpericia.analise.views.preference.TipoAnalisePreference;

public class TipoAnaliseEditarAction extends Action
{
	private TipoAnaliseView view;
	
    public TipoAnaliseEditarAction(TipoAnaliseView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        TipoAnalisePreference dlg = new TipoAnalisePreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
