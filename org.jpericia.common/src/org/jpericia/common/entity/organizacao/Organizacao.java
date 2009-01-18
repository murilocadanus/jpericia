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
import org.jpericia.common.entity.generic.Uf;


@Entity
@Table(name="organizacao")
@SequenceGenerator(name = "organizacao_sequence", sequenceName = "organizacao_id_seq")
public class Organizacao extends AbstractEntity {

	private static final long serialVersionUID = -426381767405280311L;

	private Long codigo;
	
	private TipoOrganizacao tipoOrganizacao; 
	
	private String nome;
	
	private Uf uf;
	
	private String cidade;
	
	private String bairro;
	
	private String endereco;
	
	private String logradouro;
	
	private Long numero;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizacao_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoTipoOrganizacao", referencedColumnName="codigo", nullable=false)
	public TipoOrganizacao getTipoOrganizacao() {
		return tipoOrganizacao;
	}

	public void setTipoOrganizacao(TipoOrganizacao tipoOrganizacao) {
		this.tipoOrganizacao = tipoOrganizacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoUf", referencedColumnName="codigo", nullable=false)
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
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
		final Organizacao other = (Organizacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
