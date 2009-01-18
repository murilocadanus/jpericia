package org.jpericia.common.entity.analise;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.common.entity.perito.Perito;


@Entity
@Table(name="analise")
@SequenceGenerator(name = "analise_codigo_sequence", sequenceName = "analise_seq")
public class Analise extends AbstractEntity {

	private static final long serialVersionUID = -1385673998253634900L;
	
	private Long codigo;
	
	private TipoAnalise tipoAnalise;
	
	private Pericia pericia;
	
	private Set<Perito> peritos;
	
	private Set<Objeto> objetos;
	
	private String titulo;
	
	private String descricao;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private Boolean finalizada;
	
	private String resultado;

	private Date dataFinalizada;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analise_codigo_sequence")
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoTipoAnalise", referencedColumnName="codigo", nullable=false)
	public TipoAnalise getTipoAnalise() {
		return tipoAnalise;
	}

	public void setTipoAnalise(TipoAnalise tipoAnalise) {
		this.tipoAnalise = tipoAnalise;
	}
	
	@ManyToOne()
	@JoinColumn(name="codigoPericia", referencedColumnName="codigo", nullable=false)
	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "analise_perito",
                     joinColumns = {@JoinColumn(name = "codigoAnalise")},
                     inverseJoinColumns = {@JoinColumn(name = "codigoPerito")})
	public Set<Perito> getPeritos() {
		return peritos;
	}

	public void setPeritos(Set<Perito> peritos) {
		this.peritos = peritos;
	}

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "analises")
	public Set<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(Set<Objeto> objetos) {
		this.objetos = objetos;
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

	@Lob
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	@Lob
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		
		final Analise other = (Analise) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
