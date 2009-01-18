package org.jpericia.perito.views.listeners;

import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class TituloPeritoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public TituloPerito[] toArray()
	{
		return (TituloPerito[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public TituloPerito[] toArray(TituloPerito[] a)
	{
		return (TituloPerito[]) this.results.toArray(a);
	}
}
