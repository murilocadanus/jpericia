package org.jpericia.perito.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.perito.businessdelegate.FuncaoPeritoDelegate;
import org.jpericia.perito.wizards.pages.FuncaoPeritoWizardPage;

public class FuncaoPeritoWizard extends AbstractWizard
{
	private FuncaoPeritoWizardPage funcaoPeritoWizardPage;

	private FuncaoPerito funcaoPerito = new FuncaoPerito();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Função Perito");
		funcaoPeritoWizardPage = new FuncaoPeritoWizardPage(funcaoPerito);
		
		addPage(funcaoPeritoWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			FuncaoPeritoDelegate delegate = FuncaoPeritoDelegate.getInstance();

			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	funcaoPeritoWizardPage.setEntityObject();
                }
            });
			
			delegate.inserir(funcaoPerito);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
