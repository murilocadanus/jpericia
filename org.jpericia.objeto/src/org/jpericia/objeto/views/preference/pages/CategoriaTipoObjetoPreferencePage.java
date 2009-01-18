package org.jpericia.objeto.views.preference.pages;

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
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.objeto.messages.Messages;

public class CategoriaTipoObjetoPreferencePage extends PreferencePage
{
	private CategoriaTipoObjeto categoriaTipoObjeto;

	private Text tipoTxt;
	
    public CategoriaTipoObjetoPreferencePage()
    {
        setTitle("Categoria tipo objeto");
        setDescription("Dados da categoria tipo objeto");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public CategoriaTipoObjetoPreferencePage(CategoriaTipoObjeto categoriaTipoObjeto)
    {
        this.categoriaTipoObjeto = categoriaTipoObjeto;
        setTitle("Categoria tipo objeto");
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
		loginLbl.setText(Messages.tipo);
		
		tipoTxt = new Text(content, SWT.BORDER);
		tipoTxt.setText(categoriaTipoObjeto.getNome());
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
			setErrorMessage(Messages.informaTipoObjeto);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		categoriaTipoObjeto.setNome(tipoTxt.getText());
	}
	
}
