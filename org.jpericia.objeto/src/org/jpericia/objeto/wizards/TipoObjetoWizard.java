package org.jpericia.objeto.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.objeto.businessdelegate.TipoObjetoDelegate;
import org.jpericia.objeto.wizards.pages.TipoObjetoWizardPage;

public class TipoObjetoWizard extends AbstractWizard
{
	private TipoObjetoWizardPage tipoObjetoWizardPage;

	private TipoObjeto tipoObjeto = new TipoObjeto();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Tipo objeto");
		tipoObjetoWizardPage = new TipoObjetoWizardPage(tipoObjeto);
		
		addPage(tipoObjetoWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			TipoObjetoDelegate delegate = TipoObjetoDelegate.getInstance();

			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tipoObjetoWizardPage.setEntityObject();
                }
            });
			
			delegate.inserir(tipoObjeto);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
