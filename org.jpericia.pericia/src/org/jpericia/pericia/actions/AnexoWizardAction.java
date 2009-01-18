package org.jpericia.pericia.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.jpericia.core.ui.actions.AbstractWizardAction;
import org.jpericia.pericia.wizards.AnexoWizard;

public class AnexoWizardAction extends AbstractWizardAction
{
	@Override
	public void run(IAction action)
	{
		site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
		window = site.getWorkbenchWindow();
		AnexoWizard wizard = new AnexoWizard();
		wizard.init(window.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
	}
}
