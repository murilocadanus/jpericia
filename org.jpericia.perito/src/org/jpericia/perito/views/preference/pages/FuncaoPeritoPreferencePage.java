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
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.perito.messages.Messages;

public class FuncaoPeritoPreferencePage extends PreferencePage
{
	private FuncaoPerito funcaoPerito;

	private Text funcaoTxt;
	
    public FuncaoPeritoPreferencePage()
    {
        setTitle("Função Perito");
        setDescription("Dados do perito");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public FuncaoPeritoPreferencePage(FuncaoPerito funcaoPerito)
    {
        this.funcaoPerito = funcaoPerito;
        setTitle("Função Perito");
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
		loginLbl.setText(Messages.funcao);
		
		funcaoTxt = new Text(content, SWT.BORDER);
		funcaoTxt.setText(funcaoPerito.getFuncao());
		funcaoTxt.setTextLimit(40);
		funcaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		funcaoTxt.setLayoutData(gridDataTxt);
		
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(funcaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaLoginPerito);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		funcaoPerito.setFuncao(funcaoTxt.getText());
	}
	
}
