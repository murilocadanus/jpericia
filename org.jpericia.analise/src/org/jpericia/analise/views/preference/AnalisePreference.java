package org.jpericia.analise.views.preference;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.widgets.Shell;
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.analise.views.AnaliseView;
import org.jpericia.analise.views.preference.pages.AnaliseDadosPreferencePage;
import org.jpericia.analise.views.preference.pages.AnaliseFinalizadaPreferencePage;
import org.jpericia.analise.views.preference.pages.AnalisePericiaPreferencePage;
import org.jpericia.analise.views.preference.pages.AnalisePeritoPreferencePage;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.core.exception.BusinessDelegateException;

public class AnalisePreference extends PreferenceDialog
{
	private AnaliseView analiseView;
	
	private Analise analise;
	
	private boolean finalizar;
	
	private AnalisePericiaPreferencePage analisePericiaPreferencePage;
	
	private AnaliseDadosPreferencePage analiseDadosPreferencePage;
	
	private AnalisePeritoPreferencePage analisePeritoPreferencePage;
	
	private AnaliseFinalizadaPreferencePage analiseFinalizadaPreferencePage;
	
	public AnalisePreference(AnaliseView analiseView, Shell parentShell, boolean finalizar)
	{
		super(parentShell, new PreferenceManager());
		this.analise = analiseView.getAnalise();
		this.analiseView = analiseView;
		this.finalizar = finalizar;
		
		if(finalizar)
		{
			analiseFinalizadaPreferencePage = new AnaliseFinalizadaPreferencePage(analise);
			getPreferenceManager().addToRoot(new PreferenceNode("analise", analiseFinalizadaPreferencePage));
		}
		else
		{
			analisePericiaPreferencePage = new AnalisePericiaPreferencePage(analise);
			getPreferenceManager().addToRoot(new PreferenceNode("analise", analisePericiaPreferencePage));
			
			analiseDadosPreferencePage = new AnaliseDadosPreferencePage(analise);
			getPreferenceManager().addToRoot(new PreferenceNode("analise", analiseDadosPreferencePage));
			
			analisePeritoPreferencePage = new AnalisePeritoPreferencePage(analise);
			getPreferenceManager().addToRoot(new PreferenceNode("analise", analisePeritoPreferencePage));
		}
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        if(finalizar)
        {
        	newShell.setText("Finalizar análise");
        }
        else
        {
        	newShell.setText("Atualizar análise");
        }
    }

	@Override
	protected void okPressed()
	{
		SafeRunnable.run(new SafeRunnable()
		{
			public void run()
			{
				handleSave();
				close();
			}
		});
	}

	@Override
	protected void handleSave()
	{
		try
		{
			//Adiciona o valor do campo da page na entity.
			if(finalizar)
			{
				analiseFinalizadaPreferencePage.setEntityObject();
			}
			else
			{
				analisePericiaPreferencePage.setEntityObject();
				analiseDadosPreferencePage.setEntityObject();
				analisePeritoPreferencePage.setEntityObject();
			}
			
			AnaliseDelegate.getInstance().atualizar(analise);
			super.handleSave();
			
			//Atualiza a lista.
			analiseView.getViewer().setInput(AnaliseDelegate.getInstance().pesquisar());
			analiseView.getViewer().refresh();
		}
		catch (BusinessDelegateException e)
		{
            Policy.getLog().log(new Status(IStatus.ERROR, Policy.JFACE, 0, e.toString(), e));

            setSelectedNodePreference(null);
            String message = JFaceResources.getString("SafeRunnable.errorMessage"); //$NON-NLS-1$
            MessageDialog.openError(getShell(), JFaceResources.getString("Error"), message); //$NON-NLS-1$
		}
	}

}
