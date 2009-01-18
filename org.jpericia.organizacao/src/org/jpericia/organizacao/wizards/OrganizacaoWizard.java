package org.jpericia.organizacao.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.organizacao.OrganizacaoPlugin;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.organizacao.wizards.pages.OrganizacaoDadosWizardPage;
import org.jpericia.organizacao.wizards.pages.OrganizacaoTitulolWizardPage;

public class OrganizacaoWizard extends AbstractWizard
{
	OrganizacaoTitulolWizardPage titulolWizardPage;
	
	OrganizacaoDadosWizardPage dadosWizardPage;
	
	Organizacao organizacao = new Organizacao();
	
	public OrganizacaoWizard()
	{
		IDialogSettings peritoSettings = OrganizacaoPlugin.getDefault().getDialogSettings();
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
		setWindowTitle("Organização");
		titulolWizardPage = new OrganizacaoTitulolWizardPage(organizacao);
		addPage(titulolWizardPage);
		
		dadosWizardPage = new OrganizacaoDadosWizardPage(organizacao);
		addPage(dadosWizardPage);
		
		titulolWizardPage.init(initialSelection);
		
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	titulolWizardPage.setEntityObject();
                }
            }); 
			
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	dadosWizardPage.setEntityObject();
                }
            });
     
            OrganizacaoDelegate delegate = OrganizacaoDelegate.getInstance();
			delegate.inserir(organizacao);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
