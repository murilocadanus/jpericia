package org.jpericia.objeto.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.objeto.messages.Messages;

public class CategoriaTipoObjetoWizardPage extends WizardPage
{
	private Text tipoTxt;
	private CategoriaTipoObjeto categoriaTipoObjeto;
	
	public CategoriaTipoObjetoWizardPage()
	{
		super("categoriaTipoObjeto");
		setTitle(Messages.tituloCategoriaTipoObjetoWizard);
		setDescription(Messages.subtituloCategoriaTipoObjetoWizard);
		categoriaTipoObjeto = new CategoriaTipoObjeto();
	}
	
	public CategoriaTipoObjetoWizardPage(CategoriaTipoObjeto categoriaTipoObjeto)
	{
		super("categoriaTipoObjeto");
		this.categoriaTipoObjeto = categoriaTipoObjeto;
		setTitle(Messages.tituloCategoriaTipoObjetoWizard);
		setDescription(Messages.subtituloCategoriaTipoObjetoWizard);
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
			
		final Label tipoLbl = new Label(container, SWT.NONE);
		final GridData gridDataTitulo = new GridData(GridData.HORIZONTAL_ALIGN_END);
		tipoLbl.setLayoutData(gridDataTitulo);
		tipoLbl.setText(Messages.tipo);
		
		tipoTxt = new Text(container, SWT.BORDER);
		tipoTxt.setTextLimit(40);
		tipoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tipoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
		String tipo = tipoTxt.getText();
		
		if("".equals(tipo))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoObjeto);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		categoriaTipoObjeto.setNome(tipoTxt.getText());
	}
}
