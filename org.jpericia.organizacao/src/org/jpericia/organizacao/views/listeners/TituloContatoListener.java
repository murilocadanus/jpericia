package org.jpericia.organizacao.views.listeners;

import org.jpericia.common.entity.organizacao.TituloContato;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class TituloContatoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public TituloContato[] toArray()
	{
		return (TituloContato[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public TituloContato[] toArray(TituloContato[] a)
	{
		return (TituloContato[]) this.results.toArray(a);
	}
}
