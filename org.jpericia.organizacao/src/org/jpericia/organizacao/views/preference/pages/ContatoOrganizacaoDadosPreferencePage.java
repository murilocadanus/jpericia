package org.jpericia.organizacao.views.preference.pages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;

public class ContatoOrganizacaoDadosPreferencePage extends PreferencePage
{
	private Text nomeTxt;
	
	private Text telefoneTxt;
	
	private Text celularTxt;
	
	private Text emailTxt;
	
	private ContatoOrganizacao contatoOrganizacao;
	
	public ContatoOrganizacaoDadosPreferencePage()
	{
		super("dadosContato");
		setTitle("Contato Organização - Dados pessoais");
		setDescription("Informe os dados do contato da organização.");
	}
	
	public ContatoOrganizacaoDadosPreferencePage(ContatoOrganizacao contatoOrganizacao)
	{
		super("dadosContato");
		setTitle("Contato Organização - Dados pessoais");
		setDescription("Informe os dados do contato da organização.");
		this.contatoOrganizacao = contatoOrganizacao;
	}
	
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo nome
		final Label nomeLbl = new Label(container, SWT.NONE);
		nomeLbl.setLayoutData(gridDataLbl);
		nomeLbl.setText("Nome:");
		
		nomeTxt = new Text(container, SWT.BORDER);
		nomeTxt.setText(contatoOrganizacao.getNome());
		nomeTxt.setTextLimit(50);
		nomeTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		nomeTxt.setLayoutData(gridDataTxt);
		
		// Campo telefone
		final Label telefoneLbl = new Label(container, SWT.NONE);
		telefoneLbl.setLayoutData(gridDataLbl);
		telefoneLbl.setText("Telefone:");
		
		telefoneTxt = new Text(container, SWT.BORDER);
		telefoneTxt.setText(contatoOrganizacao.getTelefone().toString());
		telefoneTxt.setTextLimit(10);
		telefoneTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		telefoneTxt.setLayoutData(gridDataTxt);
		
		// Campo celular
		final Label celularLbl = new Label(container, SWT.NONE);
		celularLbl.setLayoutData(gridDataLbl);
		celularLbl.setText("Celular:");
		
		celularTxt = new Text(container, SWT.BORDER);
		celularTxt.setText(contatoOrganizacao.getCelular().toString());
		celularTxt.setTextLimit(10);
		celularTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		celularTxt.setLayoutData(gridDataTxt);
		
		// Campo email
		final Label emailLbl = new Label(container, SWT.NONE);
		emailLbl.setLayoutData(gridDataLbl);
		emailLbl.setText("E-mail:");
		
		emailTxt = new Text(container, SWT.BORDER);
		emailTxt.setText(contatoOrganizacao.getEmail());
		emailTxt.setTextLimit(40);
		emailTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		emailTxt.setLayoutData(gridDataTxt);
				
		initContents();
		return container;
	}
	
	public void init(ISelection selection)
	{
		if(!(selection instanceof IStructuredSelection))
		{
			return;
		}
	}
	
	private void initContents()
	{
		updatePageComplete();
		setMessage(null);
		setErrorMessage(null);
	}
	
	private void updatePageComplete()
	{
		setValid(false);
		
		if("".equals(nomeTxt.getText()))
		{
			setMessage(null);
			setErrorMessage("É necesário informar o campo nome para incluir o contato.");
			return;
		}
		else if("".equals(telefoneTxt.getText()))
		{
			setMessage(null);
			setErrorMessage("É necesário informar o campo telefone para incluir o contato.");
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
		if (nomeTxt != null)
		{
			contatoOrganizacao.setNome(nomeTxt.getText());
			contatoOrganizacao.setTelefone(Long.parseLong(telefoneTxt.getText()));
			contatoOrganizacao.setCelular(Long.parseLong(celularTxt.getText()));
			contatoOrganizacao.setEmail(emailTxt.getText());
		}
	}

}
