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

import br.com.kiman.curso.dominio.LibWeberBean;
import br.com.kiman.curso.dominio.entity.Libweber;
import br.com.kiman.curso.dto.LibWeberDTO;
import br.com.kiman.curso.dto.factory.LibWeberFactory;

@Path("libweber")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class LibweberResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private LibWeberBean libweberBean;

	@GET
	public Response getLibwebers() {
		List<Libweber> libwebers = libweberBean.buscaTodos();
		List<LibWeberDTO> libwebersDTO = libwebers.stream().map(c -> LibWeberFactory.toDTO(c)).collect(Collectors.toList());
		return Response.ok(new GenericEntity<List<LibWeberDTO>>(libwebersDTO) {
		}).build();
	}

	@GET
	@Path("{idLibweber}")
	public Response getLibweber(@PathParam("idLibweber") Integer idLibweber) {
		Libweber libweber = libweberBean.buscaPorId(idLibweber);
		return Response.ok(LibWeberFactory.toDTO(libweber)).build();
	}

	@POST
	public Response novoLibweber(LibWeberDTO libweberDTO) {
		libweberDTO.setIdLibWeber(null);
		Libweber libweber = libweberBean.salva(LibWeberFactory.toEntity(libweberDTO));
		return Response.created(getURI(libweber)).entity(LibWeberFactory.toDTO(libweber)).build();
	}

	@PUT
	@Path("{idLibweber}")
	public Response atualizaLibweber(@PathParam("idLibweber") Integer idLibweber, LibWeberDTO libweberDTO) {
		libweberDTO.setIdLibWeber(idLibweber);
		Libweber libweber = libweberBean.salva(LibWeberFactory.toEntity(libweberDTO));
		return Response.ok(LibWeberFactory.toDTO(libweber)).build();
	}

	@DELETE
	@Path("{idLibweber}")
	public Response removeLibweber(@PathParam("idLibweber") Integer idLibweber) {
		libweberBean.remove(idLibweber);
		return Response.noContent().build();
	}

	private URI getURI(Libweber libweber) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(libweber.getId()));
		return builder.build();
	}
}
