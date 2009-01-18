package org.jpericia.pericia.wizards.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.messages.Messages;

public class LaudoWizardPage extends WizardPage
{
	
	private Pericia pericia;
	
	private ComboExtended finalizadoCmo; 
	
	public LaudoWizardPage()
	{
		super("laudo");
		setTitle("Laudo");
		setDescription("Informe os dados.");
	}
	
	public LaudoWizardPage(Pericia pericia)
	{
		super("laudo");
		setTitle("Laudo");
		setDescription("Informe os dados.");
		//this.laudo = laudo;
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
		
		// Campo finalizado
		final Label introducaoLbl = new Label(container, SWT.NONE);
		introducaoLbl.setLayoutData(gridDataLbl);
		introducaoLbl.setText(Messages.periciaFinalizada);
		
		finalizadoCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		finalizadoCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
	
		initContents();
	}
	
	private void initContents()
	{
		//Preenche a combo do analise
		HashMap<Long, String> mapFinalizado = new HashMap<Long, String>();
		mapFinalizado.put(Long.valueOf(0), Messages.nao);
		mapFinalizado.put(Long.valueOf(1), Messages.sim);

		this.finalizadoCmo.setMap(mapFinalizado);

		updatePageComplete();
		setMessage(null);
		setErrorMessage(null);
	}	
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
		if("".equals(finalizadoCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaIntroducaoLaudo);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
/*		laudo.setPericia(pericia);
		laudo.setIntroducao(introducaoTxt.getText());
		laudo.setConslusao(conclusaoTxt.getText());*/
		
		if (this.finalizadoCmo.getValue().equals("1"))
		{
			pericia.setFinalizada(Boolean.TRUE);
		}
		else
		{
			pericia.setFinalizada(Boolean.FALSE);
		}
	}
}
