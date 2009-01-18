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
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.ObjetoDelegate;
import org.jpericia.objeto.views.ObjetoView;
import org.jpericia.objeto.views.preference.pages.ObjetoAnalisePreferencePage;
import org.jpericia.objeto.views.preference.pages.ObjetoDadosPreferencePage;

public class ObjetoPreference extends PreferenceDialog
{
	private ObjetoView objetoView;
	
	private Objeto objeto;
	
	private ObjetoDadosPreferencePage objetoDadosPreferencePage;
	
	private ObjetoAnalisePreferencePage objetoAnalisePreferencePage;
	
	public ObjetoPreference(ObjetoView objetoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.objetoView = objetoView;
		this.objeto = objetoView.getObjeto();
		
		objetoDadosPreferencePage = new ObjetoDadosPreferencePage(objeto);
		getPreferenceManager().addToRoot(new PreferenceNode("objeto", objetoDadosPreferencePage));
		
		objetoAnalisePreferencePage = new ObjetoAnalisePreferencePage(objeto);
		getPreferenceManager().addToRoot(new PreferenceNode("objeto", objetoAnalisePreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Objeto");
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
			objetoDadosPreferencePage.setEntityObject();
			objetoAnalisePreferencePage.setEntityObject();
			
			ObjetoDelegate.getInstance().atualizar(objeto);
			super.handleSave();
			
			//Atualiza a lista.
			objetoView.getViewer().setInput(ObjetoDelegate.getInstance().pesquisar());
			objetoView.getViewer().refresh();
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
