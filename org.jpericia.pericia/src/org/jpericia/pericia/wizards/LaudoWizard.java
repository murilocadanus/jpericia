package org.jpericia.pericia.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.pericia.PericiaPlugin;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.views.PericiaView;
import org.jpericia.pericia.wizards.pages.LaudoWizardPage;

public class LaudoWizard extends AbstractWizard
{
	LaudoWizardPage laudoWizardPage;
	
	PericiaView periciaView;
	
	//Laudo laudo = new Laudo();
	Pericia pericia;
	
	public LaudoWizard(PericiaView periciaView)
	{
		this.periciaView = periciaView;
		this.pericia = this.periciaView.getPericia();
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
		setWindowTitle("Laudo");
		laudoWizardPage = new LaudoWizardPage(pericia);
		addPage(laudoWizardPage);
		
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	laudoWizardPage.setEntityObject();
                }
            }); 
     
            PericiaDelegate delegate = PericiaDelegate.getInstance();
			delegate.atualizar(pericia);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
