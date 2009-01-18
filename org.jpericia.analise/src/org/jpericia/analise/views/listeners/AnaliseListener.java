package org.jpericia.analise.views.listeners;

import org.jpericia.common.entity.analise.Analise;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class AnaliseListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Analise[] toArray()
	{
		return (Analise[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Analise[] toArray(Analise[] a)
	{
		return (Analise[]) this.results.toArray(a);
	}
}
