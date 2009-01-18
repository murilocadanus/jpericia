package org.jpericia.core.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public abstract class AbstractWizard extends Wizard implements INewWizard
{
	protected IStructuredSelection initialSelection;

	@Override
	public final boolean performFinish()
	{
		try
		{
			getContainer().run(true, true, new IRunnableWithProgress()
			{
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					performOperation(monitor);
				}
			});
		}
		catch (InvocationTargetException e)
		{
			// TODO: handle exception
			return false;
		}
		catch (InterruptedException e)
		{
			// TODO: handle exception
			return false;
		}
		return true;
	}
	

	@Override
	public abstract void addPages();

	public final void init(IWorkbench workbench, IStructuredSelection selection)
	{
		this.initialSelection = selection;
	}
	
	protected abstract void performOperation(IProgressMonitor monitor);

}
