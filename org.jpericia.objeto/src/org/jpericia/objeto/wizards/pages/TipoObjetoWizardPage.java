package org.jpericia.objeto.wizards.pages;

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
import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.objeto.businessdelegate.CategoriaTipoObjetoDelegate;
import org.jpericia.objeto.messages.Messages;

public class TipoObjetoWizardPage extends WizardPage
{
	private Text tipoTxt;
	private TipoObjeto tipoObjeto;
	private ComboExtended categoriaCmo;
	
	public TipoObjetoWizardPage()
	{
		super("tipoObjeto");
		setTitle(Messages.tituloTipoObjetoWizard);
		setDescription(Messages.subtituloTipoObjetoWizard);
		tipoObjeto = new TipoObjeto();
	}
	
	public TipoObjetoWizardPage(TipoObjeto tipoObjeto)
	{
		super("tipoObjeto");
		this.tipoObjeto = tipoObjeto;
		setTitle(Messages.tituloTipoObjetoWizard);
		setDescription(Messages.subtituloTipoObjetoWizard);
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
		
		tipoTxt = new Text(container, SWT.BORDER);
		tipoTxt.setTextLimit(40);
		tipoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tipoTxt.setLayoutData(gridDataTxt);
		
		// Campo categoria
		final Label categoriaLbl = new Label(container, SWT.NONE);
		categoriaLbl.setLayoutData(gridDataLbl);
		categoriaLbl.setText(Messages.categoria);
		
		categoriaCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		initContents();
	}
	
	private void initContents()
	{
		try
		{		
			//Preenche a combo da categoria tipo objeto
			HashMap<Long, String> mapCategoriaTipoObjeto = new HashMap<Long, String>();
			AbstractResultList abstractCategoriaTipoObjetoList;
	
			abstractCategoriaTipoObjetoList = CategoriaTipoObjetoDelegate.getInstance().pesquisar();
	
			List<AbstractEntity> categoriaTipoObjetoList = abstractCategoriaTipoObjetoList.getResultList();
			for (Iterator<AbstractEntity> iter = categoriaTipoObjetoList.iterator(); iter.hasNext();)
			{
				CategoriaTipoObjeto categoriaTipoObjeto = (CategoriaTipoObjeto) iter.next();
				mapCategoriaTipoObjeto.put(categoriaTipoObjeto.getCodigo(), categoriaTipoObjeto.getNome());
			}
			
			this.categoriaCmo.setMap(mapCategoriaTipoObjeto);
			
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
		
		if("".equals(tipoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoObjeto);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		CategoriaTipoObjeto categoriaTipoObjeto = new CategoriaTipoObjeto();
		categoriaTipoObjeto.setCodigo(Long.parseLong(categoriaCmo.getValue()));
		
		tipoObjeto.setTitulo(tipoTxt.getText());
		tipoObjeto.setCategoriaTipoObjeto(categoriaTipoObjeto);
	}
}
