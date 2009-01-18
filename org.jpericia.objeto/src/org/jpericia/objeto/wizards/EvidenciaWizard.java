package org.jpericia.objeto.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;
import org.jpericia.objeto.businessdelegate.EvidenciaDelegate;
import org.jpericia.objeto.wizards.pages.EvidenciaWizardPage;

public class EvidenciaWizard extends AbstractWizard
{
	private EvidenciaWizardPage evidenciaWizardPage;

	private Evidencia evidencia = new Evidencia();
	
	@Override
	public void addPages()
	{
		setWindowTitle("Tipo evidencia");
		evidenciaWizardPage = new EvidenciaWizardPage(evidencia);
		
		addPage(evidenciaWizardPage);
	}

	protected void performOperation(IProgressMonitor monitor)
	{
		try
		{
			EvidenciaDelegate delegate = EvidenciaDelegate.getInstance();

			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	evidenciaWizardPage.setEntityObject();
                }
            });
			
			delegate.inserir(evidencia);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}
	}
}
