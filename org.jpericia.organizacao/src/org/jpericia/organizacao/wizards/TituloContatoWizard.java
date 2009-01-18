package org.jpericia.organizacao.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.organizacao.businessdelegate.TituloContatoDelegate;
import org.jpericia.organizacao.wizards.pages.TituloContatoWizardPage;

public class TituloContatoWizard extends AbstractWizard
{
	TituloContatoWizardPage tituloContatoWizardPage;

	TituloContato tituloContato = new TituloContato();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Título Contato");
		this.tituloContatoWizardPage = new TituloContatoWizardPage(tituloContato);
		
		addPage(tituloContatoWizardPage);
	}

	@Override
	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tituloContatoWizardPage.setEntityObject();
                }
            });
            
            TituloContatoDelegate delegate = TituloContatoDelegate.getInstance();
			delegate.inserir(tituloContato);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
