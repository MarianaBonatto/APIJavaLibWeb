package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "palavra")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "palavra", namespace = "http://rest.curso.kiman.com.br/")
public class PalavraDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer id;
	
	@XmlElement(name = "palavra", nillable = false)
	private String palavra;
	
	@XmlElement(name = "video_url", nillable = false)
	private String video;
	
	@XmlElement(name = "status", nillable = false)
	private String status;	

	@XmlElement(name = "id_curador", nillable = false)
	private Integer idCurador;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdCurador() {
		return idCurador;
	}

	public void setIdCurador(Integer idCurador) {
		this.idCurador = idCurador;
	}
}
