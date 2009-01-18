package org.jpericia.organizacao.views.preference;

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
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.TituloContatoDelegate;
import org.jpericia.organizacao.views.TituloContatoView;
import org.jpericia.organizacao.views.preference.pages.TituloContatoPreferencePage;

public class TituloContatoPreference extends PreferenceDialog
{
	private TituloContatoView tituloContatoView;
	
	private TituloContato tituloContato;
	
	private TituloContatoPreferencePage tituloContatoPreferencePage;
	
	public TituloContatoPreference(TituloContatoView tituloContatoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.tituloContatoView = tituloContatoView;
		this.tituloContato = tituloContatoView.getTituloContato();
		
		tituloContatoPreferencePage = new TituloContatoPreferencePage(tituloContato);
		getPreferenceManager().addToRoot(new PreferenceNode("tituloContato", tituloContatoPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Titulo Contato");
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
			tituloContatoPreferencePage.setEntityObject(); 
			
			TituloContatoDelegate.getInstance().atualizar(tituloContato);
			super.handleSave();
			
			//Atualiza a lista.
			tituloContatoView.getViewer().setInput(TituloContatoDelegate.getInstance().pesquisar());
			tituloContatoView.getViewer().refresh();
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
