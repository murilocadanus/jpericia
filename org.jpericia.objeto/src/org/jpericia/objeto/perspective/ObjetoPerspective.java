package org.jpericia.objeto.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.jpericia.objeto.views.CategoriaTipoObjetoView;
import org.jpericia.objeto.views.EvidenciaView;
import org.jpericia.objeto.views.ObjetoView;
import org.jpericia.objeto.views.TipoObjetoView;

public class ObjetoPerspective implements IPerspectiveFactory
{

	public static final String ID = "org.jpericia.perito.perspective.peritoPerspective";

	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		IFolderLayout left = layout.createFolder(
				"left", IPageLayout.LEFT, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$
		
		IFolderLayout top = layout.createFolder(
				"top", IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		IFolderLayout botton = layout.createFolder(
				"right", IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		top.addView(EvidenciaView.VIEW_ID);
		left.addView(CategoriaTipoObjetoView.VIEW_ID);
		left.addView(TipoObjetoView.VIEW_ID);
		botton.addView(ObjetoView.VIEW_ID);
	}

}
