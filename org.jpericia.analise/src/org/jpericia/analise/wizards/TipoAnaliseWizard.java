package org.jpericia.analise.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.analise.businessdelegate.TipoAnaliseDelegate;
import org.jpericia.analise.wizards.pages.TipoAnaliseWizardPage;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;

public class TipoAnaliseWizard extends AbstractWizard
{
	private TipoAnaliseWizardPage tipoAnaliseWizardPage;
	
	private TipoAnalise tipoAnalise = new TipoAnalise();

	@Override
	public void addPages()
	{
		setWindowTitle("Tipo Análise");
		tipoAnaliseWizardPage = new TipoAnaliseWizardPage(tipoAnalise);
		
		addPage(tipoAnaliseWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	tipoAnaliseWizardPage.setEntityObject();
                }
            });
            
            TipoAnaliseDelegate delegate = TipoAnaliseDelegate.getInstance();
			delegate.inserir(tipoAnalise);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}

}
