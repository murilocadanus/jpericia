package org.jpericia.organizacao.views.preference.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
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
import org.jpericia.common.entity.generic.Uf;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.organizacao.businessdelegate.UfDelegate;
import org.jpericia.organizacao.messages.Messages;

public class OrganizacaoDadosPreferencePage extends PreferencePage {
	private Organizacao organizacao;
	
	private Text cidadeTxt;
	
	private ComboExtended uFCmo;
	
	private Text bairroTxt;
	
	private Text enderecoTxt;
	
	private Text logradouroTxt;
	
	private FormattedText numeroTxt;

	public OrganizacaoDadosPreferencePage() {
		setTitle("Organizacao - Localização");
		setDescription("Dados do perito");
		noDefaultAndApplyButton();
		setValid(false);
	}

	public OrganizacaoDadosPreferencePage(Organizacao organizacao) {
		this.organizacao = organizacao;
		setTitle("Organizacao - Localização");
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

		// Campo uf
		final Label uFLbl = new Label(content, SWT.NONE);
		uFLbl.setLayoutData(gridDataLbl);
		uFLbl.setText(Messages.uf);
		
		uFCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);		
		uFCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		// Campo cidade
		final Label telefoneLbl = new Label(content, SWT.NONE);
		telefoneLbl.setLayoutData(gridDataLbl);
		telefoneLbl.setText(Messages.cidade);
		
		cidadeTxt = new Text(content, SWT.BORDER);
		cidadeTxt.setText(organizacao.getCidade());
		cidadeTxt.setTextLimit(10);
		cidadeTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		cidadeTxt.setLayoutData(gridDataTxt);
		
		// Campo bairro
		final Label celularLbl = new Label(content, SWT.NONE);
		celularLbl.setLayoutData(gridDataLbl);
		celularLbl.setText(Messages.bairro);
		
		bairroTxt = new Text(content, SWT.BORDER);
		bairroTxt.setText(organizacao.getBairro());
		bairroTxt.setTextLimit(40);
		bairroTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		bairroTxt.setLayoutData(gridDataTxt);
		
		// Campo endereco
		final Label enderecoLbl = new Label(content, SWT.NONE);
		enderecoLbl.setLayoutData(gridDataLbl);
		enderecoLbl.setText(Messages.endereco);
		
		enderecoTxt = new Text(content, SWT.BORDER);
		enderecoTxt.setText(organizacao.getEndereco());
		enderecoTxt.setTextLimit(40);
		enderecoTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		enderecoTxt.setLayoutData(gridDataTxt);
			
		//campo logradouro.
		final Label logradouroLbl = new Label(content, SWT.NONE);
		logradouroLbl.setLayoutData(gridDataLbl);
		logradouroLbl.setText(Messages.logradouro);
		
		logradouroTxt = new Text(content, SWT.BORDER);
		logradouroTxt.setText(organizacao.getLogradouro());
		logradouroTxt.setTextLimit(40);
		logradouroTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		logradouroTxt.setLayoutData(gridDataTxt);		
		
		//Campo numero.
		final Label numeroLbl = new Label(content, SWT.NONE);
		numeroLbl.setLayoutData(gridDataLbl);
		numeroLbl.setText(Messages.numero);
		
		/*		numeroTxt = new Text(content, SWT.BORDER);
		numeroTxt.setText(organizacao.getNumero().toString());
		numeroTxt.setTextLimit(40);*/
		
		numeroTxt = new FormattedText(content, SWT.BORDER | SWT.LEFT_TO_RIGHT);
		numeroTxt.setFormatter(new MaskFormatter("######"));
		numeroTxt.setValue(organizacao.getNumero().toString());
		numeroTxt.getControl().setLayoutData(gridDataTxt);
		numeroTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		numeroTxt.getControl().setLayoutData(gridDataTxt);
		
		initContents();

		return content;
	}

	private void initContents() {
		try
		{
			//Preenche a combo do uf do perito
			HashMap<Long, String> mapUFPerito = new HashMap<Long, String>();
			AbstractResultList abstractUFPeritoList = UfDelegate.getInstance().pesquisar();
			List<AbstractEntity> ufPeritoList = abstractUFPeritoList.getResultList();
			for (Iterator iter = ufPeritoList.iterator(); iter.hasNext();) {
				Uf uf = (Uf) iter.next();
				mapUFPerito.put(uf.getCodigo(), uf.getUf());
			}

			this.uFCmo.setMap(mapUFPerito);
			this.uFCmo.select(this.uFCmo.indexOf(organizacao.getUf().getUf()));
			
			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		}
		catch(BusinessDelegateException e)
		{
			//Tratar erro
		}
	}

	private void updatePageComplete() {
		setValid(false);
			
		if("".equals(uFCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaUF);
			return;
		}
		else if("".equals(cidadeTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaCidade);
			return;
		}
		else if("".equals(bairroTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaBairro);
			return;
		}
		else if("".equals(enderecoTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaEndereco);
			return;
		}
		else if("".equals(logradouroTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaLogradouro);
			return;
		}		
		else if("".equals(numeroTxt.getValue().toString()) || "      ".equals(numeroTxt.getValue().toString()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaNumero);
			return;
		}
		
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject() 
	{	
		if (this.cidadeTxt != null) 
		{	
			Uf ufOrganizacao = new Uf();
			ufOrganizacao.setCodigo(Long.parseLong(this.uFCmo.getValue()));
			ufOrganizacao.setUf(this.uFCmo.getText());
			
			organizacao.setCidade(cidadeTxt.getText());
			organizacao.setUf(ufOrganizacao);
			organizacao.setBairro(bairroTxt.getText());
			organizacao.setEndereco(enderecoTxt.getText());
			organizacao.setLogradouro(logradouroTxt.getText());
			organizacao.setNumero(Long.valueOf(numeroTxt.getValue().toString().trim()));
		}
	}
}
