package org.jpericia.common.entity.perito;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;


@Entity
@Table(name="titulo_perito")
//@SequenceGenerator(name = "titulo_perito_codigo_sequence", sequenceName = "titulo_perito_seq")
public class TituloPerito extends AbstractEntity {

	private static final long serialVersionUID = 9167040546975784553L;

	private Long codigo;
	
	private String titulo;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulo_perito_codigo_sequence")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
		final TituloPerito other = (TituloPerito) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
