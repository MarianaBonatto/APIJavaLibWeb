package br.com.kiman.curso.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cliente", namespace = "http://soap.curso.kiman.com.br/")
public class ClienteDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer idCliente;
	@XmlElement(name = "nome", nillable = false)
	private String nome;
	@XmlElement(name = "produtos", nillable = false)
	private List<ProdutoDTO> produtos;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

}
