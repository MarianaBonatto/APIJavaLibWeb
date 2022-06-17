package br.com.kiman.curso.rest.resource;

import java.time.LocalDate;

import javax.ws.rs.QueryParam;

public class ClienteFiltro {

	@QueryParam("idCliente")
	private Integer idCliente;
	@QueryParam("dataPedido")
	private LocalDate dataPedido;
	@QueryParam("dataEntrega")
	private LocalDate dataEntrega;
	@QueryParam("dataEntrega")
	private LocalDate filtroNovo;

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

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
