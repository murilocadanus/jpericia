package org.jpericia.pericia.views.listeners;

import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class AnexoListener extends AbstractResultList
{
	
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Anexo[] toArray()
	{
		return (Anexo[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Anexo[] toArray(Anexo[] a)
	{
		return (Anexo[]) this.results.toArray(a);
	}
}
