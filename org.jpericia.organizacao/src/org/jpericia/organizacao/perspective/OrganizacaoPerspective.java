package org.jpericia.organizacao.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.jpericia.organizacao.views.ContatoOrganizacaoView;
import org.jpericia.organizacao.views.OrganizacaoView;
import org.jpericia.organizacao.views.TipoOrganizacaoView;
import org.jpericia.organizacao.views.TituloContatoView;

public class OrganizacaoPerspective implements IPerspectiveFactory
{

	public static final String ID = "org.jpericia.organizacao.perspective.organizacaoPerspective";

	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		IFolderLayout top = layout.createFolder(
				"top", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$
		
		IFolderLayout left = layout.createFolder(
				"left", IPageLayout.LEFT, 0.3f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$	
		
		IFolderLayout right = layout.createFolder(
				"right", IPageLayout.TOP, 0.7f, IPageLayout.ID_EDITOR_AREA); //$NON-NLS-1$

		top.addView(ContatoOrganizacaoView.VIEW_ID);
		left.addView(TituloContatoView.VIEW_ID);
		left.addView(TipoOrganizacaoView.VIEW_ID);
		right.addView(OrganizacaoView.VIEW_ID);
	}
}
