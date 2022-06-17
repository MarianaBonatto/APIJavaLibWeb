package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "produto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "produto", namespace = "http://soap.curso.kiman.com.br/")
public class ProdutoDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer idProduto;
	@XmlElement(name = "descricao", nillable = false)
	private String descricao;
	@XmlElement(name = "preco", nillable = false)
	private Double preco;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
