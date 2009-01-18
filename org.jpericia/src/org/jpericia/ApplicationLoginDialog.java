package org.jpericia;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.perito.businessdelegate.PeritoDelegate;

public class ApplicationLoginDialog extends Dialog
{
	private Perito perito = new Perito();
	
	private Label falhaInfoLbl;

	private Text loginTxt;

	private Text senhaTxt;

	public ApplicationLoginDialog(Shell parentShell)
	{
		super(parentShell);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("JPericia");
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		container.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		GridData gridDataImg = new GridData(GridData.BEGINNING,
				GridData.CENTER, false, false, 0, 7);
		GridData gridDataHead = new GridData(GridData.BEGINNING,
				GridData.CENTER, false, false, 2, 0);
		GridData gridDataLbl = new GridData(GridData.END, GridData.CENTER,
				false, false);
		GridData gridDataTxt = new GridData(GridData.FILL, GridData.CENTER,
				false, false);
		
		final Label imgLbl = new Label(container, SWT.NONE);
		imgLbl.setImage(ImageDescriptor.createFromURL(
                FileLocator.find(JPericiaPlugin.getDefault().getBundle(), 
                        new Path("img_login.bmp"),null)).createImage());
		imgLbl.setLayoutData(gridDataImg);

		Label espac = new Label(container, SWT.NONE);
		espac = new Label(container, SWT.NONE);
		
		falhaInfoLbl = new Label(container, SWT.NONE);
		falhaInfoLbl.setText("");
		falhaInfoLbl.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		falhaInfoLbl.setLayoutData(gridDataHead);
		falhaInfoLbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		espac = new Label(container, SWT.NONE);
		
		final Label loginInfoLbl = new Label(container, SWT.NONE);
		loginInfoLbl.setText("Digite o usuário e senha para acessar:");
		loginInfoLbl.setLayoutData(gridDataHead);
		loginInfoLbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		final Label loginLbl = new Label(container, SWT.NONE);
		loginLbl.setText("Usuário:");
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		loginTxt = new Text(container, SWT.BORDER);
		loginTxt.setLayoutData(gridDataTxt);

		final Label senhaLbl = new Label(container, SWT.NONE);
		senhaLbl.setText("Senha:   ");
		senhaLbl.setLayoutData(gridDataLbl);
		senhaLbl.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		senhaTxt = new Text(container, SWT.BORDER | SWT.PASSWORD);
		senhaTxt.setLayoutData(gridDataTxt);

		return container;
	}

	@Override
	protected void okPressed()
	{
		perito.setUsuario(loginTxt.getText());
		perito.setSenha(senhaTxt.getText());
        
		try
		{
			falhaInfoLbl.setText("");
			if (null != PeritoDelegate.getInstance().autenticar(perito))
			{
				setReturnCode(OK);
				close();
			}
			else
				falhaInfoLbl.setText("Usuário ou senha inválido");
		}
		catch (BusinessDelegateException e)
		{
			// TODO: lanca excecao
		}		
	}

}
