package br.com.kiman.curso.dominio.filtro;

import java.time.LocalDate;

public class PedidoFiltro {

	private Integer idPedido;
	private Integer idCliente;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public boolean hasIdPedido() {
		return idPedido != null;
	}

	public boolean hasDataPedido() {
		return dataPedido != null;
	}

	public boolean hasIdCliente() {
		return idCliente != null;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public boolean hasDataEntrega() {
		return dataEntrega != null;
	}

}
