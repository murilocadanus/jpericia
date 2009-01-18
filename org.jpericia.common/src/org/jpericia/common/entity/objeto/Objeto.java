package org.jpericia.common.entity.objeto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;

/**
 * @author Marlus Cadanus da Costa
 */

@Entity
@Table(name = "objeto")
@SequenceGenerator(name = "objeto_codigo_sequence", sequenceName = "objeto_seq")
public class Objeto extends AbstractEntity
{

	private static final long serialVersionUID = -426381767405280311L;

	private Long codigo;

	private TipoObjeto tipoObjeto;

	private Set<Analise> analises;

	private String titulo;

	private byte[] imagem;

	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objeto_codigo_sequence")
	@Column(name = "codigo")
	public Long getCodigo()
	{
		return codigo;
	}

	public void setCodigo(Long codigo)
	{
		this.codigo = codigo;
	}

	@ManyToOne()
	@JoinColumn(name = "codigoTipoObjeto", referencedColumnName = "codigo", nullable = false, updatable=false)
	public TipoObjeto getTipoObjeto()
	{
		return tipoObjeto;
	}

	public void setTipoObjeto(TipoObjeto tipoObjeto)
	{
		this.tipoObjeto = tipoObjeto;
	}

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "analise_objeto",
                     joinColumns = {@JoinColumn(name = "codigoAnalise")},
                     inverseJoinColumns = {@JoinColumn(name = "codigoObjeto")})
	public Set<Analise> getAnalises()
	{
		return analises;
	}

	public void setAnalises(Set<Analise> analises)
	{
		this.analises = analises;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public byte[] getImagem()
	{
		return imagem;
	}

	public void setImagem(byte[] imagem)
	{
		this.imagem = imagem;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Objeto other = (Objeto) obj;
		if (codigo == null)
		{
			if (other.codigo != null)
				return false;
		}
		else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}