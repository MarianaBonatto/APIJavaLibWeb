package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "regiao")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regiao", namespace = "http://rest.curso.kiman.com.br/")
public class RegiaoDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer id;

	@XmlElement(name = "regiao", nillable = false)
	private String regiao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}	
}
