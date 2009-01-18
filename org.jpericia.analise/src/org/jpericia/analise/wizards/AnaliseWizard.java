package org.jpericia.analise.wizards;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Display;
import org.jpericia.analise.AnalisePlugin;
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.analise.wizards.pages.AnaliseDadosWizardPage;
import org.jpericia.analise.wizards.pages.AnalisePericiaWizardPage;
import org.jpericia.analise.wizards.pages.AnalisePeritoWizardPage;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.wizards.AbstractWizard;

public class AnaliseWizard extends AbstractWizard
{
	private AnalisePericiaWizardPage analisePericiaWizardPage;
	
	private AnaliseDadosWizardPage analiseDadosWizardPage;
	
	private AnalisePeritoWizardPage analisePeritoWizardPage;

	private Analise analise = new Analise();
	
	public AnaliseWizard()
	{
		IDialogSettings analiseSettings = AnalisePlugin.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = analiseSettings.getSection("AnaliseWizard");
		
		if(wizardSettings == null)
		{
			wizardSettings = analiseSettings.addNewSection("AnaliseWizard");
			setDialogSettings(analiseSettings);
		}
	}

	@Override
	public void addPages()
	{
		setWindowTitle("Dados Análise");
		analisePericiaWizardPage = new AnalisePericiaWizardPage(analise);
		addPage(analisePericiaWizardPage);	
				
		analiseDadosWizardPage = new AnaliseDadosWizardPage(analise);
		addPage(analiseDadosWizardPage);
		
		analisePeritoWizardPage = new AnalisePeritoWizardPage(analise);
		addPage(analisePeritoWizardPage);
			
		analisePericiaWizardPage.init(initialSelection);
	}

	protected void performOperation(IProgressMonitor monitor)
	{		
		try
		{
			//Adiciona os valores dos campos das pages na entity.
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	analisePericiaWizardPage.setEntityObject();
                }
            });
            		
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	analiseDadosWizardPage.setEntityObject();
                }
            });
            
            Display.getDefault().syncExec(new Runnable() {
                public void run() {
                	analisePeritoWizardPage.setEntityObject();
                }
            });
			
			AnaliseDelegate delegate = AnaliseDelegate.getInstance();
			delegate.inserir(analise);
		}
		catch(BusinessDelegateException e)
		{
			//TODO implementar
		}	
	}
}
