package org.jpericia.objeto.wizards.pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.components.ListExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.objeto.messages.Messages;

public class ObjetoAnaliseWizardPage extends WizardPage
{
	private Objeto objeto;
	private ListExtended analiseLst;
	
	public ObjetoAnaliseWizardPage()
	{
		super("objeto");
		setTitle(Messages.tituloObjetoWizard);
		setDescription(Messages.subtituloObjetoWizard);
		objeto = new Objeto();
	}
	
	public ObjetoAnaliseWizardPage(Objeto objeto)
	{
		super("objeto");
		this.objeto = objeto;
		setTitle(Messages.tituloObjetoWizard);
		setDescription(Messages.subtituloObjetoWizard);
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
		
		// Campo analise
		final Label peritoLbl = new Label(container, SWT.NONE);
		peritoLbl.setLayoutData(gridDataLbl);
		peritoLbl.setText(Messages.analise);
		
		analiseLst = new ListExtended(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
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
		
		if(0 == analiseLst.getSelectionCount())
		{
			setMessage(null);
			setErrorMessage(Messages.informaAnalise);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		Set<Analise> analises = new HashSet<Analise>();
		String[] codigos = this.analiseLst.getKeys(); 
		for(int i=0; i<codigos.length; i++)
		{
			Analise analise = new Analise();
			analise.setCodigo(Long.parseLong(codigos[i]));
			analises.add(analise);
		}
		
		objeto.setAnalises(analises);
	}
}
