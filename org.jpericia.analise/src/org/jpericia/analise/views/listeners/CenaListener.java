package org.jpericia.analise.views.listeners;

import org.jpericia.common.entity.analise.Cena;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class CenaListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Cena[] toArray()
	{
		return (Cena[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Cena[] toArray(Cena[] a)
	{
		return (Cena[]) this.results.toArray(a);
	}
}
