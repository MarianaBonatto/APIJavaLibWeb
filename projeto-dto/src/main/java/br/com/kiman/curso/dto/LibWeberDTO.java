package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "libweber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "libweber", namespace = "http://rest.curso.kiman.com.br/")
public class LibWeberDTO {

	@XmlElement(name = "idLibWeber", nillable = false)
	private Integer idLibWeber;
	@XmlElement(name = "email", nillable = false)
	private String email;
	@XmlElement(name = "senha", nillable = false)
	private String senha;

	public Integer getIdLibWeber() {
		return idLibWeber;
	}
	public void setIdLibWeber(Integer idLibWeber) {
		this.idLibWeber = idLibWeber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
