package org.jpericia.objeto.views.listeners;

import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class EvidenciaListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Evidencia[] toArray()
	{
		return (Evidencia[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Evidencia[] toArray(Evidencia[] a)
	{
		return (Evidencia[]) this.results.toArray(a);
	}
}
