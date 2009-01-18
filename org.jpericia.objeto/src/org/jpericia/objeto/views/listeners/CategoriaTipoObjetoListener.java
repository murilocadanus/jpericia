package org.jpericia.objeto.views.listeners;

import org.jpericia.common.entity.objeto.CategoriaTipoObjeto;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class CategoriaTipoObjetoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public CategoriaTipoObjeto[] toArray()
	{
		return (CategoriaTipoObjeto[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public CategoriaTipoObjeto[] toArray(CategoriaTipoObjeto[] a)
	{
		return (CategoriaTipoObjeto[]) this.results.toArray(a);
	}
}
