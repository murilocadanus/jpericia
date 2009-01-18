package org.jpericia.organizacao.views.listeners;

import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class OrganizacaoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Organizacao[] toArray()
	{
		return (Organizacao[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public Organizacao[] toArray(Organizacao[] a)
	{
		return (Organizacao[]) this.results.toArray(a);
	}
}
