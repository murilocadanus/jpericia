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
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.TipoObjetoDelegate;
import org.jpericia.objeto.views.TipoObjetoView;
import org.jpericia.objeto.views.preference.pages.TipoObjetoPreferencePage;

public class TipoObjetoPreference extends PreferenceDialog
{
	private TipoObjetoView tipoObjetoView;
	
	private TipoObjeto tipoObjeto;
	
	private TipoObjetoPreferencePage tipoObjetoPreferencePage;
	
	public TipoObjetoPreference(TipoObjetoView tipoObjetoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.tipoObjetoView = tipoObjetoView;
		this.tipoObjeto = tipoObjetoView.getTipoObjeto();
		
		tipoObjetoPreferencePage = new TipoObjetoPreferencePage(tipoObjeto);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", tipoObjetoPreferencePage));
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
			tipoObjetoPreferencePage.setEntityObject();  
			
			TipoObjetoDelegate.getInstance().atualizar(tipoObjeto);
			super.handleSave();
			
			//Atualiza a lista.
			tipoObjetoView.getViewer().setInput(TipoObjetoDelegate.getInstance().pesquisar());
			tipoObjetoView.getViewer().refresh();
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
