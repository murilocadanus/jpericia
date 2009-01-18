package org.jpericia.analise.views.preference.pages;

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
import org.jpericia.analise.businessdelegate.AnaliseDelegate;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class CenaDadosPreferencePage extends PreferencePage
{
	private Cena cena;

	private Text descricaoTxt;
	
	private ComboExtended analiseCmo;
	
    public CenaDadosPreferencePage()
    {
        setTitle("Cena - Dados");
        setDescription("Dados da cena");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public CenaDadosPreferencePage(Cena cena)
    {
        this.cena = cena;
        setTitle("Cena - Dados");
        setDescription("Dados da cena");
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
		
		// Campo analise
		final Label analiseTxt = new Label(content, SWT.NONE);
		analiseTxt.setLayoutData(gridDataLbl);
		analiseTxt.setText(Messages.analise);
		
		analiseCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);

		// Campo descricao
		final Label descricaoLbl = new Label(content, SWT.NONE);
		descricaoLbl.setLayoutData(gridDataLbl);
		descricaoLbl.setText(Messages.descricao);
		
		descricaoTxt = new Text(content, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		descricaoTxt.setText(cena.getDescricao());
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
			//Preenche a combo da analise
			HashMap<Long, String> mapObjeto = new HashMap<Long, String>();
			AbstractResultList abstractAnaliseList;
	
			abstractAnaliseList = AnaliseDelegate.getInstance().pesquisar();

			List<AbstractEntity> analiseList = abstractAnaliseList.getResultList();
			for (Iterator<AbstractEntity> iter = analiseList.iterator(); iter.hasNext();)
			{
				Analise analise = (Analise) iter.next();
				mapObjeto.put(analise.getCodigo(), analise.getTitulo());
			}
			
			this.analiseCmo.setMap(mapObjeto);
			this.analiseCmo.select(this.analiseCmo.indexOf(cena.getAnalise().getTitulo()));
			
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
		if("".equals(descricaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaCenaTitulo);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		if(null != this.analiseCmo)
		{
			Analise analise = new Analise();
			analise.setCodigo(Long.parseLong(analiseCmo.getValue()));
			analise.setTitulo(analiseCmo.getText());
			
			cena.setAnalise(analise);
			cena.setDescricao(descricaoTxt.getText());
		}
	}
}
