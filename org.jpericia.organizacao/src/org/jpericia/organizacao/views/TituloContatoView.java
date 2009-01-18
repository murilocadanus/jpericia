package org.jpericia.organizacao.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.preferences.PropertyChangeEvents;
import org.jpericia.organizacao.OrganizacaoPlugin;
import org.jpericia.organizacao.actions.TituloContatoEditarAction;
import org.jpericia.organizacao.actions.TituloContatoRemoverAction;
import org.jpericia.organizacao.businessdelegate.TituloContatoDelegate;
import org.jpericia.organizacao.messages.Messages;
import org.jpericia.organizacao.views.listeners.TituloContatoListener;
import org.jpericia.organizacao.views.sorters.TituloContatoSorter;

public class TituloContatoView extends ViewPart implements PropertyChangeListener
{
	public static final String VIEW_ID = "org.jpericia.organizacao.views.tituloContatoView";
	
	private TituloContato tituloContato;
	
	private TituloContatoRemoverAction removerAction;
	
	private TituloContatoEditarAction editarAction;

	private TableViewer viewer;

	private Text tituloText;

	ViewerFilter filter = new ViewerFilter()
	{
		public boolean select(Viewer viewer1, Object parentElement,
				Object element)
		{
			return (TituloContatoView.this.tituloText.getText().length() == 0 || ((TituloContato) element)
					.getTitulo()
					.matches(
							replaceWildCard(TituloContatoView.this.tituloText.getText())));
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
			return ((TituloContatoListener) parent).toArray(new TituloContato[0]);
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
				returnValue = ((TituloContato) obj).getTitulo();
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
		parent.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		parent.setLayoutData(gd);

		GridData gdSpan = new GridData();
		gdSpan.horizontalSpan = 2;
		
		Label infoLabel = new Label(parent, SWT.NONE);
		infoLabel.setLayoutData(gdSpan);
		infoLabel.setText(Messages.infoOrganizacao);
		
		Label tituloLabel = new Label(parent, SWT.NONE);
		tituloLabel.setText(Messages.titulo);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 100;

		tituloLabel.setLayoutData(gd);
		this.tituloText = new Text(parent, SWT.BORDER);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 200;
		this.tituloText.setLayoutData(gd);

		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(2, false));
		gd = new GridData(SWT.RIGHT, SWT.BEGINNING, true, false);
		gd.horizontalSpan = 2;
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
					TituloContatoView.this.viewer.setInput(TituloContatoDelegate.getInstance().pesquisar());
				}
				catch(BusinessDelegateException e)
				{
					//Trata erro
				}
				
				TituloContatoView.this.viewer.addFilter(TituloContatoView.this.filter);
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
				TituloContatoView.this.tituloText.setText("");
			}
		});

		final Table resultTable = new Table(parent, SWT.FULL_SELECTION
				| SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		resultTable.setHeaderVisible(true);
		resultTable.setLinesVisible(true);
		
		final TableColumn tc0 = new TableColumn(resultTable, SWT.NONE);
		tc0.setText(Messages.titulo);
		tc0.setWidth(150);
		tc0.setMoveable(true);

		Listener sortListener = new Listener()
		{
			public void handleEvent(Event e)
			{
				// determine new sort column and direction
				TableColumn sortColumn = TituloContatoView.this.viewer.getTable()
						.getSortColumn();
				TableColumn currentColumn = (TableColumn) e.widget;
				int dir = TituloContatoView.this.viewer.getTable().getSortDirection();
				if (sortColumn == currentColumn)
				{
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				}
				else
				{
					TituloContatoView.this.viewer.getTable().setSortColumn(
							currentColumn);
					dir = SWT.UP;
				}
				
				// sort the data based on column and direction
				String sortIdentifier = null;
				if (currentColumn == tc0)
				{
					sortIdentifier = TituloContatoSorter.TITULO;
				}
				TituloContatoView.this.viewer.getTable().setSortDirection(dir);
				TituloContatoView.this.viewer.setSorter(new TituloContatoSorter(
						sortIdentifier, dir));
			}
		};

		tc0.addListener(SWT.Selection, sortListener);

		try
		{
			this.viewer = new TableViewer(resultTable);
			this.viewer.setContentProvider(new ViewContentProvider());
			this.viewer.setLabelProvider(new ViewLabelProvider());
			//this.viewer.setInput(TituloContatoDelegate.getInstance().pesquisar());
			gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd.horizontalSpan = 2;
			this.viewer.getTable().setLayoutData(gd);

			TituloContatoDelegate.getInstance().pesquisar().addPropertyChangeListener(
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
					TituloContatoView.this.viewer.refresh();
				}
				if (evt.getPropertyName().equals(PropertyChangeEvents.CLEAR))
				{
					TituloContatoView.this.viewer.refresh();
				}

			}
		});

	}

	public void dispose()
	{
		try
		{
			TituloContatoDelegate.getInstance().pesquisar().removePropertyChangeListener(
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
		removerAction = new TituloContatoRemoverAction(this, "Remover");
		
		ImageDescriptor imgRemover = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/award_star_delete.png"),null));
		
		ImageDescriptor imgRemoverDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/award_star_delete_disabled.png"),null));
		
		removerAction.setImageDescriptor(imgRemover);
		removerAction.setDisabledImageDescriptor(imgRemoverDisabled);
		removerAction.setToolTipText("Remove o titulo contato selecionado");
		
		// Adiciona acao de editar
		editarAction = new TituloContatoEditarAction(this, "Atualizar");
		
		ImageDescriptor imgEditar = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/award_star_edit.png"),null));
		
		ImageDescriptor imgEditarDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(OrganizacaoPlugin.getDefault().getBundle(), 
            new Path("icons/award_star_edit_disabled.png"),null));
		
		editarAction.setImageDescriptor(imgEditar);
		editarAction.setDisabledImageDescriptor(imgEditarDisabled);
		editarAction.setToolTipText("Atualiza o titulo contato selecionado");
		
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
				setTituloContato((TituloContato)((StructuredSelection)event.getSelection()).getFirstElement());
			}
		});
	}

	public TituloContato getTituloContato()
	{
		return tituloContato;
	}

	public void setTituloContato(TituloContato tituloContato)
	{
		this.tituloContato = tituloContato;
	}	
	
	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}
}