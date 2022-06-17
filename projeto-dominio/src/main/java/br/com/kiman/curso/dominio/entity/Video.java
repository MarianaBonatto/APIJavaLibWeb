package br.com.kiman.curso.dominio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEO")
@NamedQueries(value = {
	@NamedQuery(name = Video.BUSCA_POR_REGIAO, query = " SELECT v FROM Video v JOIN FETCH v.regiao JOIN FETCH v.palavra JOIN FETCH v.libweber JOIN FETCH v.curador WHERE v.regiao.regiao = :pRegiao AND v.palavra.palavra = :pPalavra  "),
	@NamedQuery(name = Video.BUSCA_POR_POSTER, query = " SELECT v FROM Video v JOIN FETCH v.regiao JOIN FETCH v.palavra JOIN FETCH v.libweber JOIN FETCH v.curador WHERE v.libweber.email = :pEmail "),
	@NamedQuery(name = Video.BUSCA_POR_PALAVRA, query = " SELECT v FROM Video v JOIN FETCH v.regiao JOIN FETCH v.palavra JOIN FETCH v.libweber JOIN FETCH v.curador WHERE v.palavra.palavra = :pPalavra ")})
public class Video implements Entidade<Integer> {

	public static final String BUSCA_POR_REGIAO = "Video.buscaPorRegiao";
	public static final String BUSCA_POR_POSTER = "Video.buscaPorLibweber";
	public static final String BUSCA_POR_PALAVRA = "Video.buscaPorPalavra";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVideo")
	private Integer id;
	
	@Column(name = "url_video")
	private String url;
	
	@Column(name = "indicador_publico")	
	private Boolean isPublico;
	
	@Column(name = "data")	
	private Date data;
	
	@Column(name = "idPalavra")	
	private Integer idPalavra;
	
	@Column(name = "idRegiao")	
	private Integer idRegiao;
	
	@Column(name = "idLibWeber")	
	private Integer idLibWeber;
	
	@Column(name = "idCurador")	
	private Integer idCurador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLibweber", referencedColumnName = "idLibweber", insertable = false, updatable = false)
	private Libweber libweber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCurador", referencedColumnName = "idCurador", insertable = false, updatable = false)
	private Libweber curador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRegiao", referencedColumnName = "idRegiao", insertable = false, updatable = false)
	private Regiao regiao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPalavra", referencedColumnName = "idPalavra", insertable = false, updatable = false)
	private Palavra palavra;
	
	public Integer getIdPalavra() {
		return idPalavra;
	}

	public void setIdPalavra(Integer idPalavra) {
		this.idPalavra = idPalavra;
	}

	public Integer getIdLibWeber() {
		return idLibWeber;
	}

	public void setIdLibWeber(Integer idLibWeber) {
		this.idLibWeber = idLibWeber;
	}

	public Integer getIdCurador() {
		return idCurador;
	}

	public void setIdCurador(Integer idCurador) {
		this.idCurador = idCurador;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getUrl() {
		return url;
	}

	public Integer getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}

	public Libweber getLibweber() {
		return libweber;
	}

	public void setLibweber(Libweber libweber) {
		this.idLibWeber = null;
		if (libweber != null) {
			this.idLibWeber = libweber.getId();
		}
		this.libweber = libweber;
	}

	public Libweber getCurador() {
		return curador;
	}

	public void setCurador(Libweber curador) {
		this.idCurador = null;
		if (curador != null) {
			this.idCurador = curador.getId();
		}
		this.curador = curador;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.idRegiao = null;
		if (regiao != null) {
			this.idRegiao = regiao.getId();
		}
		this.regiao = regiao;
	}

	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.idPalavra = null;
		if (palavra != null) {
			this.idPalavra = palavra.getId();
		}
		this.palavra = palavra;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsPublico() {
		return isPublico;
	}

	public void setIsPublico(Boolean isPublico) {
		this.isPublico = isPublico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Video && this.id != null) {
			Video other = (Video) obj;
			return this.id.equals(other.getId());
		}
		return false;
	}

}
