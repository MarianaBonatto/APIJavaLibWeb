package br.com.kiman.curso.dominio.entity;

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
@Table(name = "PALAVRA")
@NamedQueries(value = {
		@NamedQuery(name = Palavra.BUSCA_POR_STATUS, query = " SELECT p FROM Palavra p WHERE p.status = :pStatus "),
		@NamedQuery(name = Palavra.BUSCA_POR_PALAVRA, query = " SELECT p FROM Palavra p WHERE p.palavra = :pPalavra ")})
public class Palavra implements Entidade<Integer> {


	public static final String BUSCA_POR_STATUS = "Palavra.buscaPorStatus";		
	public static final String BUSCA_POR_PALAVRA = "Palavra.buscaPorPalavra";		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPalavra")
	private Integer id;
	
	@Column(name = "palavra")
	private String palavra;
	
	@Column(name = "url_video_oficial")
	private String video;
	
	@Column(name = "status")
	private String status;	

	@Column(name = "idCurador")
	private Integer idCurador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCurador", referencedColumnName = "idCurador", insertable = false, updatable = false)
	private Libweber curador;
	
	public Integer getId() {
		return id;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	
	public String getStatus() {
		return status;
	}

	public Integer getIdCurador() {
		return idCurador;
	}

	public void setIdCurador(Integer idCurador) {
		this.idCurador = idCurador;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((palavra == null) ? 0 : palavra.hashCode());
		result = prime * result + ((video == null) ? 0 : video.hashCode());
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
		Palavra other = (Palavra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (palavra == null) {
			if (other.palavra != null)
				return false;
		} else if (!palavra.equals(other.palavra))
			return false;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		return true;
	}

}
