package org.jpericia.perito.views.preference;

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
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.TituloPeritoDelegate;
import org.jpericia.perito.views.TituloPeritoView;
import org.jpericia.perito.views.preference.pages.TituloPeritoPreferencePage;

public class TituloPeritoPreference extends PreferenceDialog
{
	private TituloPeritoView tituloPeritoView;
	
	private TituloPerito tituloPerito;
	
	private TituloPeritoPreferencePage tituloPeritoPreferencePage;
	
	public TituloPeritoPreference(TituloPeritoView tituloPeritoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.tituloPerito = tituloPeritoView.getTituloPerito();
		this.tituloPeritoView = tituloPeritoView;
		
		tituloPeritoPreferencePage = new TituloPeritoPreferencePage(tituloPerito);
		getPreferenceManager().addToRoot(new PreferenceNode("tituloPerito", tituloPeritoPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Perito");
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
			tituloPeritoPreferencePage.setEntityObject(); 
			
			TituloPeritoDelegate.getInstance().atualizar(tituloPerito);
			super.handleSave();
			
			//Atualiza a lista.
			tituloPeritoView.getViewer().setInput(TituloPeritoDelegate.getInstance().pesquisar());
			tituloPeritoView.getViewer().refresh();
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
