package org.jpericia.perito.views.preference.pages;

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
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.perito.businessdelegate.FuncaoPeritoDelegate;
import org.jpericia.perito.businessdelegate.TituloPeritoDelegate;
import org.jpericia.perito.businessdelegate.UfDelegate;
import org.jpericia.perito.messages.Messages;

public class PeritoPessoalPreferencePage extends PreferencePage {
	private Perito perito;

	private ComboExtended tituloCmo;

	private ComboExtended funcaoCmo;

	private Text nomeTxt;

	private FormattedText telefoneTxt;

	private FormattedText celularTxt;

	private ComboExtended UFCmo;

	private Text cidadeTxt;

	private Text bairroTxt;

	private Text enderecoTxt;

	private FormattedText numeroTxt;

	private Text logradouroTxt;

	public PeritoPessoalPreferencePage() {
		setTitle("Perito - Dados pessoais");
		setDescription("Dados do perito");
		noDefaultAndApplyButton();
		setValid(false);
	}

	public PeritoPessoalPreferencePage(Perito perito) {
		this.perito = perito;
		setTitle("Perito - Dados pessoais");
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

		// Campo titulo
		final Label tituloLbl = new Label(content, SWT.NONE);
		tituloLbl.setLayoutData(gridDataLbl);
		tituloLbl.setText(Messages.titulo);

		tituloCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);

		// Campo funcao
		final Label funcaoLbl = new Label(content, SWT.NONE);
		funcaoLbl.setLayoutData(gridDataLbl);
		funcaoLbl.setText(Messages.funcao);

		funcaoCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);

		// Campo nome
		final Label nomeLbl = new Label(content, SWT.NONE);
		nomeLbl.setLayoutData(gridDataLbl);
		nomeLbl.setText(Messages.nome);

		nomeTxt = new Text(content, SWT.BORDER);
		nomeTxt.setText(perito.getNome());
		nomeTxt.setTextLimit(60);
		nomeTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		nomeTxt.setLayoutData(gridDataTxt);

		// Campo telefone
		final Label telefoneLbl = new Label(content, SWT.NONE);
		telefoneLbl.setLayoutData(gridDataLbl);
		telefoneLbl.setText(Messages.telefone);

/*		telefoneTxt = new Text(content, SWT.BORDER);
		telefoneTxt.setText(perito.getTelefone().toString());
		telefoneTxt.setTextLimit(10);
		telefoneTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		telefoneTxt.setLayoutData(gridDataTxt);*/
		telefoneTxt = new FormattedText(content, SWT.BORDER | SWT.LEFT);
		telefoneTxt.setFormatter(new MaskFormatter("(##)####-####"));
		telefoneTxt.setValue(perito.getTelefone().toString());
		telefoneTxt.getControl().setLayoutData(gridDataTxt);

		telefoneTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		telefoneTxt.getControl().setLayoutData(gridDataTxt);
		

		// Campo celular
		final Label celularLbl = new Label(content, SWT.NONE);
		celularLbl.setLayoutData(gridDataLbl);
		celularLbl.setText(Messages.celular);

