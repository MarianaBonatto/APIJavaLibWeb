package br.com.kiman.curso.dto;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.kiman.curso.dto.converter.LocalDateAdapter;

@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pedido", namespace = "http://soap.curso.kiman.com.br/")
public class PedidoDTO {

	@XmlElement(name = "id", nillable = false)
	private Integer idPedido;
	@XmlElement(name = "dataPedido", nillable = false)
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dataPedido;
	@XmlElement(name = "dataEntrega", nillable = false)
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dataEntrega;
	@XmlElement(name = "valor", nillable = false)
	private Double valorTotal;
	@XmlElement(name = "cliente", nillable = false)
	private ClienteDTO cliente;
	@XmlElement(name = "itens", nillable = false)
	private List<ItemPedidoDTO> itens;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
