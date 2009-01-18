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
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.organizacao.views.OrganizacaoView;
import org.jpericia.organizacao.views.preference.pages.OrganizacaoDadosPreferencePage;
import org.jpericia.organizacao.views.preference.pages.OrganizacaoTituloPreferencePage;

public class OrganizacaoPreference extends PreferenceDialog
{
	private OrganizacaoView organizacaoView;
	
	private Organizacao organizacao;
	
	private OrganizacaoTituloPreferencePage organizacaoTituloPreferencePage;
	
	private OrganizacaoDadosPreferencePage organizacaoDadosPreferencePage;
	
	public OrganizacaoPreference(OrganizacaoView organizacaoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.organizacaoView = organizacaoView;
		this.organizacao = organizacaoView.getOrganizacao();
		
		organizacaoTituloPreferencePage = new OrganizacaoTituloPreferencePage(organizacao);
		getPreferenceManager().addToRoot(new PreferenceNode("organizacao", organizacaoTituloPreferencePage));
		
		organizacaoDadosPreferencePage = new OrganizacaoDadosPreferencePage(organizacao);
		getPreferenceManager().addToRoot(new PreferenceNode("organizacao", organizacaoDadosPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Organização");
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
           	organizacaoTituloPreferencePage.setEntityObject();     		
            organizacaoDadosPreferencePage.setEntityObject(); 
			
			OrganizacaoDelegate.getInstance().atualizar(organizacao);
			super.handleSave();
			
			//Atualiza a lista.
			organizacaoView.getViewer().setInput(OrganizacaoDelegate.getInstance().pesquisar());
			organizacaoView.getViewer().refresh();
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
