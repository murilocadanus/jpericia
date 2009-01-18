package org.jpericia.perito.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.perito.businessdelegate.TituloPeritoDelegate;
import org.jpericia.perito.wizards.pages.TituloPeritoWizardPage;

public class TituloPeritoWizard extends AbstractWizard
{
	private TituloPeritoWizardPage tituloPeritoWizardPage;
	
	private TituloPerito tituloPerito = new TituloPerito();

	@Override
	public void addPages()
	{
		setWindowTitle("Título Perito");
		tituloPeritoWizardPage = new TituloPeritoWizardPage(tituloPerito);
		
		addPage(tituloPeritoWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tituloPeritoWizardPage.setEntityObject();
                }
            });
            
            TituloPeritoDelegate delegate = TituloPeritoDelegate.getInstance();
			delegate.inserir(tituloPerito);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}

}
