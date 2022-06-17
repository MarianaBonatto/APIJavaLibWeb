package br.com.kiman.curso.dto.factory;

import br.com.kiman.curso.dominio.entity.Palavra;
import br.com.kiman.curso.dto.PalavraDTO;

public class PalavraFactory {

	public static PalavraDTO toDTO(Palavra palavra) {
		PalavraDTO dto = new PalavraDTO();
		dto.setId(palavra.getId());
		dto.setIdCurador(palavra.getIdCurador());
		dto.setPalavra(palavra.getPalavra());
		dto.setStatus(palavra.getStatus());
		dto.setVideo(palavra.getVideo());
		return dto;
	}

	public static Palavra toEntity(PalavraDTO palavraDTO) {
		if (palavraDTO == null) {
			return null;
		}
		Palavra palavra = new Palavra();
		palavra.setId(palavraDTO.getId());
		palavra.setIdCurador(palavraDTO.getIdCurador());
		palavra.setPalavra(palavraDTO.getPalavra());
		palavra.setStatus(palavraDTO.getStatus());
		palavra.setVideo(palavraDTO.getVideo());
		return palavra;
	}

}
