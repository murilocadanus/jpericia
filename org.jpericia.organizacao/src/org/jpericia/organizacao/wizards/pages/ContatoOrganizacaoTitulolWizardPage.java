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
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.organizacao.businessdelegate.TituloContatoDelegate;
import org.jpericia.organizacao.messages.Messages;

public class ContatoOrganizacaoTitulolWizardPage extends WizardPage
{
	private ComboExtended organizacaoCmo;
	
	private ComboExtended tituloContatoCmo;
	
	private ContatoOrganizacao contatoOrganizacao;
	
	public ContatoOrganizacaoTitulolWizardPage()
	{
		super("dadosTitulo");
		setTitle(Messages.tituloContatoOrganizacaoWizard);
		setDescription(Messages.subtituloContatoOrganizacaoWizard);
	}

	public ContatoOrganizacaoTitulolWizardPage(ContatoOrganizacao contatoOrganizacao)
	{
		super("dadosTitulo");
		setTitle(Messages.tituloContatoOrganizacaoWizard);
		setDescription(Messages.subtituloContatoOrganizacaoWizard);
		this.contatoOrganizacao = contatoOrganizacao;
	}
	
	public void createControl(Composite parent)
	{
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		setControl(container);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);

		// Campo organização
		final Label organizacaoLbl = new Label(container, SWT.NONE);
		organizacaoLbl.setLayoutData(gridDataLbl);
		organizacaoLbl.setText(Messages.organizacao);
		
		organizacaoCmo = new ComboExtended(container, SWT.BORDER | SWT.READ_ONLY);
		organizacaoCmo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		
		// Campo titulo contato
		final Label tituloContatoLbl = new Label(container, SWT.NONE);
		tituloContatoLbl.setLayoutData(gridDataLbl);
		tituloContatoLbl.setText(Messages.tituloContato);
		
		tituloContatoCmo = new ComboExtended(container, SWT.BORDER | SWT.READ_ONLY);
		tituloContatoCmo.addModifyListener(new ModifyListener()
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
			//Preenche a combo de organizacao
			HashMap<Long, String> mapOrganizacao = new HashMap<Long, String>();
			AbstractResultList abstractOrganizacaoList = OrganizacaoDelegate.getInstance().pesquisar();
			List<AbstractEntity> organizacaoList = abstractOrganizacaoList.getResultList();
			for (Iterator iter = organizacaoList.iterator(); iter.hasNext();) {
				Organizacao organizacao = (Organizacao) iter.next();
				mapOrganizacao.put(organizacao.getCodigo(), organizacao.getNome());
			}
			
			this.organizacaoCmo.setMap(mapOrganizacao);
			
			//Preenche a combo do titulo contato
			HashMap<Long, String> mapTituloContato = new HashMap<Long, String>();
			AbstractResultList abstractTituloContatoList = TituloContatoDelegate.getInstance().pesquisar();
			List<AbstractEntity> tituloContatoList = abstractTituloContatoList.getResultList();
			for (Iterator iter = tituloContatoList.iterator(); iter.hasNext();) {
				TituloContato tituloContato = (TituloContato) iter.next();
				mapTituloContato.put(tituloContato.getCodigo(), tituloContato.getTitulo());
			}
			
			this.tituloContatoCmo.setMap(mapTituloContato);
			
			updatePageComplete();
			setMessage(null);
			setErrorMessage(null);
		}
		catch(Exception e)
		{
			//Tratar erro
		}
	}
	
	private void updatePageComplete()
	{
		setPageComplete(false);
		
		if("".equals(organizacaoCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaOrganizacao);
			return;
		}
		else if("".equals(tituloContatoCmo.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaTituloContato);
			return;
		}
		
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		TituloContato tituloContato = new TituloContato();
		tituloContato.setCodigo(Long.parseLong(this.tituloContatoCmo.getValue()));
		
		Organizacao organizacao = new Organizacao();
		organizacao.setCodigo(Long.parseLong(this.organizacaoCmo.getValue()));
		
		contatoOrganizacao.setTituloContato(tituloContato);
		contatoOrganizacao.setOrganizacao(organizacao);
	}	
}
