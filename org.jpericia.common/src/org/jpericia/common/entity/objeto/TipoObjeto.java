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
@Table(name="tipo_objeto")
@SequenceGenerator(name = "tipo_objeto_codigo_sequence", sequenceName = "tipo_objeto_seq")
public class TipoObjeto extends AbstractEntity {

	private static final long serialVersionUID = 685178995172030491L;

	private Long codigo;
	
	private CategoriaTipoObjeto categoriaTipoObjeto;
	
	private String titulo;
	
	private String icone;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_codigo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne()
	@JoinColumn(name="codigoCategoriaTipoObjeto", referencedColumnName="codigo", nullable=false)
	public CategoriaTipoObjeto getCategoriaTipoObjeto() {
		return categoriaTipoObjeto;
	}

	public void setCategoriaTipoObjeto(CategoriaTipoObjeto categoriaTipoObjeto) {
		this.categoriaTipoObjeto = categoriaTipoObjeto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
	
	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
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
		
		final TipoObjeto other = (TipoObjeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
