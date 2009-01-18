package org.jpericia.pericia.views.preference.pages;

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
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.pericia.messages.Messages;

public class LaudoPreferencePage extends PreferencePage
{
	//private Laudo laudo;
	
	private Pericia pericia;
	
	private Text introducaoTxt;
	
	private Text conclusaoTxt;
	
	public LaudoPreferencePage()
	{
        setTitle("Laudo");
        setDescription("Dados do laudo");
        noDefaultAndApplyButton();
        setValid(false);
	}
	
	public LaudoPreferencePage(Pericia pericia)
	{	
        this.pericia = pericia;
        setTitle("Laudo");
        noDefaultAndApplyButton();
        setValid(true);
	}
	
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
			
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);
		
		// Campo introducao
		final Label introducaoLbl = new Label(container, SWT.NONE);
		introducaoLbl.setLayoutData(gridDataLbl);
		introducaoLbl.setText(Messages.introducao);
		
		introducaoTxt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		
		if (pericia.getIntroducao() != null)
		{
			introducaoTxt.setText(pericia.getIntroducao());
		}
		introducaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		introducaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		// Campo conclusao
		final Label descricaoLbl = new Label(container, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.conclusao);
		
		conclusaoTxt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		if (pericia.getConclusao() != null)
		{
			conclusaoTxt.setText(pericia.getConclusao());
		}
		conclusaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		conclusaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));	
		
		return container;
	}
	
	private void updatePageComplete()
	{
		setValid(false);
		
		if("".equals(introducaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaIntroducaoLaudo);
			return;
		}
		else if("".equals(conclusaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaConclusaoLaudo);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{	
		pericia.setFinalizada(Boolean.FALSE);
		pericia.setIntroducao(introducaoTxt.getText());
		pericia.setConclusao(conclusaoTxt.getText());
	}
}
