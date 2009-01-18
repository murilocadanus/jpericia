package org.jpericia.common.entity.objeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;


@Entity
@Table(name="evidencia")
@SequenceGenerator(name = "evidencia_codigo_sequence", sequenceName = "evidencia_seq")
public class Evidencia extends AbstractEntity {

	private static final long serialVersionUID = -426381767405280311L;

	private Long codigo;
	
	private Objeto objeto;
	
	private String titulo;
	
	private byte[] imagem;
	
	private String descricao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evidencia_codigo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne()
	@JoinColumn(name="codigoObjeto", referencedColumnName="codigo", nullable=false)
	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Evidencia other = (Evidencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
