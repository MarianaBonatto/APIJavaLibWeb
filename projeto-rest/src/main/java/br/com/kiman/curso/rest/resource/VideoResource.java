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

import br.com.kiman.curso.dominio.VideoBean;
import br.com.kiman.curso.dominio.entity.Video;
import br.com.kiman.curso.dto.VideoDTO;
import br.com.kiman.curso.dto.factory.VideoFactory;

@Path("video")
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
public class VideoResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private VideoBean videoBean;

	@GET
	public Response getVideos() {
		List<Video> videos = videoBean.buscaTodos();
		List<VideoDTO> videosDTO = videos.stream().map(c -> VideoFactory.toDTO(c)).collect(Collectors.toList());
		return Response.ok(new GenericEntity<List<VideoDTO>>(videosDTO) {
		}).build();
	}

	@GET
	@Path("{idVideo}")
	public Response getVideo(@PathParam("idVideo") Integer idVideo) {
		Video video = videoBean.buscaPorId(idVideo);
		return Response.ok(VideoFactory.toDTO(video)).build();
	}

	@POST
	public Response novoVideo(VideoDTO videoDTO) {
		videoDTO.setIdVideo(null);
		Video video = videoBean.salva(VideoFactory.toEntity(videoDTO));
		return Response.created(getURI(video)).entity(VideoFactory.toDTO(video)).build();
	}

	@PUT
	@Path("{idVideo}")
	public Response atualizaVideo(@PathParam("idVideo") Integer idVideo, VideoDTO videoDTO) {
		videoDTO.setIdVideo(idVideo);
		Video video = videoBean.salva(VideoFactory.toEntity(videoDTO));
		return Response.ok(VideoFactory.toDTO(video)).build();
	}

	@DELETE
	@Path("{idVideo}")
	public Response removeVideo(@PathParam("idVideo") Integer idVideo) {
		videoBean.remove(idVideo);
		return Response.noContent().build();
	}

	private URI getURI(Video video) {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(video.getId()));
		return builder.build();
	}
}
