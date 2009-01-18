package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.objeto.views.TipoObjetoView;
import org.jpericia.objeto.views.preference.TipoObjetoPreference;

public class TipoObjetoEditarAction extends Action
{
	private TipoObjetoView view;
	
    public TipoObjetoEditarAction(TipoObjetoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        TipoObjetoPreference dlg = new TipoObjetoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