/*		celularTxt = new Text(content, SWT.BORDER);
		celularTxt.setText(perito.getCelular() == null ? "" : perito
				.getCelular().toString());
		celularTxt.setTextLimit(10);
		celularTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		celularTxt.setLayoutData(gridDataTxt);*/
		
		celularTxt = new FormattedText(content, SWT.BORDER | SWT.SINGLE);
		celularTxt.setFormatter(new MaskFormatter("(##)####-####"));
		celularTxt.setValue(perito.getCelular().toString());
		celularTxt.getControl().setLayoutData(gridDataTxt);	
		celularTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		celularTxt.getControl().setLayoutData(gridDataTxt);
		

		// Campo UF
		final Label UFLbl = new Label(content, SWT.NONE);
		UFLbl.setLayoutData(gridDataLbl);
		UFLbl.setText(Messages.uf);

		UFCmo = new ComboExtended(content, SWT.DROP_DOWN | SWT.READ_ONLY);

		// Campo cidade
		final Label cidadeLbl = new Label(content, SWT.NONE);
		cidadeLbl.setLayoutData(gridDataLbl);
		cidadeLbl.setText(Messages.cidade);

		cidadeTxt = new Text(content, SWT.BORDER);
		cidadeTxt.setText(perito.getCidade());
		cidadeTxt.setTextLimit(50);
		cidadeTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		cidadeTxt.setLayoutData(gridDataTxt);

		// Campo bairro
		final Label bairroLbl = new Label(content, SWT.NONE);
		bairroLbl.setLayoutData(gridDataLbl);
		bairroLbl.setText(Messages.bairro);

		bairroTxt = new Text(content, SWT.BORDER);
		bairroTxt.setText(perito.getBairro());
		bairroTxt.setTextLimit(50);
		bairroTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		bairroTxt.setLayoutData(gridDataTxt);

		// Campo endereco
		final Label enderecoLbl = new Label(content, SWT.NONE);
		enderecoLbl.setLayoutData(gridDataLbl);
		enderecoLbl.setText(Messages.endereco);

		enderecoTxt = new Text(content, SWT.BORDER);
		enderecoTxt.setText(perito.getEndereco());
		enderecoTxt.setTextLimit(60);
		enderecoTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		enderecoTxt.setLayoutData(gridDataTxt);

		// Campo número
		final Label numeroLbl = new Label(content, SWT.NONE);
		numeroLbl.setLayoutData(gridDataLbl);
		numeroLbl.setText(Messages.numero);
		
		numeroTxt = new FormattedText(content, SWT.BORDER | SWT.LEFT_TO_RIGHT);
		numeroTxt.setFormatter(new MaskFormatter("######"));
		numeroTxt.setValue(perito.getNumero().toString());
		numeroTxt.getControl().setLayoutData(gridDataTxt);
		numeroTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});

		// Campo logradouro
		final Label logradouroLbl = new Label(content, SWT.NONE);
		logradouroLbl.setLayoutData(gridDataLbl);
		logradouroLbl.setText(Messages.logradouro);

		logradouroTxt = new Text(content, SWT.BORDER);
		logradouroTxt.setText(perito.getLogradouro());
		logradouroTxt.setTextLimit(20);
		logradouroTxt.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		logradouroTxt.setLayoutData(gridDataTxt);

		initContents();

		return content;
	}

	private void initContents() {
		try {
			// Preenche a combo do titulo perito
			HashMap<Long, String> mapTituloPerito = new HashMap<Long, String>();
			AbstractResultList abstractTituloPeritoList = TituloPeritoDelegate
					.getInstance().pesquisar();
			List<AbstractEntity> tituloPeritoList = abstractTituloPeritoList
					.getResultList();
			for (Iterator iter = tituloPeritoList.iterator(); iter.hasNext();) {
				TituloPerito tituloPerito = (TituloPerito) iter.next();
				mapTituloPerito.put(tituloPerito.getCodigo(), tituloPerito
						.getTitulo());
			}

			this.tituloCmo.setMap(mapTituloPerito);
			this.tituloCmo.select(this.tituloCmo.indexOf(perito
					.getTituloPerito().getTitulo()));

			// Preenche com a combo da funcao do perito
			HashMap<Long, String> mapFuncaoPerito = new HashMap<Long, String>();
			AbstractResultList abstractFuncaoPeritoList = FuncaoPeritoDelegate
					.getInstance().pesquisar();
			List<AbstractEntity> funcaoPeritoList = abstractFuncaoPeritoList
					.getResultList();
			for (Iterator iter = funcaoPeritoList.iterator(); iter.hasNext();) {
				FuncaoPerito funcaoPerito = (FuncaoPerito) iter.next();
				mapFuncaoPerito.put(funcaoPerito.getCodigo(), funcaoPerito
						.getFuncao());
			}

			this.funcaoCmo.setMap(mapFuncaoPerito);
			this.funcaoCmo.select(this.funcaoCmo.indexOf(perito
					.getFuncaoPerito().getFuncao()));

			// Preenche a combo do uf do perito
			HashMap<Long, String> mapUFPerito = new HashMap<Long, String>();
			AbstractResultList abstractUFPeritoList = UfDelegate.getInstance()
					.pesquisar();
			List<AbstractEntity> ufPeritoList = abstractUFPeritoList
					.getResultList();
			for (Iterator iter = ufPeritoList.iterator(); iter.hasNext();) {
				Uf uf = (Uf) iter.next();
				mapUFPerito.put(uf.getCodigo(), uf.getUf());
			}

			this.UFCmo.setMap(mapUFPerito);
			this.UFCmo.select(this.UFCmo.indexOf(perito.getUf().getUf()));

			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		} catch (BusinessDelegateException e) {
			// Tratar erro
		}
	}

	private void updatePageComplete() {
		setValid(false);
		if ("".equals(nomeTxt.getText())) {
			setMessage(null);
			setErrorMessage(Messages.informaNomePerito);
			return;
		} else if ("".equals(telefoneTxt.getValue()) || "          ".equals(telefoneTxt.getValue().toString())) {
			setMessage(null);
			setErrorMessage(Messages.informaTelefonePerito);
			return;
		} else if ("".equals(cidadeTxt.getText())) {
			setMessage(null);
			setErrorMessage(Messages.informaCidadePerito);
			return;
		} else if ("".equals(bairroTxt.getText())) {
			setMessage(null);
			setErrorMessage(Messages.informaBairroPerito);
			return;
		} else if ("".equals(enderecoTxt.getText())) {
			setMessage(null);
			setErrorMessage(Messages.informaEnderecoPerito);
			return;
		} else if ("".equals(numeroTxt.getValue()) || "      ".equals(numeroTxt.getValue().toString())) {
			setMessage(null);
			setErrorMessage(Messages.informaNumeroPerito);
			return;
		} else if ("".equals(logradouroTxt.getText())) {
			setMessage(null);
			setErrorMessage(Messages.informaLogradouroPerito);
			return;
		}
		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}

	public void setEntityObject() 
	{	
		if (this.tituloCmo != null) 
		{
			TituloPerito tituloPerito = new TituloPerito();
			tituloPerito.setCodigo(Long.parseLong(this.tituloCmo.getValue()));
			tituloPerito.setTitulo(this.tituloCmo.getText());

			FuncaoPerito funcaoPerito = new FuncaoPerito();
			funcaoPerito.setCodigo(Long.parseLong(this.funcaoCmo.getValue()));
			funcaoPerito.setFuncao(this.funcaoCmo.getText());

			Uf ufPerito = new Uf();
			ufPerito.setCodigo(Long.parseLong(this.UFCmo.getValue()));
			ufPerito.setUf(this.UFCmo.getText());

			perito.setTituloPerito(tituloPerito);
			perito.setFuncaoPerito(funcaoPerito);
			perito.setUf(ufPerito);
			perito.setNome(nomeTxt.getText());
			perito.setTelefone(Long.parseLong(telefoneTxt.getValue().toString().trim()));
			perito.setCelular(Long.parseLong(celularTxt.getValue().toString().trim()));
			perito.setCidade(cidadeTxt.getText());
			perito.setBairro(bairroTxt.getText());
			perito.setEndereco(enderecoTxt.getText());
			perito.setNumero(Integer.parseInt(numeroTxt.getValue().toString().replaceAll(" ", "")));
			perito.setLogradouro(logradouroTxt.getText());
		}
	}
}
