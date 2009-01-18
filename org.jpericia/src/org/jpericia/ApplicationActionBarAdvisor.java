package org.jpericia;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.mw3d.swt.ui.dialog.DialogManager;
import com.mw3d.swt.ui.dialog.GlobalLightDialog;
import com.mw3d.swt.ui.editors.MonkeyEditor;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{

	private IWorkbenchAction cutAction;

	private IWorkbenchAction copyAction;

	private IWorkbenchAction pasteAction;

	private IWorkbenchAction deleteAction;

	private IWorkbenchAction importAction;

	private IWorkbenchAction exportAction;

	private IWorkbenchAction propertiesAction;

	private IWorkbenchAction quitAction;

	private IWorkbenchAction newWindowAction;

	private IWorkbenchAction helpContentsAction;

	private IWorkbenchAction aboutAction;

	private IWorkbenchAction editActionSetsAction;

	private IWorkbenchAction resetPerspectiveAction;

	private IWorkbenchAction savePerspectiveAction;

	private IContributionItem perspectiveList;

	private IContributionItem viewList;

	// Editor
	private IWorkbenchAction newAction;

	private IWorkbenchAction saveAction;

	private IWorkbenchAction saveAsAction;

	private IWorkbenchAction undoAction;

	private IWorkbenchAction redoAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer)
	{
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window)
	{
		// Registra acoes editor
		newAction = ActionFactory.NEW.create(window);
		newAction.setText("Novo...");
		register(newAction);
		
		saveAction = ActionFactory.SAVE.create(window);
		saveAction.setText("Salvar");
		register(saveAction);
		
		saveAsAction = ActionFactory.SAVE_AS.create(window);
		saveAsAction.setText("Salvar como...");
		register(saveAsAction);
		
		undoAction = ActionFactory.UNDO.create(window);
		undoAction.setText("Desfazer");
		register(undoAction);
		
		redoAction = ActionFactory.REDO.create(window);
		redoAction.setText("Refazer");
		register(redoAction);
		
		copyAction = ActionFactory.COPY.create(window);
		copyAction.setText("Copiar");
		register(copyAction);
		
		pasteAction = ActionFactory.PASTE.create(window);
		pasteAction.setText("Colar");
		register(pasteAction);

		// Registra acoes aplicacao
		importAction = ActionFactory.IMPORT.create(window);
		importAction.setText("Importar...");
		
		exportAction = ActionFactory.EXPORT.create(window);
		exportAction.setText("Exportar...");
		
		propertiesAction = ActionFactory.PROPERTIES.create(window);
		propertiesAction.setText("Propriedades");
		
		quitAction = ActionFactory.QUIT.create(window);
		quitAction.setText("Sair");

		cutAction = ActionFactory.CUT.create(window);
		cutAction.setText("Recortar");
		
		copyAction = ActionFactory.COPY.create(window);
		copyAction.setText("Copiar");
		
		pasteAction = ActionFactory.PASTE.create(window);
		pasteAction.setText("Colar");
		
		deleteAction = ActionFactory.DELETE.create(window);
		deleteAction.setText("Apagar");

		newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
		newWindowAction.setText("Abrir em nova janela");
		
		editActionSetsAction = ActionFactory.EDIT_ACTION_SETS.create(window);
		helpContentsAction = ActionFactory.HELP_CONTENTS.create(window);
		aboutAction = ActionFactory.ABOUT.create(window);

		perspectiveList = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
		viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
	}

	protected void fillMenuBar(IMenuManager menuBar)
	{

		MenuManager menu = new MenuManager("Arquivo",
				IWorkbenchActionConstants.M_FILE);
		menu.add(new Separator(IWorkbenchActionConstants.FILE_START));
		MenuManager wizardMenu = new MenuManager("Novo Cadastro", "newWizard");
		wizardMenu.add(new Separator("top"));
		wizardMenu.add(new Separator("plugins"));
		wizardMenu.add(new Separator("bottom"));

		menu.add(newAction); // Editor
		menu.add(wizardMenu);
		menu.add(new Separator());
		menu.add(saveAction); // Editor
		menu.add(saveAsAction); // Editor
		menu.add(new Separator());
		menu.add(importAction);
		menu.add(exportAction);
		//menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		//menu.add(new Separator(IWorkbenchActionConstants.FILE_END));
		menu.add(propertiesAction);
		menu.add(new Separator());
		menu.add(quitAction);
		menuBar.add(menu);

		menu = new MenuManager("Editar", IWorkbenchActionConstants.M_EDIT);
		menu.add(undoAction);
		menu.add(redoAction);
		menu.add(new Separator());
		menu.add(cutAction);
		menu.add(copyAction);
		menu.add(pasteAction);
		menu.add(new Separator());
		menu.add(deleteAction);
		menu.add(new Separator());
		menu.add(new ChangeBackgroundAction());
		menu.add(new ChangeGlobalLightAction());
		menuBar.add(menu);

		menu = new MenuManager("&Janela", IWorkbenchActionConstants.M_WINDOW); //$NON-NLS-1$
		menu.add(new Separator("top")); //$NON-NLS-1$
		menu.add(newWindowAction);
		//menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		menu.add(new Separator());
		MenuManager perspectiveMenu = new MenuManager("Abrir Perspectiva", "openPerspective"); //$NON-NLS-1$ //$NON-NLS-2$
		perspectiveMenu.add(perspectiveList);
		menu.add(perspectiveMenu);
		MenuManager viewMenu = new MenuManager("Exibir Visualizações", "showView");
		viewMenu.add(viewList);
		menu.add(viewMenu);

/*		menu.add(new Separator());
		menu.add(editActionSetsAction);
		menu.add(savePerspectiveAction);
		menu.add(resetPerspectiveAction);
		menu.add(new Separator("bottom")); //$NON-NLS-1$
*/		menuBar.add(menu);

		menu = new MenuManager("&Ajuda", IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$
		// Welcome or intro page would go here
		//menu.add(helpContentsAction);
		// Tips and tricks page would go here
		//menu.add(new Separator(IWorkbenchActionConstants.HELP_START));
		//menu.add(new Separator("group.main.ext")); //$NON-NLS-1$
		//menu.add(new Separator("group.tutorials")); //$NON-NLS-1$
		//menu.add(new Separator("group.tools")); //$NON-NLS-1$
		//menu.add(new Separator("group.updates")); //$NON-NLS-1$
		//menu.add(new Separator(IWorkbenchActionConstants.HELP_END));
		//menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		// About should always be at the bottom
		// To use the real RCP About dialog uncomment these lines
		//menu.add(new Separator("group.about")); //$NON-NLS-1$ 
		menu.add(aboutAction);
		//menu.add(new Separator("group.about.ext")); //$NON-NLS-1$ 
		menuBar.add(menu);

		getActionBarConfigurer().registerGlobalAction(cutAction);
		getActionBarConfigurer().registerGlobalAction(copyAction);
		getActionBarConfigurer().registerGlobalAction(pasteAction);
		// getActionBarConfigurer().registerGlobalAction(pasteSpecialAction);
		getActionBarConfigurer().registerGlobalAction(deleteAction);

	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		ToolBarManager toolBar = new ToolBarManager(SWT.FLAT | SWT.WRAP);
		toolBar.add(newAction);
		toolBar.add(saveAction);
		toolBar.add(new Separator());
		toolBar.add(copyAction);
		toolBar.add(pasteAction);
		toolBar.add(new Separator());
		toolBar.add(undoAction);
		toolBar.add(redoAction);
		coolBar.add(toolBar);

//		CoolbarManager coolbarManager = new CoolbarManager();
//		coolBar.add(coolbarManager.createTransManager());
	}
	
	class ChangeBackgroundAction extends Action { 
		public ChangeBackgroundAction() { 
			super("Alterar cor de fundo");
			setToolTipText("Alterar cor de fundo");
		}
		
		public void run() {
	        RGB rgb = DialogManager.getInstance().getColorDialog().open();
			IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editorPart instanceof MonkeyEditor) {
				((MonkeyEditor)editorPart).setBackground(rgb);
			}
		}
	}
	
	class ChangeGlobalLightAction extends Action { 
		public ChangeGlobalLightAction() { 
			super("Alterar luz global");
			setToolTipText("Alterar luz global");
		}
		
		public void run() {
			IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editorPart instanceof MonkeyEditor) {
				GlobalLightDialog lightDialog = DialogManager.getInstance().getGlobalLightDialog();
				lightDialog.setLightState(((MonkeyEditor)editorPart).getLightState());
				lightDialog.setDirectionalLight(((MonkeyEditor)editorPart).getDirectionalLight());
				lightDialog.open();
			}
		}
	}		


}
