package org.jpericia.objeto.views.preference.pages;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

public class EvidenciaPreferencePage extends PreferencePage
{
	private Text tituloTxt;
	private Evidencia evidencia;
	private ComboExtended objetoCmo;
	private Text descricaoTxt;
	private Button imagemBtn;
	
    public EvidenciaPreferencePage()
    {
        setTitle("Evidencia");
        setDescription("Dados da Evidencia");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public EvidenciaPreferencePage(Evidencia evidencia)
    {
        this.evidencia = evidencia;
        setTitle("Evidencia");
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
		final Label loginLbl = new Label(content, SWT.NONE);
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setText(Messages.tipo);
		
		tituloTxt = new Text(content, SWT.BORDER);
		tituloTxt.setText(evidencia.getTitulo());
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
		final Label tipoObjeto = new Label(content, SWT.NONE);
		tipoObjeto.setLayoutData(gridDataLbl);
		tipoObjeto.setText(Messages.objeto);
		
		objetoCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		// Campo Imagem
		final Label imagemLbl = new Label(content, SWT.NONE);
		imagemLbl.setLayoutData(gridDataLbl);
		imagemLbl.setText("Imagem:");
		imagemLbl.setLayoutData(gridDataLbl);
		
		InputStream in = new ByteArrayInputStream(evidencia.getImagem());
		ImageDescriptor dadosImagem = ImageDescriptor.createFromImageData(new ImageData(in));
		final Label imagem = new Label(content, SWT.BORDER);
		imagem.setImage(dadosImagem.createImage());
		
		// campo nova imagem
		final Label novaImagemLbl = new Label(content, SWT.NONE);
		novaImagemLbl.setLayoutData(gridDataLbl);
		novaImagemLbl.setText("");
		
		final Label imagemPathLbl = new Label(content, SWT.BORDER);
		imagemPathLbl.setLayoutData(gridDataTxt);
		
		final Label imagemBtnLbl = new Label(content, SWT.NONE);
		imagemBtnLbl.setLayoutData(gridDataLbl);
		imagemBtnLbl.setText("");
		
		imagemBtn = new Button(content, SWT.NONE);
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
		final Label descricaoLbl = new Label(content, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.descricao);
		
		descricaoTxt = new Text(content, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		descricaoTxt.setText(evidencia.getDescricao());
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
			AbstractResultList abstractObjetoList;
	
			abstractObjetoList = ObjetoDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> objetoList = abstractObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = objetoList.iterator(); iter.hasNext();)
			{
				Objeto objeto = (Objeto) iter.next();
				mapObjeto.put(objeto.getCodigo(), objeto.getTitulo());
			}
			
			this.objetoCmo.setMap(mapObjeto);
			this.objetoCmo.select(this.objetoCmo.indexOf(evidencia.getObjeto().getTitulo()));
			
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
			setErrorMessage(Messages.informaEvidencia);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		Objeto objeto = new Objeto();
		objeto.setCodigo(Long.parseLong(objetoCmo.getValue()));
		objeto.setTitulo(this.objetoCmo.getText());
		
		evidencia.setTitulo(tituloTxt.getText());
		evidencia.setObjeto(objeto);
		evidencia.setDescricao(descricaoTxt.getText());
	}
	
}
