package org.jpericia.perito.views.preference.pages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.perito.messages.Messages;

public class PeritoUsuarioPreferencePage extends PreferencePage
{
	private Perito perito;

	private Text loginTxt;
	
	private Text senhaTxt;
	
	private Text confirmarSenhaTxt;
	
    public PeritoUsuarioPreferencePage()
    {
        setTitle("Perito - Usuário");
        setDescription("Dados do perito");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public PeritoUsuarioPreferencePage(Perito perito)
    {
        this.perito = perito;
        setTitle("Perito - Usuário");
        noDefaultAndApplyButton();
        setValid(true);
    }
	
	@Override
	protected Control createContents(Composite parent)
	{
		Composite content = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		content.setLayout(gridLayout);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo login
		final Label loginLbl = new Label(content, SWT.NONE);
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setText(Messages.login);
		
		loginTxt = new Text(content, SWT.BORDER);
		loginTxt.setText(perito.getUsuario());
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
		final Label senhaLbl = new Label(content, SWT.NONE);
		senhaLbl.setLayoutData(gridDataLbl);
		senhaLbl.setText(Messages.senha);
		
		senhaTxt = new Text(content, SWT.BORDER | SWT.PASSWORD);
		senhaTxt.setText(perito.getSenha());
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
		final Label confirmarSenhaLbl = new Label(content, SWT.NONE);
		confirmarSenhaLbl.setLayoutData(gridDataLbl);
		confirmarSenhaLbl.setText(Messages.confirmarSenha);
		
		confirmarSenhaTxt = new Text(content, SWT.BORDER | SWT.PASSWORD);
		confirmarSenhaTxt.setText(perito.getSenha());
		confirmarSenhaTxt.setTextLimit(20);
		confirmarSenhaTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		confirmarSenhaTxt.setLayoutData(gridDataTxt);
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
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
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		perito.setUsuario(loginTxt.getText());
		perito.setSenha(senhaTxt.getText());		
	}
	
}
