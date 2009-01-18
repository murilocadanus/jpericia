package org.jpericia.perito.views.listeners;

import org.jpericia.common.entity.perito.Perito;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class PeritoListener extends AbstractResultList
{
	
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Perito[] toArray()
	{
		return (Perito[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Perito[] toArray(Perito[] a)
	{
		return (Perito[]) this.results.toArray(a);
	}
}
