package org.jpericia.perito.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.perito.PeritoPlugin;
import org.jpericia.perito.businessdelegate.PeritoDelegate;
import org.jpericia.perito.wizards.pages.PeritoPessoalWizardPage;
import org.jpericia.perito.wizards.pages.PeritoUsuarioWizardPage;

public class PeritoWizard extends AbstractWizard
{
	private PeritoUsuarioWizardPage usuarioWizardPage;

	private PeritoPessoalWizardPage pessoalWizardPage;
	
	private Perito perito = new Perito();
	
	public PeritoWizard()
	{
		IDialogSettings peritoSettings = PeritoPlugin.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = peritoSettings.getSection("PeritoWizard");
		
		if(wizardSettings == null)
		{
			wizardSettings = peritoSettings.addNewSection("PeritoWizard");
			setDialogSettings(peritoSettings);
		}
	}
	
	@Override
	public void addPages()
	{
		setWindowTitle("Dados Perito");
		usuarioWizardPage = new PeritoUsuarioWizardPage(perito); 
		addPage(usuarioWizardPage);
		
		pessoalWizardPage = new PeritoPessoalWizardPage(perito);
		addPage(pessoalWizardPage);
			
		usuarioWizardPage.init(initialSelection);
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{			
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	usuarioWizardPage.setEntityObject();
                }
            });
            		
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	pessoalWizardPage.setEntityObject();
                }
            });            		
			
			PeritoDelegate delegate = PeritoDelegate.getInstance();
			delegate.inserir(perito);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}		
	}
}
