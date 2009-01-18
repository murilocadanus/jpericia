package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.objeto.views.CategoriaTipoObjetoView;
import org.jpericia.objeto.views.preference.CategoriaTipoObjetoPreference;

public class CategoriaTipoObjetoEditarAction extends Action
{
	private CategoriaTipoObjetoView view;
	
    public CategoriaTipoObjetoEditarAction(CategoriaTipoObjetoView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        CategoriaTipoObjetoPreference dlg = new CategoriaTipoObjetoPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
