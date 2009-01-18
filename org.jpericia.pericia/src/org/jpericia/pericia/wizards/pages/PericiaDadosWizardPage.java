package org.jpericia.pericia.wizards.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.organizacao.businessdelegate.ContatoOrganizacaoDelegate;
import org.jpericia.pericia.messages.Messages;
import org.jpericia.perito.businessdelegate.PeritoDelegate;

public class PericiaDadosWizardPage extends WizardPage
{
	private Pericia pericia;
	
	private Text tituloTxt;
	
	private Text descricaoTxt;
	
	private CDateTime dataInicioCDT;
	
	private CDateTime dataFimCDT;
	
	public PericiaDadosWizardPage()
	{
		super("pericia");
		setTitle("Pericia");
		setDescription("Informe os dados.");
	}
	
	public PericiaDadosWizardPage(Pericia pericia)
	{
		super("pericia");
		setTitle("Pericia");
		setDescription("Informe os dados.");
		this.pericia = pericia;
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
			
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);
				
		// Campo titulo
		final Label tituloLbl = new Label(container, SWT.NONE);
		tituloLbl.setLayoutData(gridDataLbl);
		tituloLbl.setText(Messages.titulo);
		
		tituloTxt = new Text(container, SWT.BORDER);
		tituloTxt.setTextLimit(40);
		tituloTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tituloTxt.setLayoutData(gridDataTxt);
		
		// Campo descricao
		final Label descricaoLbl = new Label(container, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.descricao);
		
		descricaoTxt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		descricaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		descricaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		
		// Campo data inicio
		final Label dataInicioLbl = new Label(container, SWT.NONE);
		dataInicioLbl.setLayoutData(gridDataLbl);
		dataInicioLbl.setText(Messages.dataInicio);
		
		dataInicioCDT = new CDateTime(container, CDT.BORDER | CDT.DROP_DOWN);
		dataInicioCDT.setLocale(new Locale("pt", "BR"));
		dataInicioCDT.setLayoutData(gridDataTxt);
		
		// Campo data fim
		final Label dataFimLbl = new Label(container, SWT.NONE);
		dataFimLbl.setLayoutData(gridDataLbl);
		dataFimLbl.setText(Messages.dataFim);
		
		dataFimCDT = new CDateTime(container, CDT.BORDER | CDT.DROP_DOWN);
		dataFimCDT.setLocale(new Locale("pt", "BR"));
		dataFimCDT.setLayoutData(gridDataTxt);
		
	}
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
		if("".equals(tituloTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloPericia);
			return;
		}
		else if("".equals(descricaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaDescricaoPericia);
			return;
		}
		
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
		pericia.setTitulo(tituloTxt.getText());
		pericia.setDescricao(this.descricaoTxt.getText());
		pericia.setDataInicio(this.dataInicioCDT.getSelection());
		pericia.setDataFim(this.dataFimCDT.getSelection());
		//Adiciona a pericia como nao finalizada
		pericia.setFinalizada(Boolean.FALSE);
		
	}
}