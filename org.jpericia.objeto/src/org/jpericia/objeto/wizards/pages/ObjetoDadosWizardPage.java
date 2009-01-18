package org.jpericia.objeto.wizards.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.objeto.businessdelegate.TipoObjetoDelegate;
import org.jpericia.objeto.messages.Messages;

public class ObjetoDadosWizardPage extends WizardPage
{
	private Text tituloTxt;
	private Objeto objeto;
	private ComboExtended tipoObjetoCmo;
	private Text descricaoTxt;
	private Button imagemBtn;
	
	public ObjetoDadosWizardPage()
	{
		super("objeto");
		setTitle(Messages.tituloObjetoWizard);
		setDescription(Messages.subtituloObjetoWizard);
		objeto = new Objeto();
	}
	
	public ObjetoDadosWizardPage(Objeto objeto)
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
		
		// Campo tipoObjeto
		final Label tipoObjeto = new Label(container, SWT.NONE);
		tipoObjeto.setLayoutData(gridDataLbl);
		tipoObjeto.setText(Messages.tipoObjeto);
		
		tipoObjetoCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		
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
				        objeto.setImagem(data);
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
			//Preenche a combo do tipo objeto
			HashMap<Long, String> mapTipoObjeto = new HashMap<Long, String>();
			AbstractResultList abstractTipoObjetoList;
	
			abstractTipoObjetoList = TipoObjetoDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> tipoObjetoList = abstractTipoObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = tipoObjetoList.iterator(); iter.hasNext();)
			{
				TipoObjeto tipoObjeto = (TipoObjeto) iter.next();
				mapTipoObjeto.put(tipoObjeto.getCodigo(), tipoObjeto.getTitulo());
			}
			
			this.tipoObjetoCmo.setMap(mapTipoObjeto);
			
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
			setErrorMessage(Messages.informaObjeto);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		TipoObjeto tipoObjeto = new TipoObjeto();
		tipoObjeto.setCodigo(Long.parseLong(tipoObjetoCmo.getValue()));
		
		objeto.setTitulo(tituloTxt.getText());
		objeto.setTipoObjeto(tipoObjeto);
		objeto.setDescricao(descricaoTxt.getText());
	}
}
