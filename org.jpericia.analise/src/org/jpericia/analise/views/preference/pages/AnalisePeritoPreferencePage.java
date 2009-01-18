package org.jpericia.analise.views.preference.pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.components.ListExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.perito.businessdelegate.PeritoDelegate;

public class AnalisePeritoPreferencePage extends PreferencePage
{
	private Analise analise;
	private ListExtended peritoLst;
	
    public AnalisePeritoPreferencePage()
    {
        setTitle("Análise - Dados Perícia");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public AnalisePeritoPreferencePage(Analise analise)
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

		// Campo perito
		final Label peritoLbl = new Label(content, SWT.NONE);
		peritoLbl.setLayoutData(gridDataLbl);
		peritoLbl.setText(Messages.perito);
		
		peritoLst = new ListExtended(content, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		peritoLst.setLayoutData(gridDataTxt);
		peritoLst.addSelectionListener(new SelectionListener()
		{
			public void widgetSelected(SelectionEvent e)
			{
				updatePageComplete();
			}
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		});
		

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
	
			abstractObjetoList = PeritoDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> peritoList = abstractObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = peritoList.iterator(); iter.hasNext();)
			{
				Perito perito = (Perito) iter.next();
				mapObjeto.put(perito.getCodigo(), perito.getNome());
			}
			
			this.peritoLst.setMap(mapObjeto);
			
			Set<Perito> peritoSet = analise.getPeritos();
			for (Iterator<Perito> iterator = peritoSet.iterator(); iterator.hasNext();)
			{
				Perito perito = (Perito)iterator.next();
				this.peritoLst.select(this.peritoLst.indexOf(perito.getNome()));
			}
			
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
		
		if(0 == peritoLst.getSelectionCount())
		{
			setMessage(null);
			setErrorMessage(Messages.informaPerito);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		if(null != this.peritoLst)
		{
			Set<Perito> peritos = new HashSet<Perito>();
			String[] codigos = this.peritoLst.getKeys();
			String[] nomes = this.peritoLst.getValues();
			
			for(int i=0; i<codigos.length; i++)
			{
				Perito perito = new Perito();
				perito.setCodigo(Long.parseLong(codigos[i]));
				perito.setNome(nomes[i]);
				peritos.add(perito);
			}
			
			analise.setPeritos(peritos);
		}
	}
}
