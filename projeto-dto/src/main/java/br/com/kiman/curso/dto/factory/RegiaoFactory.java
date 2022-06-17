package br.com.kiman.curso.dto.factory;

import br.com.kiman.curso.dominio.entity.Regiao;
import br.com.kiman.curso.dto.RegiaoDTO;

public class RegiaoFactory {

	public static RegiaoDTO toDTO(Regiao regiao) {
		RegiaoDTO dto = new RegiaoDTO();
		dto.setId(regiao.getId());
		dto.setRegiao(regiao.getRegiao());
		return dto;
	}

	public static Regiao toEntity(RegiaoDTO regiaoDTO) {
		if (regiaoDTO == null) {
			return null;
		}
		Regiao regiao = new Regiao();
		regiao.setId(regiaoDTO.getId());
		regiao.setRegiao(regiaoDTO.getRegiao());
		return regiao;
	}

}
