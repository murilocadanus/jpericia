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
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.CategoriaTipoObjetoDelegate;
import org.jpericia.objeto.views.CategoriaTipoObjetoView;
import org.jpericia.objeto.views.preference.pages.CategoriaTipoObjetoPreferencePage;

public class CategoriaTipoObjetoPreference extends PreferenceDialog
{
	private CategoriaTipoObjetoView categoriaTipoObjetoView;
	
	private CategoriaTipoObjeto categoriaTipoObjeto;
	
	private CategoriaTipoObjetoPreferencePage categoriaTipoObjetoPreferencePage;
	
	public CategoriaTipoObjetoPreference(CategoriaTipoObjetoView categoriaTipoObjetoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.categoriaTipoObjetoView = categoriaTipoObjetoView;
		this.categoriaTipoObjeto = categoriaTipoObjetoView.getCategoriaTipoObjeto();
		
		categoriaTipoObjetoPreferencePage = new CategoriaTipoObjetoPreferencePage(categoriaTipoObjeto);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", categoriaTipoObjetoPreferencePage));
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
			categoriaTipoObjetoPreferencePage.setEntityObject();  
			
			CategoriaTipoObjetoDelegate.getInstance().atualizar(categoriaTipoObjeto);
			super.handleSave();
			
			//Atualiza a lista.
			categoriaTipoObjetoView.getViewer().setInput(CategoriaTipoObjetoDelegate.getInstance().pesquisar());
			categoriaTipoObjetoView.getViewer().refresh();
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
