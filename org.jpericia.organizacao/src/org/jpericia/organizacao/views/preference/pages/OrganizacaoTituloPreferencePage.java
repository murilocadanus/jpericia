package org.jpericia.organizacao.views.preference.pages;

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
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.organizacao.businessdelegate.TipoOrganizacaoDelegate;
import org.jpericia.organizacao.messages.Messages;

public class OrganizacaoTituloPreferencePage extends PreferencePage {
	private Organizacao organizacao;

	private ComboExtended tipoOrganizacaoCmo;

	private Text nomeOrganizacaoTxt;

	public OrganizacaoTituloPreferencePage() {
		setTitle("Organizacao - Dados organizacao");
		setDescription("Dados do perito");
		noDefaultAndApplyButton();
		setValid(false);
	}

	public OrganizacaoTituloPreferencePage(Organizacao organizacao) {
		this.organizacao = organizacao;
		setTitle("Organizacao - Dados organizacao");
		noDefaultAndApplyButton();
		setValid(true);
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		content.setLayout(gridLayout);

		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo organização
		final Label organizacaoLbl = new Label(content, SWT.NONE);
		organizacaoLbl.setLayoutData(gridDataLbl);
		organizacaoLbl.setText(Messages.tipoOrganizacao);
		
		tipoOrganizacaoCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);
		tipoOrganizacaoCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		// Campo titulo contato
		final Label tituloContatoLbl = new Label(content, SWT.NONE);
		tituloContatoLbl.setLayoutData(gridDataLbl);
		tituloContatoLbl.setText(Messages.nomeOrganizacao);
		
		nomeOrganizacaoTxt = new Text(content, SWT.BORDER);
		nomeOrganizacaoTxt.setText(organizacao.getNome());
		nomeOrganizacaoTxt.setTextLimit(50);
		nomeOrganizacaoTxt.setLayoutData(gridDataTxt);
		nomeOrganizacaoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		

		initContents();

		return content;
	}

	private void initContents() {
		try {
			// Preenche a combo do tipoOrganizacao
			HashMap<Long, String> mapTipoOrganizacao = new HashMap<Long, String>();
			AbstractResultList abstractTipoOrganizacaoList = TipoOrganizacaoDelegate
					.getInstance().pesquisar();
			List<AbstractEntity> tipoOrganizacaoList = abstractTipoOrganizacaoList
					.getResultList();
			for (Iterator iter = tipoOrganizacaoList.iterator(); iter.hasNext();) {
				TipoOrganizacao tipoOrganizacao = (TipoOrganizacao) iter.next();
				mapTipoOrganizacao.put(tipoOrganizacao.getCodigo(), tipoOrganizacao
						.getNome());
			}

			this.tipoOrganizacaoCmo.setMap(mapTipoOrganizacao);
			this.tipoOrganizacaoCmo.select(this.tipoOrganizacaoCmo.indexOf(organizacao
					.getTipoOrganizacao().getNome()));

			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		} catch (BusinessDelegateException e) {
			// Tratar erro
		}
	}

	private void updatePageComplete() {
		setValid(false);
		
		if("".equals(nomeOrganizacaoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaNomeOrganizacao);
			return;
		}
		else if("".equals(tipoOrganizacaoCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTipoOrganizacao);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject() 
	{	
		if (this.tipoOrganizacaoCmo != null) 
		{
			TipoOrganizacao tipoOrganizacao = new TipoOrganizacao();
			tipoOrganizacao.setCodigo(Long.parseLong(this.tipoOrganizacaoCmo.getValue()));
			tipoOrganizacao.setNome(this.tipoOrganizacaoCmo.getText());
			
			organizacao.setTipoOrganizacao(tipoOrganizacao);
			organizacao.setNome(nomeOrganizacaoTxt.getText());
		}
	}
}
