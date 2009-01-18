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
import org.jpericia.analise.businessdelegate.CenaDelegate;
import org.jpericia.analise.views.CenaView;
import org.jpericia.analise.views.preference.pages.CenaDadosPreferencePage;
import org.jpericia.analise.views.preference.pages.CenaImagemPreferencePage;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.core.exception.BusinessDelegateException;

public class CenaPreference extends PreferenceDialog
{
	private CenaView cenaView;
	
	private Cena cena;
	
	private CenaImagemPreferencePage cenaImagemPreferencePage;
	
	private CenaDadosPreferencePage cenaDadosPreferencePage;
	
	public CenaPreference(CenaView cenaView, Shell parentShell)
	{
		super(parentShell, new PreferenceManager());
		this.cena = cenaView.getCena();
		this.cenaView = cenaView;
		
		cenaImagemPreferencePage = new CenaImagemPreferencePage(cena);
		getPreferenceManager().addToRoot(new PreferenceNode("cena", cenaImagemPreferencePage));
		
		cenaDadosPreferencePage = new CenaDadosPreferencePage(cena);
		getPreferenceManager().addToRoot(new PreferenceNode("cena", cenaDadosPreferencePage));
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
			cenaImagemPreferencePage.setEntityObject();
			
			cenaDadosPreferencePage.setEntityObject(); 
			
			CenaDelegate.getInstance().atualizar(cena);
			super.handleSave();
			
			//Atualiza a lista.
			cenaView.getViewer().setInput(CenaDelegate.getInstance().pesquisar());
			cenaView.getViewer().refresh();
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
