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
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.perito.messages.Messages;

public class TituloPeritoPreferencePage extends PreferencePage
{
	private TituloPerito tituloPerito;

	private Text tituloTxt;
	
    public TituloPeritoPreferencePage()
    {
        setTitle("Título Perito");
        setDescription("Dados do titulo perito");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public TituloPeritoPreferencePage(TituloPerito tituloPerito)
    {
        this.tituloPerito = tituloPerito;
        setTitle("Título Perito");
        setDescription("Dados do titulo perito");
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

		// Campo titulo
		final Label loginLbl = new Label(content, SWT.NONE);
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setText(Messages.titulo);
		
		tituloTxt = new Text(content, SWT.BORDER);
		tituloTxt.setText(tituloPerito.getTitulo());
		tituloTxt.setTextLimit(40);
		tituloTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tituloTxt.setLayoutData(gridDataTxt);
		
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(tituloTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloPerito);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		tituloPerito.setTitulo(tituloTxt.getText());
	}
}
