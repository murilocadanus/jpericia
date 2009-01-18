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
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.FuncaoPeritoDelegate;
import org.jpericia.perito.views.FuncaoPeritoView;
import org.jpericia.perito.views.preference.pages.FuncaoPeritoPreferencePage;

public class FuncaoPeritoPreference extends PreferenceDialog
{
	private FuncaoPeritoView funcaoPeritoView;
	
	private FuncaoPerito funcaoPerito;
	
	private FuncaoPeritoPreferencePage funcaoPeritoPreferencePage;
	
	public FuncaoPeritoPreference(FuncaoPeritoView funcaoPeritoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.funcaoPeritoView = funcaoPeritoView;
		this.funcaoPerito = funcaoPeritoView.getFuncaoPerito();
		
		funcaoPeritoPreferencePage = new FuncaoPeritoPreferencePage(funcaoPerito);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", funcaoPeritoPreferencePage));
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
			funcaoPeritoPreferencePage.setEntityObject();  
			
			FuncaoPeritoDelegate.getInstance().atualizar(funcaoPerito);
			super.handleSave();
			
			//Atualiza a lista.
			funcaoPeritoView.getViewer().setInput(FuncaoPeritoDelegate.getInstance().pesquisar());
			funcaoPeritoView.getViewer().refresh();
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
