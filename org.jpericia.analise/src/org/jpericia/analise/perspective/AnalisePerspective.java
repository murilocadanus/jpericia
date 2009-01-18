package org.jpericia.analise.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.jpericia.analise.views.AnaliseView;
import org.jpericia.analise.views.CenaView;
import org.jpericia.analise.views.TipoAnaliseView;

public class AnalisePerspective implements IPerspectiveFactory
{

	public static final String ID = "org.jpericia.analise.perspective.analisePerspective";

	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		IFolderLayout left = layout.createFolder(
				"left", IPageLayout.LEFT, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		IFolderLayout top = layout.createFolder(
				"top", IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$
		
		IFolderLayout bottom = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, 0.5f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		left.addView(TipoAnaliseView.VIEW_ID);
		top.addView(AnaliseView.VIEW_ID);
		bottom.addView(CenaView.VIEW_ID);
	}

}
