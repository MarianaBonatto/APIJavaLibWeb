package br.com.kiman.curso.rest.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.kiman.curso.dominio.ClienteBean;
import br.com.kiman.curso.dominio.ListaPrecoBean;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.ListaPreco;
import br.com.kiman.curso.dto.ClienteDTO;
import br.com.kiman.curso.dto.factory.ListaPrecoFactory;

@Path("cliente/{idCliente}/listaPreco")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class ClientePrecoResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private ClienteBean clienteBean;
	@Inject
	private ListaPrecoBean listaPrecoBean;

	@GET
	public Response getCliente(@PathParam("idCliente") Integer idCliente) {
		Cliente cliente = clienteBean.buscaPorId(idCliente);
		List<ListaPreco> listaPreco = listaPrecoBean.listaPrecoPor(cliente);
		return Response.ok(ListaPrecoFactory.toDTO(cliente, listaPreco)).build();
	}

	@PUT
	public Response atualizaCliente(@PathParam("idCliente") Integer idCliente, ClienteDTO clienteDTO) {
		clienteDTO.setIdCliente(idCliente);
		List<ListaPreco> entity = ListaPrecoFactory.toEntity(clienteDTO);
		listaPrecoBean.salva(entity);
		return getCliente(idCliente);
	}

}
