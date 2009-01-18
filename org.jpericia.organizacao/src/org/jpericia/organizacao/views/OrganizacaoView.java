package org.jpericia.organizacao.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.core.components.ComboExtended;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.core.ui.preferences.PropertyChangeEvents;
import org.jpericia.organizacao.OrganizacaoPlugin;
import org.jpericia.organizacao.actions.OrganizacaoEditarAction;
import org.jpericia.organizacao.actions.OrganizacaoRemoverAction;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.organizacao.businessdelegate.TipoOrganizacaoDelegate;
import org.jpericia.organizacao.messages.Messages;
import org.jpericia.organizacao.views.listeners.OrganizacaoListener;
import org.jpericia.organizacao.views.sorters.OrganizacaoSorter;

public class OrganizacaoView extends ViewPart implements PropertyChangeListener
{
	public static final String VIEW_ID = "org.jpericia.organizacao.views.organizacaoView";
	
	private Organizacao organizacao;
	
	private OrganizacaoRemoverAction removerAction;
	
	private OrganizacaoEditarAction editarAction;

	private TableViewer viewer;

	private Text tipoOrganizacaoText;
	
	private Text nomeText;

	ViewerFilter filter = new ViewerFilter()
	{
		public boolean select(Viewer viewer1, Object parentElement,
				Object element)
		{
			return (OrganizacaoView.this.nomeText.getText().length() == 0 || ((TipoOrganizacao) element)
					.getNome()
					.matches(
							replaceWildCard(OrganizacaoView.this.nomeText.getText())));
		}
	};

	static String replaceWildCard(String value)
	{
		return value.replaceAll("\\*", "(\\\\s*?\\\\S)*?"); //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider
	{
		public void inputChanged(Viewer v, Object oldInput, Object newInput)
		{
			// do nothing
		}

		public void dispose()
		{
			// do nothing
		}

		public Object[] getElements(Object parent)
		{
			return ((OrganizacaoListener) parent).toArray(new Organizacao[0]);
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider
	{
		public String getColumnText(Object obj, int index)
		{
			String returnValue = null;
			switch (index)
			{
			case 0:
				returnValue = ((Organizacao) obj).getTipoOrganizacao().getNome();
				break;
			case 1:
				returnValue = ((Organizacao) obj).getNome();
				break;
			case 2:
				returnValue = ((Organizacao) obj).getCidade();
				break;
			case 3:
				returnValue = ((Organizacao) obj).getUf().getUf();
				break;				
			default:
				break;
			}
			return returnValue;
		}

		public Image getColumnImage(Object obj, int index)
		{
			return null;
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{
		parent.setLayout(new GridLayout(4, false));
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		parent.setLayoutData(gd);

		GridData gdSpan = new GridData();
		gdSpan.horizontalSpan = 4;
		
		Label infoLabel = new Label(parent, SWT.NONE);
		infoLabel.setLayoutData(gdSpan);
		infoLabel.setText(Messages.infoOrganizacao);
		
		Label userLabel = new Label(parent, SWT.NONE);
		userLabel.setText(Messages.tipoOrganizacao);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 100;

		//Campo organizacao
		userLabel.setLayoutData(gd);
		this.tipoOrganizacaoText = new Text(parent, SWT.BORDER);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 200;
		this.tipoOrganizacaoText.setLayoutData(gd);

		Label ipLabel = new Label(parent, SWT.NONE);
		ipLabel.setText(Messages.nome);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 100;

		//campo nome
		ipLabel.setLayoutData(gd);
		this.nomeText = new Text(parent, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		this.nomeText.setLayoutData(gd);
		
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(2, false));
		gd = new GridData(SWT.RIGHT, SWT.BEGINNING, true, false);
		gd.horizontalSpan = 4;
		buttonComposite.setLayoutData(gd);

		Button applyButton = new Button(buttonComposite, SWT.PUSH);
		gd = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		applyButton.setText(Messages.pesquisar);
		applyButton.setLayoutData(gd);
		applyButton.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				try
				{
					//Verificar a necessidade de caching.
					//if (PeritoView.this.loginText.getText().equals("") && PeritoView.this.tituloText.getText().equals("") 
					//	&& PeritoView.this.funcaoText.getText().equals("") && PeritoView.this.nomeText.getText().equals(""))
					//{
						OrganizacaoView.this.viewer.setInput(OrganizacaoDelegate.getInstance().pesquisar());
					//}
				}
				catch(BusinessDelegateException e)
				{
					//Trata erro
				}
					
				
				OrganizacaoView.this.viewer.addFilter(OrganizacaoView.this.filter);
				
			}
		});

		Button clearButton = new Button(buttonComposite, SWT.PUSH);
		gd = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		clearButton.setText(Messages.limpar);
		clearButton.setLayoutData(gd);
		clearButton.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				OrganizacaoView.this.nomeText.setText("");
				OrganizacaoView.this.tipoOrganizacaoText.setText("");
			}
		});

