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

import br.com.kiman.curso.dominio.ProdutoBean;
import br.com.kiman.curso.dominio.entity.Produto;
import br.com.kiman.curso.dto.ProdutoDTO;
import br.com.kiman.curso.dto.factory.ProdutoFactory;

@Path("produto")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class ProdutoResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private ProdutoBean produtoBean;

	@GET
	public Response getProdutos() {
		List<Produto> produtos = produtoBean.buscaTodos();
		List<ProdutoDTO> produtosDTO = produtos.stream().map(p -> ProdutoFactory.toDTO(p)).collect(Collectors.toList());
		return Response.ok(new GenericEntity<List<ProdutoDTO>>(produtosDTO) {
		}).build();
	}

	@GET
	@Path("{idProduto}")
	public Response getProduto(@PathParam("idProduto") Integer idProduto) {
		Produto produto = produtoBean.buscaPorId(idProduto);
		return Response.ok(ProdutoFactory.toDTO(produto)).build();
	}

	@POST
	public Response novoProduto(ProdutoDTO produtoDTO) {
		produtoDTO.setIdProduto(null);
		Produto produto = produtoBean.salvaProduto(ProdutoFactory.toEntity(produtoDTO));
		return Response.created(getURI(produto)).entity(ProdutoFactory.toDTO(produto)).build();
	}

	@PUT
	@Path("{idProduto}")
	public Response atualizaProduto(@PathParam("idProduto") Integer idProduto, ProdutoDTO produtoDTO) {
		produtoDTO.setIdProduto(idProduto);
		Produto produto = produtoBean.salvaProduto(ProdutoFactory.toEntity(produtoDTO));
		return Response.ok(ProdutoFactory.toDTO(produto)).build();
	}

	@DELETE
	@Path("{idProduto}")
	public Response removeProduto(@PathParam("idProduto") Integer idProduto) {
		produtoBean.remove(idProduto);
		return Response.noContent().build();
	}

	private URI getURI(Produto produto) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(produto.getId().toString());
		return builder.build();
	}
}
