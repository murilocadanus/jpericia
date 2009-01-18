package org.jpericia.perito.wizards.pages;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.perito.messages.Messages;

public class PeritoUsuarioWizardPage extends WizardPage
{
	private Text loginTxt;
	
	private Text senhaTxt;
	
	private Text confirmarSenhaTxt;
	
	private Perito perito;
	
	public PeritoUsuarioWizardPage()
	{
		super("dadosUsuario");
		setTitle(Messages.tituloPeritoWizard);
		setDescription(Messages.subtituloPeritoWizard);
	}

	public PeritoUsuarioWizardPage(Perito perito)
	{
		super("dadosUsuario");
		setTitle(Messages.tituloPeritoWizard);
		setDescription(Messages.subtituloPeritoWizard);
		this.perito = perito;
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

		// Campo login
		final Label loginLbl = new Label(container, SWT.NONE);
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setText(Messages.login);
		
		loginTxt = new Text(container, SWT.BORDER);
		loginTxt.setTextLimit(40);
		loginTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		loginTxt.setLayoutData(gridDataTxt);
		
		// Campo senha
		final Label senhaLbl = new Label(container, SWT.NONE);
		senhaLbl.setLayoutData(gridDataLbl);
		senhaLbl.setText(Messages.senha);
		
		senhaTxt = new Text(container, SWT.BORDER | SWT.PASSWORD);
		senhaTxt.setTextLimit(20);
		senhaTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		senhaTxt.setLayoutData(gridDataTxt);
		
		// Campo confirmar senha
		final Label confirmarSenhaLbl = new Label(container, SWT.NONE);
		confirmarSenhaLbl.setLayoutData(gridDataLbl);
		confirmarSenhaLbl.setText(Messages.confirmarSenha);
		
		confirmarSenhaTxt = new Text(container, SWT.BORDER | SWT.PASSWORD);
		confirmarSenhaTxt.setTextLimit(20);
		confirmarSenhaTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		confirmarSenhaTxt.setLayoutData(gridDataTxt);
				
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
		
		if("".equals(loginTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaLoginPerito);
			return;
		}
		else if("".equals(senhaTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaSenhaPerito);
			return;
		}
		else if("".equals(confirmarSenhaTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaConfirmarSenhaPerito);
			return;
		}
		if(!senhaTxt.getText().equals(confirmarSenhaTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaFalhaConfirmarSenhaPerito);
			confirmarSenhaTxt.setFocus();
			return;
		}
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		perito.setUsuario(loginTxt.getText());
		perito.setSenha(senhaTxt.getText());		
	}	
}