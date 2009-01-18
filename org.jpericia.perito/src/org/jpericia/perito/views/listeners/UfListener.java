package org.jpericia.perito.views.listeners;

import org.jpericia.common.entity.generic.Uf;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class UfListener extends AbstractResultList
{
	
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Uf[] toArray()
	{
		return (Uf[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Uf[] toArray(Uf[] a)
	{
		return (Uf[]) this.results.toArray(a);
	}
}
