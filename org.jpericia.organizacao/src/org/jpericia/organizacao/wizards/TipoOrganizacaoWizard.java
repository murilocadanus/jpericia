package org.jpericia.organizacao.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.organizacao.businessdelegate.TipoOrganizacaoDelegate;
import org.jpericia.organizacao.wizards.pages.TipoOrganizacaoWizardPage;

public class TipoOrganizacaoWizard extends AbstractWizard
{
	TipoOrganizacaoWizardPage tipoOrganizacaoWizardPage;
	
	TipoOrganizacao tipoOrganizacao = new TipoOrganizacao();

	@Override
	public void addPages()
	{
		setWindowTitle("Tipo Organização");
		this.tipoOrganizacaoWizardPage = new TipoOrganizacaoWizardPage(tipoOrganizacao);
		
		addPage(tipoOrganizacaoWizardPage);
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tipoOrganizacaoWizardPage.setEntityObject();
                }
            });
            
            TipoOrganizacaoDelegate delegate = TipoOrganizacaoDelegate.getInstance();
			delegate.inserir(tipoOrganizacao);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
