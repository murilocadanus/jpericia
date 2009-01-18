package org.jpericia.organizacao.views.listeners;

import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.core.ui.listeners.AbstractResultList;

public class ContatoOrganizacaoListener extends AbstractResultList
{

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public ContatoOrganizacao[] toArray()
	{
		return (ContatoOrganizacao[]) this.results.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	public ContatoOrganizacao[] toArray(ContatoOrganizacao[] a)
	{
		return (ContatoOrganizacao[]) this.results.toArray(a);
	}
}
