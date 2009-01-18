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
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.perito.messages.Messages;

public class FuncaoPeritoWizardPage extends WizardPage
{
	private Text funcaoTxt;
	private FuncaoPerito funcaoPerito;
	
	public FuncaoPeritoWizardPage()
	{
		super("funcaoPerito");
		setTitle(Messages.funcaoPeritoTitulo);
		setDescription(Messages.informeDados);
		funcaoPerito = new FuncaoPerito();
	}
	
	public FuncaoPeritoWizardPage(FuncaoPerito funcaoPerito)
	{
		super("funcaoPerito");
		this.funcaoPerito = funcaoPerito;
		setTitle(Messages.funcaoPeritoTitulo);
		setDescription(Messages.informeDados);
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
			
		final Label funcaoLbl = new Label(container, SWT.NONE);
		final GridData gridDataTitulo = new GridData(GridData.HORIZONTAL_ALIGN_END);
		funcaoLbl.setLayoutData(gridDataTitulo);
		funcaoLbl.setText(Messages.funcao);
		
		funcaoTxt = new Text(container, SWT.BORDER);
		funcaoTxt.setTextLimit(40);
		funcaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		funcaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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
		String funcao = funcaoTxt.getText();
		
		if("".equals(funcao))
		{
			setMessage(null);
			setErrorMessage(Messages.informaFuncaoPerito);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		funcaoPerito.setFuncao(funcaoTxt.getText());
	}
}
