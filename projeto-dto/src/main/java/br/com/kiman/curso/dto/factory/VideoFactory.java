package br.com.kiman.curso.dto.factory;

import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.VideoDAO;
import br.com.kiman.curso.dominio.entity.Video;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dto.VideoDTO;

public class VideoFactory {

	@Inject
	private static VideoDAO videoDAO;
	
	public static VideoDTO toDTO(Video video) {
		VideoDTO dto = new VideoDTO();
		dto.setIdVideo(video.getId());
		dto.setData(video.getData());
		dto.setIsPublico(video.getIsPublico());
		dto.setURL(video.getUrl());
		dto.setCurador(video.getIdCurador());
		dto.setPalavra(video.getPalavra().getPalavra());
		return dto;
	}

	public static Video toEntity(VideoDTO videoDTO) {
		if (videoDTO == null) {
			return null;
		}
		Video video = videoDAO.buscaPorId(videoDTO.getIdVideo());
		if (video == null) {
			throw new DominioException("Video não encontrado.");
		}
		return video;
	}

}
