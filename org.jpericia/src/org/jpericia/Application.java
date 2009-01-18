package org.jpericia;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication
{

	public Object start(IApplicationContext context) throws Exception
	{
		Display display = PlatformUI.createDisplay();
		
		if (authenticate())
		{
			try
			{
					int code = PlatformUI.createAndRunWorkbench(display,
							new ApplicationWorkbenchAdvisor());
					return code == PlatformUI.RETURN_RESTART ? EXIT_RESTART
							: EXIT_OK;
			}
			finally
			{
				if (display != null)
					display.dispose();
			}
		}
		else
			return EXIT_OK;
	}

	public void stop()
	{
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable()
		{
			public void run()
			{
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}

	private boolean authenticate()
	{
		ApplicationLoginDialog loginDialog = new ApplicationLoginDialog(Display
				.getDefault().getActiveShell());
		if(loginDialog.open() == InputDialog.OK)
		{
			return true;
		}
		else
			return false;
	}
}
