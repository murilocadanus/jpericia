package org.jpericia.pericia.views.listeners;

import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class PericiaListener extends AbstractResultList
{
	
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Pericia[] toArray()
	{
		return (Pericia[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Pericia[] toArray(Pericia[] a)
	{
		return (Pericia[]) this.results.toArray(a);
	}
}
