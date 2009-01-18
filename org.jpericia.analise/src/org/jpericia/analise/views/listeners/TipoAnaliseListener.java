package org.jpericia.analise.views.listeners;

import org.jpericia.common.entity.analise.TipoAnalise;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class TipoAnaliseListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public TipoAnalise[] toArray()
	{
		return (TipoAnalise[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public TipoAnalise[] toArray(TipoAnalise[] a)
	{
		return (TipoAnalise[]) this.results.toArray(a);
	}
}
