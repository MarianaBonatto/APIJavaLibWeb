package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Erro")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "erro", namespace = "http://soap.curso.kiman.com.br/")
public class ErroDTO {

	@XmlElement(name = "mensagem", nillable = false)
	private String mensagem;
	@XmlElement(name = "campo", nillable = false)
	private String campo;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

}
