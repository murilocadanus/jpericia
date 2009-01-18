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
import org.jpericia.common.entity.analise.TipoAnalise;

public class TipoAnalisePreferencePage extends PreferencePage
{
	private TipoAnalise tipoAnalise;

	private Text tipoTxt;
	
    public TipoAnalisePreferencePage()
    {
        setTitle("Tipo análise");
        setDescription("Dados do tipo análise");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public TipoAnalisePreferencePage(TipoAnalise tipoAnalise)
    {
        this.tipoAnalise = tipoAnalise;
        setTitle("Tipo Análise");
        setDescription("Dados do tipo análise");
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

		// Campo tipo
		final Label tipoLbl = new Label(content, SWT.NONE);
		tipoLbl.setLayoutData(gridDataLbl);
		tipoLbl.setText(Messages.tipo);
		
		tipoTxt = new Text(content, SWT.BORDER);
		tipoTxt.setText(tipoAnalise.getNome());
		tipoTxt.setTextLimit(40);
		tipoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tipoTxt.setLayoutData(gridDataTxt);
		
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(tipoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoAnalise);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		tipoAnalise.setNome(tipoTxt.getText());
	}
}
