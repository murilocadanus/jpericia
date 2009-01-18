package org.jpericia.objeto.views.preference.pages;

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
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.components.ListExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.objeto.messages.Messages;

public class ObjetoAnalisePreferencePage extends PreferencePage
{
	private Objeto objeto;
	private ListExtended analiseLst;
	
    public ObjetoAnalisePreferencePage()
    {
        setTitle("Objeto - Dados Análise");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public ObjetoAnalisePreferencePage(Objeto objeto)
    {
        this.objeto = objeto;
        setTitle("Objeto - Dados Análise");
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

		// Campo analise
		final Label peritoLbl = new Label(content, SWT.NONE);
		peritoLbl.setLayoutData(gridDataLbl);
		peritoLbl.setText(Messages.analise);
		
		analiseLst = new ListExtended(content, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		analiseLst.setLayoutData(gridDataTxt);
		analiseLst.addSelectionListener(new SelectionListener()
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
	
			abstractObjetoList = AnaliseDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> analiseList = abstractObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = analiseList.iterator(); iter.hasNext();)
			{
				Analise analise = (Analise) iter.next();
				mapObjeto.put(analise.getCodigo(), analise.getTitulo());
			}
			
			this.analiseLst.setMap(mapObjeto);
			
			Set<Analise> analiseSet = objeto.getAnalises();
			for (Iterator<Analise> iterator = analiseSet.iterator(); iterator.hasNext();)
			{
				Analise analise = (Analise)iterator.next();
				this.analiseLst.select(this.analiseLst.indexOf(analise.getTitulo()));
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
		
		if(0 == analiseLst.getSelectionCount())
		{
			setMessage(null);
			setErrorMessage(Messages.informaAnalise);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		if(null != this.analiseLst)
		{
			Set<Analise> analises = new HashSet<Analise>();
			String[] codigos = this.analiseLst.getKeys();
			String[] titulos = this.analiseLst.getValues();
			
			for(int i=0; i<codigos.length; i++)
			{
				Analise perito = new Analise();
				perito.setCodigo(Long.parseLong(codigos[i]));
				perito.setTitulo(titulos[i]);
				analises.add(perito);
			}
			
			objeto.setAnalises(analises);
		}
	}
	
}
