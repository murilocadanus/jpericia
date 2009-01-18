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
import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.pericia.bussinessdelegate.AnexoDelegate;
import org.jpericia.pericia.views.AnexoView;
import org.jpericia.pericia.views.preference.pages.AnexoPreferencePage;

public class AnexoPreference extends PreferenceDialog
{
	private AnexoView anexoView;
	
	private Anexo anexo;
	
	private AnexoPreferencePage anexoPreferencePage;

	public AnexoPreference(AnexoView anexoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.anexoView = anexoView;
		this.anexo = anexoView.getAnexo();
		
		anexoPreferencePage = new AnexoPreferencePage(anexo);
		getPreferenceManager().addToRoot(new PreferenceNode("anexo", anexoPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Anexo");
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
			anexoPreferencePage.setEntityObject();
			
			AnexoDelegate.getInstance().atualizar(anexo);
			super.handleSave();
			
			//Atualiza a lista.
			anexoView.getViewer().setInput(AnexoDelegate.getInstance().pesquisar());
			anexoView.getViewer().refresh();
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
