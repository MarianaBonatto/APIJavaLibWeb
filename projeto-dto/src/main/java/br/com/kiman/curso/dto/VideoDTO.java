package br.com.kiman.curso.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "video")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "video", namespace = "http://rest.curso.kiman.com.br/")
public class VideoDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer idVideo;
	@XmlElement(name = "url_video", nillable = false)
	private String URL;
	@XmlElement(name = "indicador_publico", nillable = false)
	private Boolean isPublico;
	@XmlElement(name = "data", nillable = false)
	private Date data;
	@XmlElement(name = "palavra", nillable = false)
	private String palavra;
	@XmlElement(name = "curador", nillable = false)
	private Integer curador;
	
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public Integer getCurador() {
		return curador;
	}
	public void setCurador(Integer curador) {
		this.curador = curador;
	}
	public Integer getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(Integer idVideo) {
		this.idVideo = idVideo;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public Boolean getIsPublico() {
		return isPublico;
	}
	public void setIsPublico(Boolean isPublico) {
		this.isPublico = isPublico;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
