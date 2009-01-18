package org.jpericia.pericia.views.listeners;

import org.jpericia.common.entity.pericia.Laudo;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class LaudoListener extends AbstractResultList
{
	
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Laudo[] toArray()
	{
		return (Laudo[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Laudo[] toArray(Laudo[] a)
	{
		return (Laudo[]) this.results.toArray(a);
	}
}
