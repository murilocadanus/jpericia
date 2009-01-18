package org.jpericia.pericia.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

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
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.core.ui.preferences.PropertyChangeEvents;
import org.jpericia.organizacao.businessdelegate.OrganizacaoDelegate;
import org.jpericia.pericia.PericiaPlugin;
import org.jpericia.pericia.actions.LaudoEditarAction;
import org.jpericia.pericia.actions.LaudoVisualizarAction;
import org.jpericia.pericia.actions.LaudoWizardAction;
import org.jpericia.pericia.actions.PericiaEditarAction;
import org.jpericia.pericia.actions.PericiaRemoverAction;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.messages.Messages;
import org.jpericia.pericia.views.listeners.PericiaListener;
import org.jpericia.pericia.views.sorters.PericiaSorter;

public class PericiaView extends ViewPart implements PropertyChangeListener
{
	public static final String ID = "org.jpericia.pericia.views.periciaView"; //$NON-NLS-1$
	
	private Pericia pericia;
	
	private PericiaRemoverAction removerAction;
	
	private PericiaEditarAction editarAction;
	
	private LaudoWizardAction gerarLaudoAction;
	
	private LaudoEditarAction atualizarLaudoAction;
	
	private LaudoVisualizarAction visualizarLaudoAction;

	private TableViewer viewer;

	private Text contatoOrganizacaoText;

	private Text tituloText;

	private Text dataInicioText;

