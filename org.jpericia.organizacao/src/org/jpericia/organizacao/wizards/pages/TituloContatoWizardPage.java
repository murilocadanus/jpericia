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
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.organizacao.messages.Messages;

public class TituloContatoWizardPage extends WizardPage
{
	private TituloContato tituloContato;
	
	private Text tituloTxt;
	
	public TituloContatoWizardPage()
	{
		super("tituloContato");
		setTitle(Messages.tituloTituloContatoWizard);
		setDescription(Messages.subtituloTituloContatoWizard);
	}
	
	public TituloContatoWizardPage(TituloContato tituloContato)
	{
		super("tituloContato");
		this.tituloContato = tituloContato;
		setTitle(Messages.tituloTituloContatoWizard);
		setDescription(Messages.subtituloTituloContatoWizard);
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
			setErrorMessage(Messages.informaTituloContato);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		tituloContato.setTitulo(tituloTxt.getText());
	}
}
