package org.jpericia.analise.views.preference.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;

public class AnalisePericiaPreferencePage extends PreferencePage
{
	private Analise analise;
	private List<AbstractEntity> periciaList;
	private ComboExtended periciaCmo;
	private Label nomeResponsavelValueLbl;
	private Label nomeOrganizacaoValueLbl;
	
    public AnalisePericiaPreferencePage()
    {
        setTitle("Análise - Dados Perícia");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public AnalisePericiaPreferencePage(Analise analise)
    {
        this.analise = analise;
        setTitle("Análise - Dados Perícia");
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

		// Campo pericia
		final Label periciaLbl = new Label(content, SWT.NONE);
		periciaLbl.setLayoutData(gridDataLbl);
		periciaLbl.setText(Messages.pericia);
		
		periciaCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);
		periciaCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});

		// Campo nome organizacao
		final Label nomeOrganizacaoLbl = new Label(content, SWT.NONE);
		nomeOrganizacaoLbl.setLayoutData(gridDataLbl);
		nomeOrganizacaoLbl.setText(Messages.nomeOrganizacao);
		
		nomeOrganizacaoValueLbl = new Label(content, SWT.BORDER);
		nomeOrganizacaoValueLbl.setLayoutData(gridDataTxt);
		
		// Campo nome responsavel
		final Label nomeResposavelLbl = new Label(content, SWT.NONE);
		nomeResposavelLbl.setLayoutData(gridDataLbl);
		nomeResposavelLbl.setText(Messages.nomeResponsavel);
		
		nomeResponsavelValueLbl = new Label(content, SWT.BORDER);
		nomeResponsavelValueLbl.setLayoutData(gridDataTxt);
	
		initContents();
		
		return content;
	}
	
	private void initContents()
	{
		try
		{		
			//Preenche a combo do analise
			HashMap<Long, String> mapObjeto = new HashMap<Long, String>();
			AbstractResultList abstractObjetoList;
	
			abstractObjetoList = PericiaDelegate.getInstance().pesquisar();
	
			periciaList = abstractObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = periciaList.iterator(); iter.hasNext();)
			{
				Pericia pericia = (Pericia) iter.next();
				mapObjeto.put(pericia.getCodigo(), pericia.getTitulo());
			}
			
			this.periciaCmo.setMap(mapObjeto);
			this.periciaCmo.select(this.periciaCmo.indexOf(analise.getPericia().getTitulo()));
			
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
		
		if("".equals(periciaCmo.getText()))
		{
			return;
		}
		else
		{
			if("".equals(periciaCmo.getValue()))
			{
				setMessage(null);
				setErrorMessage(Messages.informaPericia);
				return;
			}
			else
			{
				for (Iterator<AbstractEntity> iter = periciaList.iterator(); iter.hasNext();)
				{
					Pericia pericia = (Pericia) iter.next();
					if(Long.valueOf(periciaCmo.getValue()).equals(pericia.getCodigo()))
					{
						nomeOrganizacaoValueLbl.setText(pericia.getContatoOrganizacao().getOrganizacao().getNome());
						nomeResponsavelValueLbl.setText(pericia.getContatoOrganizacao().getNome());
						break;
					}
				}
			}
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		Pericia pericia = new Pericia();
		pericia.setCodigo(Long.parseLong(this.periciaCmo.getValue()));
		pericia.setTitulo(this.periciaCmo.getText());
		
		analise.setPericia(pericia);
	}
}
