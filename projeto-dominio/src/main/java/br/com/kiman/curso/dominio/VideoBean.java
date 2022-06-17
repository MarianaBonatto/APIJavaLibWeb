package br.com.kiman.curso.dominio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.VideoDAO;
import br.com.kiman.curso.dominio.entity.Video;
import br.com.kiman.curso.dominio.exception.DominioException;

@Stateless
public class VideoBean {

	@Inject
	private VideoDAO videoDAO;
	
	public List<Video> buscaTodos() {
		return videoDAO.buscaTodos();

	}

	public Video buscaPorId(Integer idVideo) {
		Video video = videoDAO.buscaPorId(idVideo);
		if (video == null) {
			throw new DominioException("Video não encontrado.");
		}
		return video;
	}

	public List<Video> buscaPorEmail(String palavra) {
		List<Video> video = videoDAO.buscaPorPalavra(palavra);
		if (palavra == null) {
			throw new DominioException("Palavra inexistente");
		}
		return video;
	}
	
	public List<Video> buscaPorRegiao(String palavra, String regiao) {
		List<Video> video = videoDAO.buscaPorRegiao(regiao, palavra);
		if (palavra == null || regiao == null) {
			throw new DominioException("Palavra ou região inexistentes");
		}
		return video;
	}

	public Video salva(Video video) {
		return videoDAO.salvar(video);
	}

	public void remove(Integer idVideo) {
		Video video = buscaPorId(idVideo);
		videoDAO.remove(video);
	}
	
}