package org.jpericia.analise.wizards.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;

public class AnalisePericiaWizardPage extends WizardPage
{
	private List<AbstractEntity> periciaList;
	private ComboExtended periciaCmo;
	private Label nomeResponsavelValueLbl;
	private Label nomeOrganizacaoValueLbl;
	
	private Analise analise;
	
	public AnalisePericiaWizardPage()
	{
		super("analise");
		setTitle(Messages.tituloTipoAnaliseWizard);
		setDescription(Messages.subtituloTipoAnaliseWizard);
		analise = new Analise();
	}

	public AnalisePericiaWizardPage(Analise analise)
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
			
		// Campo pericia
		final Label periciaLbl = new Label(container, SWT.NONE);
		periciaLbl.setLayoutData(gridDataLbl);
		periciaLbl.setText(Messages.pericia);
		
		periciaCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		periciaCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});

		// Campo nome organizacao
		final Label nomeOrganizacaoLbl = new Label(container, SWT.NONE);
		nomeOrganizacaoLbl.setLayoutData(gridDataLbl);
		nomeOrganizacaoLbl.setText(Messages.nomeOrganizacao);
		
		nomeOrganizacaoValueLbl = new Label(container, SWT.BORDER);
		nomeOrganizacaoValueLbl.setLayoutData(gridDataTxt);
		
		// Campo nome responsavel
		final Label nomeResposavelLbl = new Label(container, SWT.NONE);
		nomeResposavelLbl.setLayoutData(gridDataLbl);
		nomeResposavelLbl.setText(Messages.nomeResponsavel);
		
		nomeResponsavelValueLbl = new Label(container, SWT.BORDER);
		nomeResponsavelValueLbl.setLayoutData(gridDataTxt);
		
		
		initContents();
	}
	
	public void init(ISelection selection)
	{
		if(!(selection instanceof IStructuredSelection))
		{
			return;
		}
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
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		Pericia pericia = new Pericia();
		pericia.setCodigo(Long.parseLong(this.periciaCmo.getValue()));
		
		analise.setPericia(pericia);
	}
}
