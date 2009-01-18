package org.jpericia.analise.wizards.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.analise.businessdelegate.TipoAnaliseDelegate;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class AnaliseDadosWizardPage extends WizardPage
{
	private Text tituloTxt;
	private Text descricaoTxt;
	private Analise analise;
	private CDateTime dataInicioCDT;
	private CDateTime dataFimCDT;
	private ComboExtended tipoAnaliseCmo;
	
	public AnaliseDadosWizardPage()
	{
		super("analise");
		setTitle(Messages.tituloTipoAnaliseWizard);
		setDescription(Messages.subtituloTipoAnaliseWizard);
		analise = new Analise();
	}

	public AnaliseDadosWizardPage(Analise analise)
	{
		super("analise");
		this.analise = analise;
		setTitle(Messages.tituloAnaliseWizard);
		setDescription(Messages.subtituloAnaliseWizard);
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
		
		// Campo tipo analise
		final Label tipoAnalise = new Label(container, SWT.NONE);
		tipoAnalise.setLayoutData(gridDataLbl);
		tipoAnalise.setText(Messages.tipoAnalise);
		
		tipoAnaliseCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		tipoAnaliseCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
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
		
		initContents();
	}
	
	private void initContents()
	{
		try
		{		
			//Preenche a combo do objeto
			HashMap<Long, String> mapObjeto = new HashMap<Long, String>();
			AbstractResultList abstractTipoAnaliseList;
	
			abstractTipoAnaliseList = TipoAnaliseDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> tipoAnaliseList = abstractTipoAnaliseList.getResultList();
			for (Iterator<AbstractEntity> iter = tipoAnaliseList.iterator(); iter.hasNext();)
			{
				TipoAnalise tipoAnalise = (TipoAnalise) iter.next();
				mapObjeto.put(tipoAnalise.getCodigo(), tipoAnalise.getNome());
			}
			
			this.tipoAnaliseCmo.setMap(mapObjeto);
			
			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
		}
	}
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
		if("".equals(tituloTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloAnalise);
			return;
		}
		
		if("".equals(tipoAnaliseCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoAnaliseAnalise);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		TipoAnalise tipoAnalise = new TipoAnalise();
		tipoAnalise.setCodigo(Long.parseLong(this.tipoAnaliseCmo.getValue()));
		
		analise.setTipoAnalise(tipoAnalise);
		analise.setTitulo(tituloTxt.getText());
		analise.setDescricao(descricaoTxt.getText());
		analise.setDataInicio(this.dataInicioCDT.getSelection());
		analise.setDataFim(this.dataFimCDT.getSelection());
		analise.setFinalizada(false);
	}
}
