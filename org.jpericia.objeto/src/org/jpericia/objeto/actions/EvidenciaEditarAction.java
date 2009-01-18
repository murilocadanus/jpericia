package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.objeto.views.EvidenciaView;
import org.jpericia.objeto.views.preference.EvidenciaPreference;

public class EvidenciaEditarAction extends Action
{
	private EvidenciaView view;
	
    public EvidenciaEditarAction(EvidenciaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        EvidenciaPreference dlg = new EvidenciaPreference(view, view.getViewSite().getShell());
        dlg.open();
    }
}
