package org.jpericia.objeto.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.jpericia.core.ui.actions.AbstractWizardAction;
import org.jpericia.objeto.wizards.TipoObjetoWizard;

public class TipoObjetoWizardAction extends AbstractWizardAction
{
	@Override
	public void run(IAction action)
	{
		site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
		window = site.getWorkbenchWindow();
		TipoObjetoWizard wizard = new TipoObjetoWizard();
		wizard.init(window.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
	}
}
