package org.jpericia.analise.views.preference.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

public class AnaliseDadosPreferencePage extends PreferencePage
{
	private Text tituloTxt;
	private Text descricaoTxt;
	private Analise analise;
	private CDateTime dataInicioCDT;
	private CDateTime dataFimCDT;
	private ComboExtended tipoAnaliseCmo;
	
    public AnaliseDadosPreferencePage()
    {
        setTitle("Análise - Dados Análise");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public AnaliseDadosPreferencePage(Analise analise)
    {
        this.analise = analise;
        setTitle("Análise - Dados Análise");
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
		final Label tituloLbl = new Label(content, SWT.NONE);
		tituloLbl.setLayoutData(gridDataLbl);
		tituloLbl.setText(Messages.titulo);
		
		tituloTxt = new Text(content, SWT.BORDER);
		
		tituloTxt.setTextLimit(40);
		tituloTxt.setText(analise.getTitulo());
		tituloTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tituloTxt.setLayoutData(gridDataTxt);
		
		// Campo tipo analise
		final Label tipoAnalise = new Label(content, SWT.NONE);
		tipoAnalise.setLayoutData(gridDataLbl);
		tipoAnalise.setText(Messages.tipoAnalise);
		
		tipoAnaliseCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		// Campo data inicio
		final Label dataInicioLbl = new Label(content, SWT.NONE);
		dataInicioLbl.setLayoutData(gridDataLbl);
		dataInicioLbl.setText(Messages.dataInicio);

		dataInicioCDT = new CDateTime(content, CDT.BORDER | CDT.DROP_DOWN);
		dataInicioCDT.setLocale(new Locale("pt", "BR"));
		dataInicioCDT.setSelection(analise.getDataInicio());
		dataInicioCDT.setLayoutData(gridDataTxt);
		
		// Campo data fim
		final Label dataFimLbl = new Label(content, SWT.NONE);
		dataFimLbl.setLayoutData(gridDataLbl);
		dataFimLbl.setText(Messages.dataFim);
		
		dataFimCDT = new CDateTime(content, CDT.BORDER | CDT.DROP_DOWN);
		dataFimCDT.setLocale(new Locale("pt", "BR"));
		dataFimCDT.setSelection(analise.getDataFim());
		dataFimCDT.setLayoutData(gridDataTxt);
		
		// Campo descricao
		final Label descricaoLbl = new Label(content, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.descricao);
		
		descricaoTxt = new Text(content, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		descricaoTxt.setText(analise.getDescricao());
		descricaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		descricaoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		initContents();
		
		return content;
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
			this.tipoAnaliseCmo.select(this.tipoAnaliseCmo.indexOf(analise.getTipoAnalise().getNome()));
			
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
		setValid(false);
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

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		if(null != this.tipoAnaliseCmo)
		{
			TipoAnalise tipoAnalise = new TipoAnalise();
			tipoAnalise.setCodigo(Long.parseLong(this.tipoAnaliseCmo.getValue()));
			tipoAnalise.setNome(this.tipoAnaliseCmo.getText());
			
			analise.setTipoAnalise(tipoAnalise);
			analise.setTitulo(tituloTxt.getText());
			analise.setDescricao(descricaoTxt.getText());
			analise.setDataInicio(this.dataInicioCDT.getSelection());
			analise.setDataFim(this.dataFimCDT.getSelection());
		}
	}
}
