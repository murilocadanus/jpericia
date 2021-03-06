package org.jpericia.common.entity.objeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;


@Entity
@Table(name="CATEGORIA_TIPO_OBJETO")
@SequenceGenerator(name = "categoria_tipo_objeto_codigo_sequence", sequenceName = "categoria_tipo_objeto_seq")
public class CategoriaTipoObjeto extends AbstractEntity {

	private static final long serialVersionUID = -8156690805931975411L;

	private Long codigo;
	
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_tipo_objeto_codigo_sequence")
	@Column(name="CODIGO")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		
		final CategoriaTipoObjeto other = (CategoriaTipoObjeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
