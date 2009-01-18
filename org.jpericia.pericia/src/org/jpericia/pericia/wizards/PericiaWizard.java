package org.jpericia.pericia.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.pericia.PericiaPlugin;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.wizards.pages.PericiaDadosWizardPage;
import org.jpericia.pericia.wizards.pages.PericiaProprietarioWizardPage;

public class PericiaWizard extends AbstractWizard
{
	PericiaProprietarioWizardPage periciaProprietarioWizardPage;
	
	PericiaDadosWizardPage periciaDadosWizardPage;
	
	Pericia pericia = new Pericia();
	
	public PericiaWizard()
	{
		IDialogSettings periciaSettings = PericiaPlugin.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = periciaSettings.getSection("OrganizacaoWizard");
		
		if(wizardSettings == null)
		{
			wizardSettings = periciaSettings.addNewSection("OrganizacaoWizard");
			setDialogSettings(periciaSettings);
		}
	}

	@Override
	public void addPages()
	{
		setWindowTitle("Pericia");
		periciaProprietarioWizardPage = new PericiaProprietarioWizardPage(pericia);
		addPage(periciaProprietarioWizardPage);
		
		periciaDadosWizardPage = new PericiaDadosWizardPage(pericia);
		addPage(periciaDadosWizardPage);
		
		periciaProprietarioWizardPage.init(initialSelection);
		
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	periciaProprietarioWizardPage.setEntityObject();
                }
            }); 
     
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	periciaDadosWizardPage.setEntityObject();
                }
            });
            
            PericiaDelegate delegate = PericiaDelegate.getInstance();
			delegate.inserir(pericia);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
