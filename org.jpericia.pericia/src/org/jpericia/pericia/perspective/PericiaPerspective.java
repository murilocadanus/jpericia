package org.jpericia.pericia.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.jpericia.pericia.views.AnexoView;
import org.jpericia.pericia.views.PericiaView;

public class PericiaPerspective implements IPerspectiveFactory
{
	public static final String ID = "org.jpericia.pericia.perspective.periciaPerspective";
	
	
	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		IFolderLayout top = layout.createFolder(
				"top", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		IFolderLayout bottom = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, 0.7f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		top.addView(PericiaView.ID);
		bottom.addView(AnexoView.ID);
	}
}
