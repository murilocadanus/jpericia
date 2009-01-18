package org.jpericia.objeto.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.objeto.businessdelegate.CategoriaTipoObjetoDelegate;
import org.jpericia.objeto.wizards.pages.CategoriaTipoObjetoWizardPage;

public class CategoriaTipoObjetoWizard extends AbstractWizard
{
	private CategoriaTipoObjetoWizardPage categoriaTipoObjetoWizardPage;

	private CategoriaTipoObjeto categoriaTipoObjeto = new CategoriaTipoObjeto();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Categoria tipo objeto");
		categoriaTipoObjetoWizardPage = new CategoriaTipoObjetoWizardPage(categoriaTipoObjeto);
		
		addPage(categoriaTipoObjetoWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			CategoriaTipoObjetoDelegate delegate = CategoriaTipoObjetoDelegate.getInstance();

			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	categoriaTipoObjetoWizardPage.setEntityObject();
                }
            });
			
			delegate.inserir(categoriaTipoObjeto);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
