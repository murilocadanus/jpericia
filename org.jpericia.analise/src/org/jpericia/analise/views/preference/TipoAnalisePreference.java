package org.jpericia.analise.views.preference;

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
import org.jpericia.analise.businessdelegate.TipoAnaliseDelegate;
import org.jpericia.analise.views.TipoAnaliseView;
import org.jpericia.analise.views.preference.pages.TipoAnalisePreferencePage;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.core.exception.BusinessDelegateException;

public class TipoAnalisePreference extends PreferenceDialog
{
	private TipoAnaliseView tipoAnaliseView;
	
	private TipoAnalise tipoAnalise;
	
	private TipoAnalisePreferencePage tipoAnalisePreferencePage;
	
	public TipoAnalisePreference(TipoAnaliseView tipoAnaliseView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.tipoAnalise = tipoAnaliseView.getTipoAnalise();
		this.tipoAnaliseView = tipoAnaliseView;
		
		tipoAnalisePreferencePage = new TipoAnalisePreferencePage(tipoAnalise);
		getPreferenceManager().addToRoot(new PreferenceNode("tipoAnalise", tipoAnalisePreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar tipo análise");
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
			tipoAnalisePreferencePage.setEntityObject(); 
			
			TipoAnaliseDelegate.getInstance().atualizar(tipoAnalise);
			super.handleSave();
			
			//Atualiza a lista.
			tipoAnaliseView.getViewer().setInput(TipoAnaliseDelegate.getInstance().pesquisar());
			tipoAnaliseView.getViewer().refresh();
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
