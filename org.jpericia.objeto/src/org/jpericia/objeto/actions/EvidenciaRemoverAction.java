package org.jpericia.objeto.actions;

import org.eclipse.jface.action.Action;
import org.jpericia.core.exception.BusinessDelegateException;
import org.jpericia.objeto.businessdelegate.EvidenciaDelegate;
import org.jpericia.objeto.views.EvidenciaView;

public class EvidenciaRemoverAction extends Action
{
	private EvidenciaView evidenciaView;
	
	public EvidenciaRemoverAction(EvidenciaView evidenciaView, String text)
	{
		super(text);
		this.evidenciaView = evidenciaView;
	}
	
	public void run()
	{
		try
		{
			EvidenciaDelegate.getInstance().remover(evidenciaView.getEvidencia());
			evidenciaView.getViewer().setInput(EvidenciaDelegate.getInstance().pesquisar());
			evidenciaView.getViewer().refresh();
			
		}
		catch (BusinessDelegateException e)
		{
			// TODO tratar erro
			e.printStackTrace();
		}
	}
}
