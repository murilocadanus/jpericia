package org.jpericia.objeto.views.listeners;

import org.jpericia.common.entity.objeto.TipoObjeto;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class TipoObjetoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public TipoObjeto[] toArray()
	{
		return (TipoObjeto[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public TipoObjeto[] toArray(TipoObjeto[] a)
	{
		return (TipoObjeto[]) this.results.toArray(a);
	}
}
