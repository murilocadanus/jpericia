package org.jpericia.perito.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.perito.messages.Messages;

public class TituloPeritoWizardPage extends WizardPage
{
	private Text tituloTxt;
	private TituloPerito tituloPerito;
	
	public TituloPeritoWizardPage()
	{
		super("tituloPerito");
		setTitle(Messages.tituloTituloPeritoWizard);
		setDescription(Messages.subtituloTituloTituloPeritoWizard);
		tituloPerito = new TituloPerito();
	}

	public TituloPeritoWizardPage(TituloPerito tituloPerito)
	{
		super("tituloPerito");
		this.tituloPerito = tituloPerito;
		setTitle(Messages.tituloTituloPeritoWizard);
		setDescription(Messages.subtituloTituloTituloPeritoWizard);
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
			
		final Label tituloLbl = new Label(container, SWT.NONE);
		final GridData gridDataTitulo = new GridData(GridData.HORIZONTAL_ALIGN_END);
		tituloLbl.setLayoutData(gridDataTitulo);
		tituloLbl.setText(Messages.titulo);
		
		tituloTxt = new Text(container, SWT.BORDER);
		tituloTxt.setTextLimit(40);
		tituloTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tituloTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
		String funcao = tituloTxt.getText();
		
		if("".equals(funcao))
		{
			setMessage(null);
			setErrorMessage("É necesário informar o título para incluir o título do perito.");
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		tituloPerito.setTitulo(tituloTxt.getText());
	}
}
