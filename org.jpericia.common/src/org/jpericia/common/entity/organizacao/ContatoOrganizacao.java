package org.jpericia.common.entity.organizacao;

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
@Table(name="contato_organizacao")
@SequenceGenerator(name = "contato_organizacao_codigo_sequence", sequenceName = "contato_organizacao_seq")
public class ContatoOrganizacao extends AbstractEntity {

	private static final long serialVersionUID = 279419063030547359L;

	private Long codigo;
	
	private TituloContato tituloContato;
	
	private Organizacao organizacao;
	
	private String nome;
	
	private Long telefone;
	
	private Long celular;
	
	private String email;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_organizacao_codigo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoTituloContato", referencedColumnName="codigo")
	public TituloContato getTituloContato() {
		return tituloContato;
	}

	public void setTituloContato(TituloContato tituloContato) {
		this.tituloContato = tituloContato;
	}

	@ManyToOne()
	@JoinColumn(name="codigoOrganizacao", referencedColumnName="codigo")
	public Organizacao getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		final ContatoOrganizacao other = (ContatoOrganizacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
