/*******************************************************************************
 * Copyright (c) 2005 Chris Aniszczyk
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Chris Aniszczyk - initial API and implementation
 * IBM Corporation
 *******************************************************************************/

package org.jpericia.editor.actions;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.jpericia.core.ui.actions.AbstractWizardAction;
import org.jpericia.editor.ShapesEditorInput;

public class NewAction extends AbstractWizardAction
{

	private String openFileDialog()
	{
		FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
		dialog.setText("??");
		dialog.setFilterExtensions(new String[] { ".shapes" });
		return dialog.open();
	}

	@Override
	public void run(IAction action)
	{
		String path = openFileDialog();
		if (path != null)
		{
			IEditorInput input = new ShapesEditorInput(new Path(path));

			try
			{
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input,
						ShapesEditor.ID, true);
			}
			catch (PartInitException e)
			{
				e.printStackTrace();
			}
		}

	}
}
