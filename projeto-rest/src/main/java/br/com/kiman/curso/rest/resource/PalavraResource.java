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

import br.com.kiman.curso.dominio.PalavraBean;
import br.com.kiman.curso.dominio.entity.Palavra;
import br.com.kiman.curso.dto.PalavraDTO;
import br.com.kiman.curso.dto.factory.PalavraFactory;

@Path("palavra")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class PalavraResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private PalavraBean palavraBean;

	@GET
	public Response getPalavras() {
		List<Palavra> palavras = palavraBean.buscaTodos();
		List<PalavraDTO> palavrasDTO = palavras.stream().map(c -> PalavraFactory.toDTO(c)).collect(Collectors.toList());
		return Response.ok(new GenericEntity<List<PalavraDTO>>(palavrasDTO) {
		}).build();
	}

	@GET
	@Path("{idPalavra}")
	public Response getPalavra(@PathParam("idPalavra") Integer idPalavra) {
		Palavra palavra = palavraBean.buscaPorId(idPalavra);
		return Response.ok(PalavraFactory.toDTO(palavra)).build();
	}

	@POST
	public Response novoPalavra(PalavraDTO palavraDTO) {
		palavraDTO.setId(null);
		Palavra palavra = palavraBean.salva(PalavraFactory.toEntity(palavraDTO));
		return Response.created(getURI(palavra)).entity(PalavraFactory.toDTO(palavra)).build();
	}

	@PUT
	@Path("{idPalavra}")
	public Response atualizaPalavra(@PathParam("idPalavra") Integer idPalavra, PalavraDTO palavraDTO) {
		palavraDTO.setId(idPalavra);
		Palavra palavra = palavraBean.salva(PalavraFactory.toEntity(palavraDTO));
		return Response.ok(PalavraFactory.toDTO(palavra)).build();
	}

	@DELETE
	@Path("{idPalavra}")
	public Response removePalavra(@PathParam("idPalavra") Integer idPalavra) {
		palavraBean.remove(idPalavra);
		return Response.noContent().build();
	}

	private URI getURI(Palavra palavra) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(palavra.getId()));
		return builder.build();
	}
}
