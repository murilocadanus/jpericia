package org.jpericia.objeto.views.listeners;

import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class ObjetoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Objeto[] toArray()
	{
		return (Objeto[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Objeto[] toArray(Objeto[] a)
	{
		return (Objeto[]) this.results.toArray(a);
	}
}
