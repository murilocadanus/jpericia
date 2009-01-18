package org.jpericia.objeto.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.objeto.businessdelegate.ObjetoDelegate;
import org.jpericia.objeto.wizards.pages.ObjetoAnaliseWizardPage;
import org.jpericia.objeto.wizards.pages.ObjetoDadosWizardPage;

public class ObjetoWizard extends AbstractWizard
{
	private ObjetoDadosWizardPage objetoDadosWizardPage;
	
	private ObjetoAnaliseWizardPage objetoAnaliseWizardPage;

	private Objeto objeto = new Objeto();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Tipo objeto");
		
		objetoDadosWizardPage = new ObjetoDadosWizardPage(objeto);
		addPage(objetoDadosWizardPage);
		
		objetoAnaliseWizardPage = new ObjetoAnaliseWizardPage(objeto);
		addPage(objetoAnaliseWizardPage);
		
		objetoDadosWizardPage.init(initialSelection);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			ObjetoDelegate delegate = ObjetoDelegate.getInstance();

			//Adiciona os valores dos campos das pages na entity.           
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	objetoDadosWizardPage.setEntityObject();
                }
            });
            
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	objetoAnaliseWizardPage.setEntityObject();
                }
            });
			
			delegate.inserir(objeto);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