		final Table resultTable = new Table(parent, SWT.FULL_SELECTION
				| SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		resultTable.setHeaderVisible(true);
		resultTable.setLinesVisible(true);

		final TableColumn tc0 = new TableColumn(resultTable, SWT.NONE);
		tc0.setText(Messages.tipoOrganizacao);
		tc0.setWidth(150);
		tc0.setMoveable(true);

		final TableColumn tc1 = new TableColumn(resultTable, SWT.NONE);
		tc1.setText(Messages.nome);
		tc1.setData(new ColumnWeightData(25));
		tc1.setMoveable(true);
		tc1.setWidth(150);
		
		final TableColumn tc2 = new TableColumn(resultTable, SWT.NONE);
		tc2.setText(Messages.cidade);
		tc2.setData(new ColumnWeightData(25));
		tc2.setMoveable(true);
		tc2.setWidth(150);

		final TableColumn tc3 = new TableColumn(resultTable, SWT.NONE);
		tc3.setText(Messages.uf);
		tc3.setData(new ColumnWeightData(25));
		tc3.setMoveable(true);
		tc3.setWidth(150);		
		
		Listener sortListener = new Listener()
		{
			public void handleEvent(Event e)
			{
				// determine new sort column and direction
				TableColumn sortColumn = OrganizacaoView.this.viewer.getTable()
						.getSortColumn();
				TableColumn currentColumn = (TableColumn) e.widget;
				int dir = OrganizacaoView.this.viewer.getTable().getSortDirection();
				if (sortColumn == currentColumn)
				{
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				}
				else
				{
					OrganizacaoView.this.viewer.getTable().setSortColumn(
							currentColumn);
					dir = SWT.UP;
				}
				// sort the data based on column and direction
				String sortIdentifier = null;
/*				if (currentColumn == tc0)
				{
					sortIdentifier = OrganizacaoSorter.LOGIN;
				}
				if (currentColumn == tc1)
				{
					sortIdentifier = OrganizacaoSorter.NOME;
				}
				if (currentColumn == tc2)
				{
					sortIdentifier = OrganizacaoSorter.TITULO;
				}
				if (currentColumn == tc3)
				{
					sortIdentifier = OrganizacaoSorter.FUNCAO;
				}*/
				OrganizacaoView.this.viewer.getTable().setSortDirection(dir);
				OrganizacaoView.this.viewer.setSorter(new OrganizacaoSorter(
						sortIdentifier, dir));
			}
		};

		tc0.addListener(SWT.Selection, sortListener);
		tc1.addListener(SWT.Selection, sortListener);
		tc2.addListener(SWT.Selection, sortListener);
		tc3.addListener(SWT.Selection, sortListener);

		try
		{
			this.viewer = new TableViewer(resultTable);
			this.viewer.setContentProvider(new ViewContentProvider());
			this.viewer.setLabelProvider(new ViewLabelProvider());
			//this.viewer.setInput(PeritoDelegate.getInstance().pesquisar());
			gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd.horizontalSpan = 4;
			this.viewer.getTable().setLayoutData(gd);

			OrganizacaoDelegate.getInstance().pesquisar().addPropertyChangeListener(
					this);
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
		}
		createToolBarButtons();
		
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(final PropertyChangeEvent evt)
	{
		getViewSite().getShell().getDisplay().asyncExec(new Runnable()
		{
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run()
			{
				if (evt.getPropertyName().equals(PropertyChangeEvents.ADD))
				{
					OrganizacaoView.this.viewer.refresh();
				}
				if (evt.getPropertyName().equals(PropertyChangeEvents.CLEAR))
				{
					OrganizacaoView.this.viewer.refresh();
				}

			}
		});

	}

	public void dispose()
	{
		try
		{
			TipoOrganizacaoDelegate.getInstance().pesquisar().removePropertyChangeListener(
					this);
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
		}
		super.dispose();
	}
	
	private void createToolBarButtons()
	{
		// Adiciona acao de remover
		removerAction = new OrganizacaoRemoverAction(this, "Remover");
		
		ImageDescriptor imgRemover = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/building_delete.png"),null));
		
		ImageDescriptor imgRemoverDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/building_delete_disabled.png"),null));
		
		removerAction.setImageDescriptor(imgRemover);
		removerAction.setDisabledImageDescriptor(imgRemoverDisabled);
		removerAction.setToolTipText("Remove a organizacao selecionada");
		
		// Adiciona acao de editar
		editarAction = new OrganizacaoEditarAction(this, "Atualizar");
		
		ImageDescriptor imgEditar = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/building_edit.png"),null));
		
		ImageDescriptor imgEditarDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/building_edit_disabled.png"),null));
		
		editarAction.setImageDescriptor(imgEditar);
		editarAction.setDisabledImageDescriptor(imgEditarDisabled);
		editarAction.setToolTipText("Atualiza a organizacao selecionada");
		
		// Adiciona botoes na view
		getViewSite().getActionBars().getToolBarManager().add(removerAction);
		getViewSite().getActionBars().getToolBarManager().add(editarAction);
		removerAction.setEnabled(false);
		editarAction.setEnabled(false);
		viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				removerAction.setEnabled(!event.getSelection().isEmpty());
				editarAction.setEnabled(!event.getSelection().isEmpty());
				setOrganizacao((Organizacao)((StructuredSelection)event.getSelection()).getFirstElement());
			}
		});
	}

	public Organizacao getOrganizacao()
	{
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao)
	{
		this.organizacao = organizacao;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}
}