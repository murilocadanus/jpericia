package org.jpericia.pericia.views.sorters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.jpericia.common.entity.perito.Perito;

public class PericiaSorter extends ViewerSorter
{

	public static final String LOGIN = "login"; //$NON-NLS-1$

	public static final String NOME = "nome"; //$NON-NLS-1$

	public static final String TITULO = "titulo"; //$NON-NLS-1$

	public static final String FUNCAO = "funcao"; //$NON-NLS-1$

	private String column = null;

	private int dir = SWT.DOWN;

	/**
	 * @param column
	 * @param dir
	 */
	public PericiaSorter(String column, int dir)
	{
		super();
		this.column = column;
		this.dir = dir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2)
	{
		int returnValue = 0;
		if (this.column == LOGIN)
		{
			returnValue = ((Perito) e1).getUsuario().compareTo(
					((Perito) e2).getUsuario());
		}
		if (this.column == NOME)
		{
			returnValue = ((Perito) e1).getNome().compareTo(
					((Perito) e2).getNome());
		}
		if (this.column == TITULO)
		{
			returnValue = ((Perito) e1).getTituloPerito().getTitulo().compareTo(
					((Perito) e2).getTituloPerito().getTitulo());
		}
		if (this.column == FUNCAO)
		{
			returnValue = ((Perito) e1).getFuncaoPerito().getFuncao().compareTo(
					((Perito) e2).getFuncaoPerito().getFuncao());
		}
		if (this.dir == SWT.DOWN)
		{
			returnValue = returnValue * -1;
		}
		return returnValue;
	}

}
