package org.jpericia.common.entity.perito;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.generic.Uf;


@Entity
@Table(name="perito")
@SequenceGenerator(name = "perito_codigo_sequence", sequenceName = "perito_seq")
public class Perito extends AbstractEntity {

	private static final long serialVersionUID = 9167040546975784553L;

	private Long codigo;
	
	private FuncaoPerito funcaoPerito;
	
	private TituloPerito tituloPerito;
	
	private Set<Analise> analises;
	
	private String nome;
	
	private Long telefone;
	
	private Long celular;
	
	private Uf uf;
	
	private String cidade;
	
	private String bairro;
	
	private String endereco;
	
	private String logradouro;
	
	private Integer numero;
	
	private String usuario;
	
	private String senha;
	
	private boolean gestor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perito_codigo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoFuncaoPerito", referencedColumnName="codigo", nullable=false, updatable=false)
	public FuncaoPerito getFuncaoPerito() {
		return funcaoPerito;
	}

	public void setFuncaoPerito(FuncaoPerito funcaoPerito) {
		this.funcaoPerito = funcaoPerito;
	}

	@ManyToOne()
	@JoinColumn(name="codigoTituloPerito", referencedColumnName="codigo", nullable=false, updatable=false)
	public TituloPerito getTituloPerito() {
		return tituloPerito;
	}

	public void setTituloPerito(TituloPerito tituloPerito) {
		this.tituloPerito = tituloPerito;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="peritos")
	public Set<Analise> getAnalises() {
		return analises;
	}

	public void setAnalises(Set<Analise> analises) {
		this.analises = analises;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoUf", referencedColumnName="codigo", nullable=false, updatable=false)
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
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

	public boolean isGestor() {
		return gestor;
	}

	public void setGestor(boolean gestor) {
		this.gestor = gestor;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
		final Perito other = (Perito) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
