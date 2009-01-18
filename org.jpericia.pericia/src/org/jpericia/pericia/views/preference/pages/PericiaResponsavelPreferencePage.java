package org.jpericia.pericia.views.preference.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.organizacao.businessdelegate.ContatoOrganizacaoDelegate;
import org.jpericia.pericia.messages.Messages;
import org.jpericia.perito.businessdelegate.PeritoDelegate;

public class PericiaResponsavelPreferencePage extends PreferencePage
{
	private Pericia pericia;

	private ComboExtended contatoOrganizacaoCmo;
	
	private ComboExtended peritoResponsavelCmo;
	
	List<AbstractEntity> contatoOrganizacaoList;
	
	private Label nomeOrganizacaoValueLbl;
	
	private Label tipoOrganizacaoValueLbl;
	
    public PericiaResponsavelPreferencePage()
    {
        setTitle("Pericia");
        setDescription("Dados do pericia");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public PericiaResponsavelPreferencePage(Pericia pericia)
    {
        this.pericia = pericia;
        setTitle("Pericia");
        noDefaultAndApplyButton();
        setValid(true);
    }
	
	@Override
	protected Control createContents(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo tipo organizacao
		final Label tipoOrganizacaoLbl = new Label(container, SWT.NONE);
		tipoOrganizacaoLbl.setLayoutData(gridDataLbl);
		tipoOrganizacaoLbl.setText(Messages.tipoOrganizacao);
		
		tipoOrganizacaoValueLbl = new Label(container, SWT.BORDER);
		tipoOrganizacaoValueLbl.setLayoutData(gridDataTxt);
		
		// Campo nome organizacao
		final Label nomeOrganizacaoLbl = new Label(container, SWT.NONE);
		nomeOrganizacaoLbl.setLayoutData(gridDataLbl);
		nomeOrganizacaoLbl.setText(Messages.organizacao);
		
		nomeOrganizacaoValueLbl = new Label(container, SWT.BORDER);
		nomeOrganizacaoValueLbl.setLayoutData(gridDataTxt);
		
		// Campo contato organizacao
		final Label contatoOrganizacaoLbl = new Label(container, SWT.NONE);
		contatoOrganizacaoLbl.setLayoutData(gridDataLbl);
		contatoOrganizacaoLbl.setText(Messages.contatoOrganizacao);
		
		contatoOrganizacaoCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		contatoOrganizacaoCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});

		// Campo perito responsavel
		final Label peritoResponsavelLbl = new Label(container, SWT.NONE);
		peritoResponsavelLbl.setLayoutData(gridDataLbl);
		peritoResponsavelLbl.setText(Messages.peritoResponsavel);
		
		peritoResponsavelCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		peritoResponsavelCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		initContents();
		return container;
	}
	
	private void initContents()
	{
		try
		{
			//Preenche a combo do contato organizacao
			HashMap<Long, String> mapContatoOrganizacao = new HashMap<Long, String>();
			AbstractResultList abstractContatoOrganizacaoList = ContatoOrganizacaoDelegate.getInstance().pesquisar();
			contatoOrganizacaoList = abstractContatoOrganizacaoList.getResultList();
			for (Iterator iter = contatoOrganizacaoList.iterator(); iter.hasNext();) {
				ContatoOrganizacao contatoOrganizacao = (ContatoOrganizacao) iter.next();
				mapContatoOrganizacao.put(contatoOrganizacao.getCodigo(), contatoOrganizacao.getNome());
			}
			
			this.contatoOrganizacaoCmo.setMap(mapContatoOrganizacao);
			this.contatoOrganizacaoCmo.select(this.contatoOrganizacaoCmo.indexOf(pericia.getContatoOrganizacao().getNome()));
			
			//Preenche a combo do perito responsavel
			HashMap<Long, String> mapPeritoResponsavel = new HashMap<Long, String>();
			AbstractResultList abstractPeritoResponsavelList = PeritoDelegate.getInstance().pesquisar();
			List<AbstractEntity> peritoResponsavelList = abstractPeritoResponsavelList.getResultList();
			for (Iterator iter = peritoResponsavelList.iterator(); iter.hasNext();) {
				Perito perito = (Perito) iter.next();
				mapPeritoResponsavel.put(perito.getCodigo(), perito.getNome());
			}
			
			this.peritoResponsavelCmo.setMap(mapPeritoResponsavel);
			this.peritoResponsavelCmo.select(this.peritoResponsavelCmo.indexOf(pericia.getPeritoResponsavel().getNome()));
		
		}
		catch(BusinessDelegateException e)
		{
			//Tratar erro
		}
		
		updatePageComplete();
		setMessage(null);
		setErrorMessage(null);
	}	

	private void updatePageComplete()
	{
		setValid(false);
				
		if("".equals(contatoOrganizacaoCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaContatoOrganizacao);
			return;
		}
		else
		{
			for (Iterator<AbstractEntity> iter = contatoOrganizacaoList.iterator(); iter.hasNext();)
			{
				ContatoOrganizacao contatoOrganizacao = (ContatoOrganizacao) iter.next();
				if(Long.valueOf(contatoOrganizacaoCmo.getValue()).equals(contatoOrganizacao.getCodigo()))
				{
					nomeOrganizacaoValueLbl.setText(contatoOrganizacao.getOrganizacao().getNome());
					tipoOrganizacaoValueLbl.setText(contatoOrganizacao.getOrganizacao().getTipoOrganizacao().getNome());
					break;
				}
			}
		}		
		
		
		if("".equals(peritoResponsavelCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaPeritoResponsavel);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject() 
	{	
		if (this.contatoOrganizacaoCmo != null) 
		{
			ContatoOrganizacao contatoOrganizacao = new ContatoOrganizacao();
			contatoOrganizacao.setCodigo(Long.parseLong(this.contatoOrganizacaoCmo.getValue()));
			contatoOrganizacao.setNome(this.contatoOrganizacaoCmo.getText());

			Perito peritoResponsavel = new Perito();
			peritoResponsavel.setCodigo(Long.parseLong(this.peritoResponsavelCmo.getValue()));
			peritoResponsavel.setNome(this.peritoResponsavelCmo.getText());

			pericia.setContatoOrganizacao(contatoOrganizacao);
			pericia.setPeritoResponsavel(peritoResponsavel);
		}
	}	
}
