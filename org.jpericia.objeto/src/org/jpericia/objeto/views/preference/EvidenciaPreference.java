package org.jpericia.objeto.views.preference;

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
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.EvidenciaDelegate;
import org.jpericia.objeto.views.EvidenciaView;
import org.jpericia.objeto.views.preference.pages.EvidenciaPreferencePage;

public class EvidenciaPreference extends PreferenceDialog
{
	private EvidenciaView evidenciaView;
	
	private Evidencia evidencia;
	
	private EvidenciaPreferencePage evidenciaPreferencePage;
	
	public EvidenciaPreference(EvidenciaView evidenciaView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.evidenciaView = evidenciaView;
		this.evidencia = evidenciaView.getEvidencia();
		
		evidenciaPreferencePage = new EvidenciaPreferencePage(evidencia);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", evidenciaPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Evidencia");
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
			evidenciaPreferencePage.setEntityObject();  
			
			EvidenciaDelegate.getInstance().atualizar(evidencia);
			super.handleSave();
			
			//Atualiza a lista.
			evidenciaView.getViewer().setInput(EvidenciaDelegate.getInstance().pesquisar());
			evidenciaView.getViewer().refresh();
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
