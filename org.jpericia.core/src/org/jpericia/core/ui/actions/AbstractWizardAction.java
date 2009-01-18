package org.jpericia.core.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public abstract class AbstractWizardAction implements IWorkbenchWindowActionDelegate
{
	
	protected IWorkbenchPart targetPart;
	protected IStructuredSelection selection;
	protected IWorkbenchPartSite site;
	protected IWorkbenchWindow window;
	
	public void init(IWorkbenchWindow window)
	{
		// TODO Auto-generated method stub
	}
	
	public void selectionChanged(IAction action, ISelection selection)
	{
		this.selection = selection instanceof IStructuredSelection
		? (IStructuredSelection) selection
		: null;		
	}

	public abstract void run(IAction action);

	public void dispose()
	{
		// TODO Auto-generated method stub
	}

}
