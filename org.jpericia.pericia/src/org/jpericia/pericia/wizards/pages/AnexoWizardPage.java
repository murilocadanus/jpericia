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
import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.messages.Messages;

public class AnexoWizardPage extends WizardPage
{
	private Anexo anexo;
	
	private ComboExtended periciaCmo;
	
	private Text tituloTxt;
	
	private Text textoTxt;
	
	public AnexoWizardPage()
	{
		super("anexo");
		setTitle("Anexo");
		setDescription("Informe os dados.");
	}
	
	public AnexoWizardPage(Anexo anexo)
	{
		super("anexo");
		setTitle("Anexo");
		setDescription("Informe os dados.");
		this.anexo = anexo;
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
		
		// Campo texto
		final Label descricaoLbl = new Label(container, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.texto);
		
		textoTxt = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		textoTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		
		initContents();
	}
	
	private void initContents()
	{
		try
		{		
			//Preenche a combo do analise
			HashMap<Long, String> mapObjeto = new HashMap<Long, String>();
			AbstractResultList abstractObjetoList;
			abstractObjetoList = PericiaDelegate.getInstance().pesquisar();
			List<AbstractEntity> periciaList = abstractObjetoList.getResultList();
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
		
		if("".equals(tituloTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloAnexo);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject()
	{
		Pericia pericia = new Pericia();
		pericia.setCodigo(Long.parseLong(this.periciaCmo.getValue()));
		
		anexo.setPericia(pericia);
		anexo.setTitulo(this.tituloTxt.getText());
		anexo.setTexto(this.textoTxt.getText());
	}
}