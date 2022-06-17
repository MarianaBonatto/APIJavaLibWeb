package br.com.kiman.curso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "itemPedido")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemPedido", namespace = "http://soap.curso.kiman.com.br/")
public class ItemPedidoDTO {

	@XmlElement(name = "numItem", nillable = false)
	private Integer numItem;
	@XmlElement(name = "preco", nillable = false)
	private Double preco;
	@XmlElement(name = "quantidade", nillable = false)
	private Integer quantidade;
	@XmlElement(name = "produto", nillable = false)
	private ProdutoDTO produto;

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
