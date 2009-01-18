package org.jpericia.common.entity.pericia;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;


@Entity
@Table(name="laudo")
@SequenceGenerator(name = "laudo_sequence", sequenceName = "laudo_id_seq")
public class Laudo extends AbstractEntity {

	private static final long serialVersionUID = -8165499620484705373L;
	
	private Long codigo;
	
	private Pericia pericia;
	
	private String introducao;
	
	private String conslusao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laudo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "periciaId")
	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}

	public String getConslusao() {
		return conslusao;
	}

	public void setConslusao(String conslusao) {
		this.conslusao = conslusao;
	}

	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
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
		final Laudo other = (Laudo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
