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
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.organizacao.businessdelegate.ContatoOrganizacaoDelegate;
import org.jpericia.organizacao.views.ContatoOrganizacaoView;
import org.jpericia.organizacao.views.preference.pages.ContatoOrganizacaoDadosPreferencePage;
import org.jpericia.organizacao.views.preference.pages.ContatoOrganizacaoTituloPreferencePage;

public class ContatoOrganizacaoPreference extends PreferenceDialog
{
	private ContatoOrganizacao contatoOrganizacao;
	
	private ContatoOrganizacaoView contatoOrganizacaoView;
	
	private ContatoOrganizacaoDadosPreferencePage contatoOrganizacaoDadosPreferencePage;
	
	private ContatoOrganizacaoTituloPreferencePage contatoOrganizacaoTituloPreferencePage;
	
	public ContatoOrganizacaoPreference(ContatoOrganizacaoView contatoOrganizacaoView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.contatoOrganizacaoView = contatoOrganizacaoView;
		this.contatoOrganizacao = contatoOrganizacaoView.getContatoOrganizacao();

		contatoOrganizacaoTituloPreferencePage = new ContatoOrganizacaoTituloPreferencePage(contatoOrganizacao);
		getPreferenceManager().addToRoot(new PreferenceNode("contatoOrganizacao", contatoOrganizacaoTituloPreferencePage));
		
		contatoOrganizacaoDadosPreferencePage = new ContatoOrganizacaoDadosPreferencePage(contatoOrganizacao);
		getPreferenceManager().addToRoot(new PreferenceNode("contatoOrganizacao", contatoOrganizacaoDadosPreferencePage));
	}
	
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Atualizar Contato Organização");
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
			contatoOrganizacaoTituloPreferencePage.setEntityObject();
			contatoOrganizacaoDadosPreferencePage.setEntityObject();
			
			ContatoOrganizacaoDelegate.getInstance().atualizar(contatoOrganizacao);
			super.handleSave();
			
			//Atualiza a lista.
			contatoOrganizacaoView.getViewer().setInput(ContatoOrganizacaoDelegate.getInstance().pesquisar());
			contatoOrganizacaoView.getViewer().refresh();
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
