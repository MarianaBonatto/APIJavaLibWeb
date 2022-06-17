package br.com.kiman.curso.rest.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.kiman.curso.dominio.PedidoBean;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.Pedido;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.filtro.PedidoFiltro;
import br.com.kiman.curso.dto.PedidoDTO;
import br.com.kiman.curso.dto.factory.ClienteFactory;
import br.com.kiman.curso.dto.factory.PedidoFactory;

@Path("pedido")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class PedidoResource {

	@Context
	private UriInfo uriInfo;
	@Inject
	private PedidoBean pedidoBean;

	@GET
	public Response getPedidos(@QueryParam("idCliente") Integer idCliente,
			@QueryParam("dataPedido") LocalDate dataPedido, @QueryParam("dataEntrega") LocalDate dataEntrega) {
		PedidoFiltro pedidoFiltro = new PedidoFiltro();
		pedidoFiltro.setDataPedido(dataPedido);
		pedidoFiltro.setIdCliente(idCliente);
		pedidoFiltro.setDataEntrega(dataEntrega);
		List<Pedido> pedidos = pedidoBean.buscaPor(pedidoFiltro);
		return Response.ok(new GenericEntity<List<PedidoDTO>>(PedidoFactory.toDTO(pedidos)) {
		}).build();
	}

	@GET
	@Path("{idPedido}")
	public Response getPedido(@PathParam("idPedido") Integer idPedido) {
		Pedido pedido = pedidoBean.buscaPorId(idPedido);
		return Response.ok(PedidoFactory.toDTO(pedido)).build();
	}

	@POST
	public Response postPedido(PedidoDTO pedidoDTO) {
		if (pedidoDTO == null) {
			throw new DominioException("Pedido invalido");
		}
		Cliente cliente = ClienteFactory.toEntity(pedidoDTO.getCliente());
		Pedido pedido = pedidoBean.criaPedido(cliente, PedidoFactory.toEntity(pedidoDTO.getItens()));
		return Response.created(getURI(pedido)).entity(PedidoFactory.toDTO(pedido)).build();
	}

	private URI getURI(Pedido pedido) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(pedido.getId()));
		return builder.build();
	}

}
