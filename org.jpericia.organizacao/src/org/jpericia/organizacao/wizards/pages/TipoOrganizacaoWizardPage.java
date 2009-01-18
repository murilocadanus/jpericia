package org.jpericia.organizacao.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.organizacao.messages.Messages;

public class TipoOrganizacaoWizardPage extends WizardPage
{
	private TipoOrganizacao tipoOrganizacao;
	
	private Text tipoOrganizacaoTxt;
	
	public TipoOrganizacaoWizardPage()
	{
		super("tipoOrganizacao");
		setTitle(Messages.tituloTipoOrganizacaoWizard);
		setDescription(Messages.subtituloTipoOrganizacaoWizard);
	}
	
	public TipoOrganizacaoWizardPage(TipoOrganizacao tipoOrganizacao)
	{
		super("tipoOrganizacao");
		setTitle(Messages.tituloTipoOrganizacaoWizard);
		setDescription(Messages.subtituloTipoOrganizacaoWizard);
		this.tipoOrganizacao = tipoOrganizacao;
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
			
		final Label tipoOrganizacaoLbl = new Label(container, SWT.NONE);
		final GridData gridDataTitulo = new GridData(GridData.HORIZONTAL_ALIGN_END);
		tipoOrganizacaoLbl.setLayoutData(gridDataTitulo);
		tipoOrganizacaoLbl.setText(Messages.tipoOrganizacao);
		
		tipoOrganizacaoTxt = new Text(container, SWT.BORDER);
		tipoOrganizacaoTxt.setTextLimit(40);
		tipoOrganizacaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tipoOrganizacaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		initContents();
	}
	
	private void initContents()
	{
		updatePageComplete();
		setMessage(null);
		setErrorMessage(null);
	}
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
		if("".equals(tipoOrganizacaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoOrganizacao);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
		tipoOrganizacao.setNome(tipoOrganizacaoTxt.getText());
	}
}