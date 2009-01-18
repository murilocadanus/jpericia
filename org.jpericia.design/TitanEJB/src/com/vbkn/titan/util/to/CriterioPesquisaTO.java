package com.vbkn.titan.util.to;

import java.io.Serializable;

/**
 * @author Valter Bruno Konrad Neto
 *  
 */
public class CriterioPesquisaTO implements Serializable
{
	
	private static final long serialVersionUID = 6321073804869529958L;

	/**
	 * Ordenar lista por CODIGO
	 */
	public static final int ORDENAR_POR_CODIGO = 1;
	
	/**
	 * Ordenar lista por NOME
	 */
	public static final int ORDENAR_POR_NOME = 2;

    /**
     * Constante que indica que o resultado deve vir em ordem crecente
     */
    public static final int ORDEM_CRESCENTE = 1;

    /**
     * Constante que indica que o resultado deve vir em ordem decrescente
     */
    public static final int ORDEM_DECRESCENTE = 2;

    /**
     * Qual página deverá ser retornada
     */
    private int pagina;

    /**
     * Indica se a ordem será ascendende ou descendente
     */
    private int ordem = 1;

    /**
     * Informa por qual coluna o resultado deverá ser ordenado
     */
    private int ordenarPor;

    /**
     * Informa quantos registros devem ser retornados
     */
    private int qtdeRegistrosPorPagina;

    /**
     * @return Returns the ordem.
     */
    public int getOrdem()
    {
        return ordem;
    }

    /**
     * @param ordem
     *            The ordem to set.
     */
    public void setOrdem(int ordem)
    {
        this.ordem = ordem;
    }

    /**
     * @return Returns the ordenarPor.
     */
    public int getOrdenarPor()
    {
        return ordenarPor;
    }

    /**
     * @param ordenarPor
     *            The ordenarPor to set.
     */
    public void setOrdenarPor(int ordenarPor)
    {
        this.ordenarPor = ordenarPor;
    }

    /**
     * @return Returns the página.
     */
    public int getPagina()
    {
        return pagina;
    }

    /**
     * @param página
     *            The página to set.
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
}

