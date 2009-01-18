package org.jpericia.common.entity.pericia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.organizacao.ContatoOrganizacao;
import org.jpericia.common.entity.perito.Perito;


@Entity
@Table(name="pericia")
@SequenceGenerator(name = "pericia_sequence", sequenceName = "pericia_id_seq")
public class Pericia extends AbstractEntity {

	private static final long serialVersionUID = -426381767405280311L;

	private Long codigo;
	
	private ContatoOrganizacao contatoOrganizacao;
	
	private Perito peritoResponsavel;
	
	private String titulo;
	
	private String descricao;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private Boolean finalizada;
	
	private Date dataFinalizada;
	
	//Laudo
	
	private String introducao;
	
	private String conclusao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pericia_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne()
	@JoinColumn(name="codigoContatoOrganizacao", referencedColumnName="codigo", nullable=false)
	public ContatoOrganizacao getContatoOrganizacao() {
		return contatoOrganizacao;
	}

	public void setContatoOrganizacao(ContatoOrganizacao contatoOrganizacao) {
		this.contatoOrganizacao = contatoOrganizacao;
	}

	@ManyToOne()
	@JoinColumn(name="codigoPeritoResponsavel", referencedColumnName="codigo", nullable=false)
	public Perito getPeritoResponsavel() {
		return peritoResponsavel;
	}

	public void setPeritoResponsavel(Perito peritoResponsavel) {
		this.peritoResponsavel = peritoResponsavel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Lob
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	@Lob
	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}

	@Lob
	public String getConclusao() {
		return conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}
	
	public Date getDataFinalizada() {
		return dataFinalizada;
	}

	public void setDataFinalizada(Date dataFinalizada) {
		this.dataFinalizada = dataFinalizada;
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
		final Pericia other = (Pericia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
