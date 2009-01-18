package org.jpericia.organizacao.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.organizacao.OrganizacaoPlugin;
import org.jpericia.organizacao.businessdelegate.ContatoOrganizacaoDelegate;
import org.jpericia.organizacao.wizards.pages.ContatoOrganizacaoDadosWizardPage;
import org.jpericia.organizacao.wizards.pages.ContatoOrganizacaoTitulolWizardPage;

public class ContatoOrganizacaoWizard extends AbstractWizard
{
	ContatoOrganizacaoTitulolWizardPage tituloWizardPage;
	
	ContatoOrganizacaoDadosWizardPage dadosWizardPage;
	
	ContatoOrganizacao contatoOrganizacao = new ContatoOrganizacao();
	
	public ContatoOrganizacaoWizard()
	{
		IDialogSettings peritoSettings = OrganizacaoPlugin.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = peritoSettings.getSection("ContatoOrganizacaoWizard");
		
		if(wizardSettings == null)
		{
			wizardSettings = peritoSettings.addNewSection("ContatoOrganizacaoWizard");
			setDialogSettings(peritoSettings);
		}
	}

	@Override
	public void addPages()
	{
		setWindowTitle("Contato Organização");
		tituloWizardPage = new ContatoOrganizacaoTitulolWizardPage(contatoOrganizacao);
		addPage(tituloWizardPage);
		
		dadosWizardPage = new ContatoOrganizacaoDadosWizardPage(contatoOrganizacao);
		addPage(dadosWizardPage);
		
		tituloWizardPage.init(initialSelection);
		
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{			
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tituloWizardPage.setEntityObject();
                }
            });
            		
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	dadosWizardPage.setEntityObject();
                }
            });            		
			
			ContatoOrganizacaoDelegate delegate = ContatoOrganizacaoDelegate.getInstance();
			delegate.inserir(contatoOrganizacao);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}		
	}

}
