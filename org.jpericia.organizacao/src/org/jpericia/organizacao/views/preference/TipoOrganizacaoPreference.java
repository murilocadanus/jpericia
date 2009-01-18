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
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.TipoOrganizacaoDelegate;
import org.jpericia.organizacao.views.TipoOrganizacaoView;
import org.jpericia.organizacao.views.preference.pages.TipoOrganizacaoPreferencePage;

public class TipoOrganizacaoPreference extends PreferenceDialog
{
	private TipoOrganizacao tipoOrganizacao;
	
	private TipoOrganizacaoView tipoOrganizacaoView;
	
	private TipoOrganizacaoPreferencePage tipoOrganizacaoPreferencePage;
	
	public TipoOrganizacaoPreference(TipoOrganizacaoView tipoOrganizacaoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.tipoOrganizacaoView = tipoOrganizacaoView;
		this.tipoOrganizacao = tipoOrganizacaoView.getTipoOrganizacao();
		
		tipoOrganizacaoPreferencePage = new TipoOrganizacaoPreferencePage(tipoOrganizacao);
		getPreferenceManager().addToRoot(new PreferenceNode("tipoOrganizacao", tipoOrganizacaoPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Tipo Organização");
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
			tipoOrganizacaoPreferencePage.setEntityObject(); 
			
			TipoOrganizacaoDelegate.getInstance().atualizar(tipoOrganizacao);
			super.handleSave();
			
			//Atualiza a lista.
			tipoOrganizacaoView.getViewer().setInput(TipoOrganizacaoDelegate.getInstance().pesquisar());
			tipoOrganizacaoView.getViewer().refresh();

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
