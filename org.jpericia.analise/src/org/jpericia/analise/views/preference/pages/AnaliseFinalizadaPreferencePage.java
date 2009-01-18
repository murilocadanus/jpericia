package org.jpericia.analise.views.preference.pages;

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
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.analise.Analise;

public class AnaliseFinalizadaPreferencePage extends PreferencePage
{
	private Analise analise;
	private Text resultadoTxt;
	
    public AnaliseFinalizadaPreferencePage()
    {
        setTitle("Análise - Finalizar Análise");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public AnaliseFinalizadaPreferencePage(Analise analise)
    {
        this.analise = analise;
        setTitle("Análise - Finalizar Análise");
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
		
		// Campo descricao
		final Label descricaoLbl = new Label(content, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.resultado);
		
		resultadoTxt = new Text(content, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		resultadoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		resultadoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		initContents();
		
		return content;
	}
	
	private void initContents()
	{
		updatePageComplete();
		setMessage(null);
		setErrorMessage(null);
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(resultadoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloAnalise);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{		
		analise.setResultado(resultadoTxt.getText());
		analise.setFinalizada(true);
	}
}
