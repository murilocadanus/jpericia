package org.jpericia;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisorHack {

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
    
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		configurer.setSaveAndRestore(true);
		super.initialize(configurer);
	}


	public String getInitialWindowPerspectiveId() {
		return "org.jpericia.perito.perspective.peritoPerspective";
	}
	
    public void eventLoopException(Throwable exception) {
        if (exception instanceof OutOfMemoryError) {
			try {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog.openError(shell, shell.getText(), "Erro inesperado. o programa será fechado.");

				if (!PlatformUI.getWorkbench().close())
					System.exit(0);
			} catch (Throwable t) {
				System.exit(0);
			}
		} else {
			super.eventLoopException(exception);
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openError(shell, shell.getText(), "Erro inesperado, por favor contate o administrador da rede.");
		}
    }

}
