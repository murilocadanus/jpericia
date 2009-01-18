package org.jpericia.pericia.views.preference;

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
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.views.PericiaView;
import org.jpericia.pericia.views.preference.pages.LaudoPreferencePage;

public class LaudoPreference extends PreferenceDialog
{
	private PericiaView periciaView;
	
	private Pericia pericia;
	
	private LaudoPreferencePage laudoPreferencePage;

	public LaudoPreference(PericiaView periciaView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.periciaView = periciaView;
		this.pericia = periciaView.getPericia();
		
		laudoPreferencePage = new LaudoPreferencePage(pericia);
		getPreferenceManager().addToRoot(new PreferenceNode("pericia", laudoPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Pericia");
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
			//Adiciona os valores dos campos das pages na entity.
			laudoPreferencePage.setEntityObject();
			
			PericiaDelegate.getInstance().atualizar(pericia);
			super.handleSave();
			
			//Atualiza a lista.
			periciaView.getViewer().setInput(PericiaDelegate.getInstance().pesquisar());
			periciaView.getViewer().refresh();
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
