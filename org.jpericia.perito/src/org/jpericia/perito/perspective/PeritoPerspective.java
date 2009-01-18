package org.jpericia.perito.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.jpericia.perito.views.FuncaoPeritoView;
import org.jpericia.perito.views.PeritoView;
import org.jpericia.perito.views.TituloPeritoView;

public class PeritoPerspective implements IPerspectiveFactory
{

	public static final String ID = "org.jpericia.perito.perspective.peritoPerspective";

	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		IFolderLayout left = layout.createFolder(
				"left", IPageLayout.LEFT, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		IFolderLayout right = layout.createFolder(
				"right", IPageLayout.TOP, 0.7f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		IFolderLayout botton = layout.createFolder("botton",
				IPageLayout.BOTTOM, 0.5f, IPageLayout.ID_EDITOR_AREA);

		left.addView(FuncaoPeritoView.VIEW_ID);
		right.addView(PeritoView.ID);
		botton.addView(TituloPeritoView.VIEW_ID);
	}

}
