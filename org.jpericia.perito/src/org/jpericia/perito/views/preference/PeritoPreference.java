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
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.PeritoDelegate;
import org.jpericia.perito.views.PeritoView;
import org.jpericia.perito.views.preference.pages.PeritoPessoalPreferencePage;
import org.jpericia.perito.views.preference.pages.PeritoUsuarioPreferencePage;

public class PeritoPreference extends PreferenceDialog
{
	private PeritoView peritoView;
	
	private Perito perito;
	
	private PeritoUsuarioPreferencePage peritoUsuarioPreferencePage;
	
	private PeritoPessoalPreferencePage peritoPessoalPreferencePage;

	public PeritoPreference(PeritoView peritoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.peritoView = peritoView;
		this.perito = peritoView.getPerito();
		
		
		peritoUsuarioPreferencePage = new PeritoUsuarioPreferencePage(perito);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", peritoUsuarioPreferencePage));
		
		peritoPessoalPreferencePage = new PeritoPessoalPreferencePage(perito);
		getPreferenceManager().addToRoot(new PreferenceNode("perito", peritoPessoalPreferencePage));
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
			//Adiciona os valores dos campos das pages na entity.
           	peritoUsuarioPreferencePage.setEntityObject();     		
            peritoPessoalPreferencePage.setEntityObject(); 
			
			PeritoDelegate.getInstance().atualizar(perito);
			super.handleSave();
			
			//Atualiza a lista.
			peritoView.getViewer().setInput(PeritoDelegate.getInstance().pesquisar());
			peritoView.getViewer().refresh();
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
