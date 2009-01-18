package org.jpericia.analise.wizards.pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.components.ListExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.perito.businessdelegate.PeritoDelegate;

public class AnalisePeritoWizardPage extends WizardPage
{
	private ListExtended peritoLst;
	
	private Analise analise;
	
	public AnalisePeritoWizardPage()
	{
		super("analise");
		setTitle(Messages.tituloTipoAnaliseWizard);
		setDescription(Messages.subtituloTipoAnaliseWizard);
		analise = new Analise();
	}

	public AnalisePeritoWizardPage(Analise analise)
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
			
		// Campo perito
		final Label peritoLbl = new Label(container, SWT.NONE);
		peritoLbl.setLayoutData(gridDataLbl);
		peritoLbl.setText(Messages.perito);
		
		peritoLst = new ListExtended(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
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
		
		if(0 == peritoLst.getSelectionCount())
		{
			setMessage(null);
			setErrorMessage(Messages.informaPerito);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		Set<Perito> peritos = new HashSet<Perito>();
		String[] codigos = this.peritoLst.getKeys(); 
		for(int i=0; i<codigos.length; i++)
		{
			Perito perito = new Perito();
			perito.setCodigo(Long.parseLong(codigos[i]));
			peritos.add(perito);
		}
		
		analise.setPeritos(peritos);
	}
}
