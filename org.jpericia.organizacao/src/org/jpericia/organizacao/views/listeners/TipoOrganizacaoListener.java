package org.jpericia.organizacao.views.listeners;

import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class TipoOrganizacaoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public TipoOrganizacao[] toArray()
	{
		return (TipoOrganizacao[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public TipoOrganizacao[] toArray(TipoOrganizacao[] a)
	{
		return (TipoOrganizacao[]) this.results.toArray(a);
	}
}
