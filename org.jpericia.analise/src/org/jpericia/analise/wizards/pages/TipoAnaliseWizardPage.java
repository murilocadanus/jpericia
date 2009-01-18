package org.jpericia.analise.wizards.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.analise.TipoAnalise;

public class TipoAnaliseWizardPage extends WizardPage
{
	private Text tipoTxt;
	private TipoAnalise tipoAnalise;
	
	public TipoAnaliseWizardPage()
	{
		super("tipoAnalise");
		setTitle(Messages.tituloTipoAnaliseWizard);
		setDescription(Messages.subtituloTipoAnaliseWizard);
		tipoAnalise = new TipoAnalise();
	}

	public TipoAnaliseWizardPage(TipoAnalise tipoAnalise)
	{
		super("tipoAnalise");
		this.tipoAnalise = tipoAnalise;
		setTitle(Messages.tituloTipoAnaliseWizard);
		setDescription(Messages.subtituloTipoAnaliseWizard);
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
		String funcao = tipoTxt.getText();
		
		if("".equals(funcao))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoAnalise);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		tipoAnalise.setNome(tipoTxt.getText());
	}
}
