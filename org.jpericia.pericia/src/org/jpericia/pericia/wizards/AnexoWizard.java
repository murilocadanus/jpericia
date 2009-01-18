package org.jpericia.pericia.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.pericia.PericiaPlugin;
import org.jpericia.pericia.bussinessdelegate.AnexoDelegate;
import org.jpericia.pericia.wizards.pages.AnexoWizardPage;

public class AnexoWizard extends AbstractWizard
{
	AnexoWizardPage anexoWizardPage;
	
	Anexo anexo = new Anexo();
	
	public AnexoWizard()
	{
		IDialogSettings peritoSettings = PericiaPlugin.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = peritoSettings.getSection("OrganizacaoWizard");
		
		if(wizardSettings == null)
		{
			wizardSettings = peritoSettings.addNewSection("OrganizacaoWizard");
			setDialogSettings(peritoSettings);
		}
	}

	@Override
	public void addPages()
	{
		setWindowTitle("Anexo");
		anexoWizardPage = new AnexoWizardPage(anexo);
		addPage(anexoWizardPage);
		
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	anexoWizardPage.setEntityObject();
                }
            }); 
     
            AnexoDelegate delegate = AnexoDelegate.getInstance();
			delegate.inserir(anexo);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
