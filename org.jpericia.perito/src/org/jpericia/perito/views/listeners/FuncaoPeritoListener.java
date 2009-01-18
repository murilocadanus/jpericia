package org.jpericia.perito.views.listeners;

import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class FuncaoPeritoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public FuncaoPerito[] toArray()
	{
		return (FuncaoPerito[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public FuncaoPerito[] toArray(FuncaoPerito[] a)
	{
		return (FuncaoPerito[]) this.results.toArray(a);
	}
}
