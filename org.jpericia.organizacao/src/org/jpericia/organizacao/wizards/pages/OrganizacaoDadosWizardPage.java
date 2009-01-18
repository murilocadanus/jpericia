package org.jpericia.organizacao.wizards.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
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

public class OrganizacaoDadosWizardPage extends WizardPage
{
	private Text cidadeTxt;
	
	private ComboExtended uFCmo;
	
	private Text bairroTxt;
	
	private Text enderecoTxt;
	
	private Text logradouroTxt;
	
    private FormattedText numeroTxt;
	
	private Organizacao organizacao;
	
	public OrganizacaoDadosWizardPage()
	{
		super("dadosContato");
		setTitle(Messages.tituloOrganizacaoWizard);
		setDescription(Messages.subtituloOrganizacaoDadosWizard);
	}

	public OrganizacaoDadosWizardPage(Organizacao organizacao)
	{
		super("dadosContato");
		setTitle(Messages.tituloOrganizacaoWizard);
		setDescription(Messages.subtituloOrganizacaoDadosWizard);
		this.organizacao = organizacao;
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

		// Campo uf
		final Label uFLbl = new Label(container, SWT.NONE);
		uFLbl.setLayoutData(gridDataLbl);
		uFLbl.setText(Messages.uf);
		
		uFCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		uFCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		// Campo cidade
		final Label telefoneLbl = new Label(container, SWT.NONE);
		telefoneLbl.setLayoutData(gridDataLbl);
		telefoneLbl.setText(Messages.cidade);
		
		cidadeTxt = new Text(container, SWT.BORDER);
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
		final Label celularLbl = new Label(container, SWT.NONE);
		celularLbl.setLayoutData(gridDataLbl);
		celularLbl.setText(Messages.bairro);
		
		bairroTxt = new Text(container, SWT.BORDER);
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
		final Label enderecoLbl = new Label(container, SWT.NONE);
		enderecoLbl.setLayoutData(gridDataLbl);
		enderecoLbl.setText(Messages.endereco);
		
		enderecoTxt = new Text(container, SWT.BORDER);
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
		final Label logradouroLbl = new Label(container, SWT.NONE);
		logradouroLbl.setLayoutData(gridDataLbl);
		logradouroLbl.setText(Messages.logradouro);
		
		logradouroTxt = new Text(container, SWT.BORDER);
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

		
		final Label numeroLbl = new Label(container, SWT.NONE);
		numeroLbl.setLayoutData(gridDataLbl);
		numeroLbl.setText(Messages.numero);
		
		numeroTxt = new FormattedText(container, SWT.BORDER | SWT.LEFT_TO_RIGHT);
		numeroTxt.setFormatter(new MaskFormatter("######"));
		numeroTxt.getControl().setLayoutData(gridDataTxt);
		numeroTxt.getControl().addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
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
			//Preenche a combo do uf do perito
			HashMap<Long, String> mapUFPerito = new HashMap<Long, String>();
			AbstractResultList abstractUFPeritoList = UfDelegate.getInstance().pesquisar();
			List<AbstractEntity> ufPeritoList = abstractUFPeritoList.getResultList();
			for (Iterator iter = ufPeritoList.iterator(); iter.hasNext();) {
				Uf uf = (Uf) iter.next();
				mapUFPerito.put(uf.getCodigo(), uf.getUf());
			}

			this.uFCmo.setMap(mapUFPerito);

			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		}
		catch(BusinessDelegateException e)
		{
			//Tratar erro
		}
	}
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
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
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{	
		Uf ufOrganizacao = new Uf();
		ufOrganizacao.setCodigo(Long.parseLong(this.uFCmo.getValue()));
		
		organizacao.setCidade(cidadeTxt.getText());
		organizacao.setUf(ufOrganizacao);
		organizacao.setBairro(bairroTxt.getText());
		organizacao.setEndereco(enderecoTxt.getText());
		organizacao.setLogradouro(logradouroTxt.getText());
		organizacao.setNumero(Long.valueOf(numeroTxt.getValue().toString().trim()));
	}
}