	private Text dataFimText;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	ViewerFilter filter = new ViewerFilter()
	{
		public boolean select(Viewer viewer1, Object parentElement,
				Object element)
		{
			return (PericiaView.this.tituloText.getText().length() == 0 || ((Pericia) element)
					.getTitulo()
					.matches(
							replaceWildCard(PericiaView.this.tituloText.getText())));
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
			return ((PericiaListener) parent).toArray(new Pericia[0]);
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
				returnValue = ((Pericia) obj).getContatoOrganizacao().getOrganizacao().getNome();
				break;			
			case 1:
				returnValue = ((Pericia) obj).getContatoOrganizacao().getNome();
				break;
			case 2:
				returnValue = ((Pericia) obj).getPeritoResponsavel().getNome();
				break;
			case 3:
				returnValue = ((Pericia) obj).getTitulo();
				break;
			case 4:
				returnValue = dateFormat.format(((Pericia) obj).getDataInicio());
				break;
			case 5:
				returnValue = dateFormat.format(((Pericia) obj).getDataFim());
				break;
			case 6:
				returnValue = (((Pericia) obj).isFinalizada()) ? Messages.sim : Messages.nao;
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
		infoLabel.setText(Messages.infoPericia);
		
		Label userLabel = new Label(parent, SWT.NONE);
		userLabel.setText(Messages.contatoOrganizacao);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 100;

		userLabel.setLayoutData(gd);
		this.contatoOrganizacaoText = new Text(parent, SWT.BORDER);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 200;
		this.contatoOrganizacaoText.setLayoutData(gd);

		Label ipLabel = new Label(parent, SWT.NONE);
		ipLabel.setText(Messages.titulo);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.widthHint = 100;

		ipLabel.setLayoutData(gd);
		this.tituloText = new Text(parent, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		this.tituloText.setLayoutData(gd);
		
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
						PericiaView.this.viewer.setInput(PericiaDelegate.getInstance().pesquisar());
					//}
				}
				catch(BusinessDelegateException e)
				{
					//Trata erro
				}
					
				
				PericiaView.this.viewer.addFilter(PericiaView.this.filter);
				
			}
		});

		Button clearButton = new Button(buttonComposite, SWT.PUSH);
		gd = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		clearButton.setText(Messages.todos);
		clearButton.setLayoutData(gd);
		clearButton.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event event)
			{
				PericiaView.this.viewer.removeFilter(PericiaView.this.filter);
			}
		});

		final Table resultTable = new Table(parent, SWT.FULL_SELECTION
				| SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		resultTable.setHeaderVisible(true);
		resultTable.setLinesVisible(true);
		
		final TableColumn tc0 = new TableColumn(resultTable, SWT.NONE);
		tc0.setText(Messages.organizacao);
		tc0.setWidth(150);
		tc0.setMoveable(true);		

		final TableColumn tc1 = new TableColumn(resultTable, SWT.NONE);
		tc1.setText(Messages.contatoOrganizacao);
		tc1.setWidth(200);
		tc1.setMoveable(true);

		final TableColumn tc2 = new TableColumn(resultTable, SWT.NONE);
		tc2.setText(Messages.peritoResponsavel);
		tc2.setWidth(200);
		tc2.setMoveable(true);		
		
		final TableColumn tc3 = new TableColumn(resultTable, SWT.NONE);
		tc3.setText(Messages.titulo);
		tc3.setData(new ColumnWeightData(25));
		tc3.setMoveable(true);
		tc3.setWidth(250);
		
		final TableColumn tc4 = new TableColumn(resultTable, SWT.NONE);
		tc4.setText(Messages.dataInicio);
		tc4.setData(new ColumnWeightData(25));
		tc4.setMoveable(true);
		tc4.setWidth(150);

		final TableColumn tc5 = new TableColumn(resultTable, SWT.NONE);
		tc5.setText(Messages.dataFim);
		tc5.setData(new ColumnWeightData(25));
		tc5.setMoveable(true);
		tc5.setWidth(150);
		
		final TableColumn tc6 = new TableColumn(resultTable, SWT.NONE);
		tc6.setText(Messages.finalizada);
		tc6.setData(new ColumnWeightData(25));
		tc6.setMoveable(true);
		tc6.setWidth(150);
		
		Listener sortListener = new Listener()
		{
			public void handleEvent(Event e)
			{
				// determine new sort column and direction
				TableColumn sortColumn = PericiaView.this.viewer.getTable()
						.getSortColumn();
				TableColumn currentColumn = (TableColumn) e.widget;
				int dir = PericiaView.this.viewer.getTable().getSortDirection();
				if (sortColumn == currentColumn)
				{
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				}
				else
				{
					PericiaView.this.viewer.getTable().setSortColumn(
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
					sortIdentifier = PeritoSorter.NOME;
				}
				if (currentColumn == tc2)
				{
					sortIdentifier = PeritoSorter.TITULO;
				}
				if (currentColumn == tc3)
				{
					sortIdentifier = PeritoSorter.FUNCAO;
				}*/
				PericiaView.this.viewer.getTable().setSortDirection(dir);
				PericiaView.this.viewer.setSorter(new PericiaSorter(
						sortIdentifier, dir));
			}
		};

		tc0.addListener(SWT.Selection, sortListener);
		tc1.addListener(SWT.Selection, sortListener);
		tc2.addListener(SWT.Selection, sortListener);
		tc3.addListener(SWT.Selection, sortListener);
		tc4.addListener(SWT.Selection, sortListener);
		tc5.addListener(SWT.Selection, sortListener);
		tc6.addListener(SWT.Selection, sortListener);

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
					PericiaView.this.viewer.refresh();
				}
				if (evt.getPropertyName().equals(PropertyChangeEvents.CLEAR))
				{
					PericiaView.this.viewer.refresh();
				}

			}
		});

	}

	public void dispose()
	{
		try
		{
			PericiaDelegate.getInstance().pesquisar().removePropertyChangeListener(
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
		removerAction = new PericiaRemoverAction(this, "Remover");
		
		ImageDescriptor imgRemover = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/zoom_delete.png"),null));
		
		ImageDescriptor imgRemoverDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/zoom_delete_disabled.png"),null));
		
		removerAction.setImageDescriptor(imgRemover);
		removerAction.setDisabledImageDescriptor(imgRemoverDisabled);
		removerAction.setToolTipText(Messages.toolTipRemovePericia);
		
		// Adiciona acao de atualizar
		editarAction = new PericiaEditarAction(this, "Atualizar");
		
		ImageDescriptor imgEditar = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/zoom_edit.png"),null));
		
		ImageDescriptor imgEditarDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/zoom_edit_disabled.png"),null));
		
		editarAction.setImageDescriptor(imgEditar);
		editarAction.setDisabledImageDescriptor(imgEditarDisabled);
		editarAction.setToolTipText(Messages.toolTipAtualizaPericia);
		
		//Adiciona acao de gerar laudo
		gerarLaudoAction = new LaudoWizardAction(this, "Gerar Laudo");
		
		ImageDescriptor imgGerarLaudo = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page_lightning.png"),null));
		
		ImageDescriptor imgGerarLaudoDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page_lightning_disabled.png"),null));
		
		gerarLaudoAction.setImageDescriptor(imgGerarLaudo);
		gerarLaudoAction.setDisabledImageDescriptor(imgGerarLaudoDisabled);
		gerarLaudoAction.setToolTipText(Messages.toolTipGerarLaudo);		
		
		//Adiciona acao de atualizar laudo
		atualizarLaudoAction = new LaudoEditarAction(this, "Atualizar Laudo");
		
		ImageDescriptor imgAtualizarLaudo = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page_edit.png"),null));
		
		ImageDescriptor imgAtualizarLaudoDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page_edit_disabled.png"),null));
		
		atualizarLaudoAction.setImageDescriptor(imgAtualizarLaudo);
		atualizarLaudoAction.setDisabledImageDescriptor(imgAtualizarLaudoDisabled);
		atualizarLaudoAction.setToolTipText(Messages.toolTipAtualizarLaudo);		
		
		//Adiciona acao de visualizar laudo
		visualizarLaudoAction = new LaudoVisualizarAction(this, "Atualizar Laudo");
		
		ImageDescriptor imgVisualizarLaudo = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page.png"),null));
		
		ImageDescriptor imgVisualizarLaudoDisabled = ImageDescriptor.createFromURL(
                FileLocator.find(PericiaPlugin.getDefault().getBundle(), 
            new Path("icons/page_disabled.png"),null));
		
		visualizarLaudoAction.setImageDescriptor(imgVisualizarLaudo);
		visualizarLaudoAction.setDisabledImageDescriptor(imgVisualizarLaudoDisabled);
		visualizarLaudoAction.setToolTipText(Messages.toolTipVisulizarLaudo);
		
		
		// Adiciona botoes na view
		getViewSite().getActionBars().getToolBarManager().add(gerarLaudoAction);
		getViewSite().getActionBars().getToolBarManager().add(visualizarLaudoAction);
		getViewSite().getActionBars().getToolBarManager().add(atualizarLaudoAction);
		getViewSite().getActionBars().getToolBarManager().add(removerAction);
		getViewSite().getActionBars().getToolBarManager().add(editarAction);
		gerarLaudoAction.setEnabled(false);
		removerAction.setEnabled(false);
		editarAction.setEnabled(false);
		visualizarLaudoAction.setEnabled(false);
		atualizarLaudoAction.setEnabled(false);
		viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				removerAction.setEnabled(!event.getSelection().isEmpty());
				editarAction.setEnabled(!event.getSelection().isEmpty());
				gerarLaudoAction.setEnabled(!event.getSelection().isEmpty());
				visualizarLaudoAction.setEnabled(!event.getSelection().isEmpty());
				atualizarLaudoAction.setEnabled(!event.getSelection().isEmpty());
				setPericia((Pericia)((StructuredSelection)event.getSelection()).getFirstElement());
			}
		});
	}

	public Pericia getPericia()
	{
		return pericia;
	}

	public void setPericia(Pericia pericia)
	{
		this.pericia = pericia;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}
}