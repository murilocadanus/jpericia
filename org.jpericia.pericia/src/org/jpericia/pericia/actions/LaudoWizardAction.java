package org.jpericia.pericia.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.jpericia.pericia.views.PericiaView;
import org.jpericia.pericia.wizards.LaudoWizard;

public class LaudoWizardAction extends Action
{
	private PericiaView view;
	
    public LaudoWizardAction(PericiaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {
        //PericiaPreference dlg = new PericiaPreference(view, view.getViewSite().getShell());
        //dlg.open();
        
    	IWorkbenchPartSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
    	IWorkbenchWindow window = site.getWorkbenchWindow();
		LaudoWizard wizard = new LaudoWizard(view);
		wizard.init(window.getWorkbench(), null);
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
    }
}
