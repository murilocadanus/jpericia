package org.jpericia.organizacao.views.preference.pages;

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
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.organizacao.messages.Messages;

public class TipoOrganizacaoPreferencePage extends PreferencePage
{
	private TipoOrganizacao tipoOrganizacao;

	private Text nomeTxt;
	
    public TipoOrganizacaoPreferencePage()
    {
        setTitle("Tipo Organização");
        setDescription("Dados do titulo contato");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public TipoOrganizacaoPreferencePage(TipoOrganizacao tipoOrganizacao)
    {
        this.tipoOrganizacao = tipoOrganizacao;
        setTitle("Tipo Organização");
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

		// Campo nome
		final Label nomeLbl = new Label(content, SWT.NONE);
		nomeLbl.setLayoutData(gridDataLbl);
		nomeLbl.setText(Messages.titulo);
		
		nomeTxt = new Text(content, SWT.BORDER);
		nomeTxt.setText(tipoOrganizacao.getNome());
		nomeTxt.setTextLimit(40);
		nomeTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		nomeTxt.setLayoutData(gridDataTxt);
		
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(nomeTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoOrganizacao);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		tipoOrganizacao.setNome(nomeTxt.getText());
	}
}
