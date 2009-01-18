package org.jpericia.common.util.to;

import java.io.Serializable;
import java.util.List;

/**
 * Essa classe representa uma página de consulta retornada do banco de dados.
 * Será utilizada nas consultas que apresentam resultados paginados. 
 */

@SuppressWarnings("unchecked")
public class PaginaTO implements Serializable
{
	private static final long serialVersionUID = -8096162333509023713L;

	/**
	 * Construtor
	 * @param criterioPesquisa
	 */
    public PaginaTO(CriterioPesquisaTO criterioPesquisa)
    {
        this.setPagina(criterioPesquisa.getPagina());
        this.setQtdeRegistrosPorPagina(criterioPesquisa.getQtdeRegistrosPorPagina());
    }

    /**
     * Número da página retornada
     */
    private int pagina;

    /**
     * Quantidade de registros retornados por página
     */
    private int qtdeRegistrosPorPagina;

    /**
     * Total de registros existentes na base de dados
     */
    private long totalRegistros;

    /**
     * Registros retornados pela pesquisa
     */
    private List registros;

    /**
     * @return Returns the pagina.
     */
    public int getPagina()
    {
        return pagina;
    }

    /**
     * @param pagina
     *            The pagina to set.
     */
    public void setPagina(int pagina)
    {
        this.pagina = pagina;
    }

    /**
     * @return Returns the qtdeRegistrosPorPagina.
     */
    public int getQtdeRegistrosPorPagina()
    {
        return qtdeRegistrosPorPagina;
    }

    /**
     * @param qtdeRegistrosPorPagina
     *            The qtdeRegistrosPorPagina to set.
     */
    public void setQtdeRegistrosPorPagina(int qtdeRegistrosPorPagina)
    {
        this.qtdeRegistrosPorPagina = qtdeRegistrosPorPagina;
    }

    /**
     * @return Returns the registros.
     */
    public List getRegistros()
    {
        return registros;
    }

    /**
     * @param registros
     *            The registros to set.
     */
    public void setRegistros(List registros)
    {
        this.registros = registros;
    }

    /**
     * @return Returns the totalRegistros.
     */
    public long getTotalRegistros()
    {
        return totalRegistros;
    }

    /**
     * @param totalRegistros
     *            The totalRegistros to set.
     */
    public void setTotalRegistros(long totalRegistros)
    {
        this.totalRegistros = totalRegistros;
    }
}

