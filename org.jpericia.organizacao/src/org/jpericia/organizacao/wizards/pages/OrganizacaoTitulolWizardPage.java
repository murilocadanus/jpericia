package org.jpericia.organizacao.wizards.pages;

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
import org.eclipse.swt.widgets.Composite;
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

public class OrganizacaoTitulolWizardPage extends WizardPage
{
	private ComboExtended tipoOrganizacaoCmo;
	
	private Text nomeOrganizacaoTxt;
	
	private Organizacao organizacao;
	
	public OrganizacaoTitulolWizardPage()
	{
		super("dadosTitulo");
		setTitle(Messages.tituloOrganizacaoWizard);
		setDescription(Messages.subtituloOrganizacaoWizard);
	}
	
	public OrganizacaoTitulolWizardPage(Organizacao organizacao)
	{
		super("dadosTitulo");
		setTitle(Messages.tituloOrganizacaoWizard);
		setDescription(Messages.subtituloOrganizacaoWizard);
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

		// Campo organização
		final Label organizacaoLbl = new Label(container, SWT.NONE);
		organizacaoLbl.setLayoutData(gridDataLbl);
		organizacaoLbl.setText(Messages.tipoOrganizacao);
		
		tipoOrganizacaoCmo = new ComboExtended(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		tipoOrganizacaoCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		// Campo titulo contato
		final Label tituloContatoLbl = new Label(container, SWT.NONE);
		tituloContatoLbl.setLayoutData(gridDataLbl);
		tituloContatoLbl.setText(Messages.nomeOrganizacao);
		
		nomeOrganizacaoTxt = new Text(container, SWT.BORDER);
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
			//Preenche a combo do tipo de organizacao
			HashMap<Long, String> mapTipoOrganizacao = new HashMap<Long, String>();
			AbstractResultList abstractTipoOrganizacaoList = TipoOrganizacaoDelegate.getInstance().pesquisar();
			List<AbstractEntity> tituloPeritoList = abstractTipoOrganizacaoList.getResultList();
			for (Iterator iter = tituloPeritoList.iterator(); iter.hasNext();) {
				TipoOrganizacao tipoOrganizacao = (TipoOrganizacao) iter.next();
				mapTipoOrganizacao.put(tipoOrganizacao.getCodigo(), tipoOrganizacao.getNome());
			}
			
			this.tipoOrganizacaoCmo.setMap(mapTipoOrganizacao);
			
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
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		TipoOrganizacao tipoOrganizacao = new TipoOrganizacao();
		tipoOrganizacao.setCodigo(Long.parseLong(this.tipoOrganizacaoCmo.getValue()));
		
		organizacao.setTipoOrganizacao(tipoOrganizacao);
		organizacao.setNome(nomeOrganizacaoTxt.getText());
	}
}
