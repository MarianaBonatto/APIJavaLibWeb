package br.com.kiman.curso.rest.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.kiman.curso.dominio.ClienteBean;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dto.ClienteDTO;
import br.com.kiman.curso.dto.factory.ClienteFactory;

@Path("cliente")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class ClienteResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private ClienteBean clienteBean;

	@GET
	public Response getClientes() {
		List<Cliente> clientes = clienteBean.buscaTodos();
		List<ClienteDTO> clientesDTO = clientes.stream().map(c -> ClienteFactory.toDTO(c)).collect(Collectors.toList());
		return Response.ok(new GenericEntity<List<ClienteDTO>>(clientesDTO) {
		}).build();
	}

	@GET
	@Path("{idCliente}")
	public Response getCliente(@PathParam("idCliente") Integer idCliente) {
		Cliente cliente = clienteBean.buscaPorId(idCliente);
		return Response.ok(ClienteFactory.toDTO(cliente)).build();
	}

	@POST
	public Response novoCliente(ClienteDTO clienteDTO) {
		clienteDTO.setIdCliente(null);
		Cliente cliente = clienteBean.salva(ClienteFactory.toEntity(clienteDTO));
		return Response.created(getURI(cliente)).entity(ClienteFactory.toDTO(cliente)).build();
	}

	@PUT
	@Path("{idCliente}")
	public Response atualizaCliente(@PathParam("idCliente") Integer idCliente, ClienteDTO clienteDTO) {
		clienteDTO.setIdCliente(idCliente);
		Cliente cliente = clienteBean.salva(ClienteFactory.toEntity(clienteDTO));
		return Response.ok(ClienteFactory.toDTO(cliente)).build();
	}

	@DELETE
	@Path("{idCliente}")
	public Response removeCliente(@PathParam("idCliente") Integer idCliente) {
		clienteBean.remove(idCliente);
		return Response.noContent().build();
	}

	private URI getURI(Cliente cliente) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(cliente.getId()));
		return builder.build();
	}
}
