package org.jpericia.objeto.wizards.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.objeto.businessdelegate.ObjetoDelegate;
import org.jpericia.objeto.messages.Messages;

public class EvidenciaWizardPage extends WizardPage
{
	private Text tituloTxt;
	private Evidencia evidencia;
	private ComboExtended objetoCmo;
	private Text descricaoTxt;
	private Button imagemBtn;
	
	public EvidenciaWizardPage()
	{
		super("evidencia");
		setTitle(Messages.tituloEvidenciaWizard);
		setDescription(Messages.subtituloEvidenciaWizard);
		evidencia = new Evidencia();
	}
	
	public EvidenciaWizardPage(Evidencia evidencia)
	{
		super("evidencia");
		this.evidencia = evidencia;
		setTitle(Messages.tituloEvidenciaWizard);
		setDescription(Messages.subtituloEvidenciaWizard);
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
		final Label tipoLbl = new Label(container, SWT.NONE);
		tipoLbl.setLayoutData(gridDataLbl);
		tipoLbl.setText(Messages.tipo);
		
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
		
		// Campo objeto
		final Label tipoObjeto = new Label(container, SWT.NONE);
		tipoObjeto.setLayoutData(gridDataLbl);
		tipoObjeto.setText(Messages.objeto);
		
		objetoCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		// Campo Imagem
		final Label imagemLbl = new Label(container, SWT.NONE);
		imagemLbl.setLayoutData(gridDataLbl);
		imagemLbl.setText("Imagem:");
		
		final Label imagemPathLbl = new Label(container, SWT.BORDER);
		imagemPathLbl.setLayoutData(gridDataTxt);
		
		final Label imagemBtnLbl = new Label(container, SWT.NONE);
		imagemBtnLbl.setLayoutData(gridDataLbl);
		imagemBtnLbl.setText("");
		
		imagemBtn = new Button(container, SWT.NONE);
		imagemBtn.setText("Procurar");
		imagemBtn.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
		        try
				{
			        FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
			        fd.setText("Abrir");
			        fd.setFilterPath("/");
			        String[] filterExt = { "*.gif", "*.jpg", "*.png" };
			        fd.setFilterExtensions(filterExt);
			        String imagemPath = fd.open();
			        
			        if(null != imagemPath)
			        {
						InputStream in = new FileInputStream(imagemPath);
						byte[] data = new byte[in.available()];
						in.read(data);
						imagemPathLbl.setText(imagemPath);
				        evidencia.setImagem(data);
			        }
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
				}
			}
		});

		
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
			AbstractResultList abstractObjetoList;
	
			abstractObjetoList = ObjetoDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> objetoList = abstractObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = objetoList.iterator(); iter.hasNext();)
			{
				Objeto objeto = (Objeto) iter.next();
				mapObjeto.put(objeto.getCodigo(), objeto.getTitulo());
			}
			
			this.objetoCmo.setMap(mapObjeto);
			
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
		String tipo = tituloTxt.getText();
		
		if("".equals(tipo))
		{
			setMessage(null);
			setErrorMessage(Messages.informaEvidencia);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		Objeto objeto = new Objeto();
		objeto.setCodigo(Long.parseLong(objetoCmo.getValue()));
		
		evidencia.setTitulo(tituloTxt.getText());
		evidencia.setObjeto(objeto);
		evidencia.setDescricao(descricaoTxt.getText());
	}
}
