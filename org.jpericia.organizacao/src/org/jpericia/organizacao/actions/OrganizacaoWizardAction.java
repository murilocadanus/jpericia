package org.jpericia.organizacao.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.jpericia.core.ui.actions.AbstractWizardAction;
import org.jpericia.organizacao.wizards.OrganizacaoWizard;

public class OrganizacaoWizardAction extends AbstractWizardAction
{
	@Override
	public void run(IAction action)
	{
		site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
		window = site.getWorkbenchWindow();
		OrganizacaoWizard wizard = new OrganizacaoWizard();
		wizard.init(window.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		dialog.open();
	}
}
