package org.jpericia.objeto.views.preference.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

public class TipoObjetoPreferencePage extends PreferencePage
{
	private Text tipoTxt;
	private TipoObjeto tipoObjeto;
	private ComboExtended categoriaCmo;
	
    public TipoObjetoPreferencePage()
    {
        setTitle("Tipo objeto");
        setDescription("Dados do tipo objeto");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public TipoObjetoPreferencePage(TipoObjeto tipoObjeto)
    {
        this.tipoObjeto = tipoObjeto;
        setTitle("Tipo objeto");
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

		// Campo login
		final Label loginLbl = new Label(content, SWT.NONE);
		loginLbl.setLayoutData(gridDataLbl);
		loginLbl.setText(Messages.tipo);
		
		tipoTxt = new Text(content, SWT.BORDER);
		tipoTxt.setText(tipoObjeto.getTitulo());
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
		final Label categoriaLbl = new Label(content, SWT.NONE);
		categoriaLbl.setLayoutData(gridDataLbl);
		categoriaLbl.setText(Messages.categoria);
		
		categoriaCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		initContents();
		
		return content;
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
			this.categoriaCmo.select(this.categoriaCmo.indexOf(tipoObjeto.getCategoriaTipoObjeto().getNome()));
			
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
		if("".equals(tipoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoObjeto);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	public void setEntityObject()
	{
		CategoriaTipoObjeto categoriaTipoObjeto = new CategoriaTipoObjeto();
		categoriaTipoObjeto.setCodigo(Long.parseLong(categoriaCmo.getValue()));
		categoriaTipoObjeto.setNome(this.categoriaCmo.getText());
		
		tipoObjeto.setTitulo(tipoTxt.getText());
		tipoObjeto.setCategoriaTipoObjeto(categoriaTipoObjeto);
	}
	
}
