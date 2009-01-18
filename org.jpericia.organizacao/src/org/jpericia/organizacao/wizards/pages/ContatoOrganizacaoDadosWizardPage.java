package org.jpericia.organizacao.wizards.pages;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.organizacao.messages.Messages;

public class ContatoOrganizacaoDadosWizardPage extends WizardPage
{
	private Text nomeTxt;
	
	private FormattedText telefoneTxt;
	
	private FormattedText celularTxt;
	
	private Text emailTxt;
	
	private ContatoOrganizacao contatoOrganizacao;
	
	public ContatoOrganizacaoDadosWizardPage()
	{
		super("dadosContato");
		setTitle(Messages.tituloContatoOrganizacaoWizard);
		setDescription(Messages.subtituloContatoOrganizacaoDadosWizard);
	}
	
	public ContatoOrganizacaoDadosWizardPage(ContatoOrganizacao contatoOrganizacao)
	{
		super("dadosContato");
		setTitle(Messages.tituloContatoOrganizacaoWizard);
		setDescription(Messages.subtituloContatoOrganizacaoDadosWizard);
		this.contatoOrganizacao = contatoOrganizacao;
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo nome
		final Label nomeLbl = new Label(container, SWT.NONE);
		nomeLbl.setLayoutData(gridDataLbl);
		nomeLbl.setText(Messages.nome);
		
		nomeTxt = new Text(container, SWT.BORDER);
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
		telefoneLbl.setText(Messages.telefone);
		
		telefoneTxt = new FormattedText(container, SWT.BORDER | SWT.LEFT);
		telefoneTxt.setFormatter(new MaskFormatter("(##)####-####"));
		telefoneTxt.getControl().setLayoutData(gridDataTxt);

		telefoneTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		telefoneTxt.getControl().setLayoutData(gridDataTxt);
		
		// Campo celular
		final Label celularLbl = new Label(container, SWT.NONE);
		celularLbl.setLayoutData(gridDataLbl);
		celularLbl.setText(Messages.celular);
		
		celularTxt = new FormattedText(container, SWT.BORDER | SWT.SINGLE);
		celularTxt.setFormatter(new MaskFormatter("(##)####-####"));
		celularTxt.getControl().setLayoutData(gridDataTxt);
		
		celularTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		celularTxt.getControl().setLayoutData(gridDataTxt);
		
		// Campo email
		final Label emailLbl = new Label(container, SWT.NONE);
		emailLbl.setLayoutData(gridDataLbl);
		emailLbl.setText(Messages.email);
		
		emailTxt = new Text(container, SWT.BORDER);
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
		setPageComplete(false);
		
		if("".equals(nomeTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaNomeContato);
			return;
		}
		else if("".equals(telefoneTxt.getValue().toString()) || "          ".equals(telefoneTxt.getValue().toString()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTelefoneContato);
			return;
		}
		else if("".equals(celularTxt.getValue().toString()) || "          ".equals(celularTxt.getValue().toString()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaCelularContato);
			return;
		}
		else if("".equals(emailTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaEmailContato);
			return;
		}

		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
		contatoOrganizacao.setNome(nomeTxt.getText());
		contatoOrganizacao.setTelefone(Long.parseLong(telefoneTxt.getValue().toString().trim()));
		contatoOrganizacao.setCelular(Long.parseLong(celularTxt.getValue().toString().trim()));
		contatoOrganizacao.setEmail(emailTxt.getText());
	}
}
