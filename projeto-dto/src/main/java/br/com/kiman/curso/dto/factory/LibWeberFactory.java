package br.com.kiman.curso.dto.factory;

import br.com.kiman.curso.dominio.entity.Libweber;
import br.com.kiman.curso.dto.LibWeberDTO;

public class LibWeberFactory {

	public static LibWeberDTO toDTO(Libweber libweber) {
		LibWeberDTO dto = new LibWeberDTO();
		dto.setIdLibWeber(libweber.getId());
		dto.setEmail(libweber.getEmail());
		dto.setSenha(libweber.getSenha());
		return dto;
	}

	public static Libweber toEntity(LibWeberDTO libweberDTO) {
		if (libweberDTO == null) {
			return null;
		}
		Libweber libweber = new Libweber();
		libweber.setId(libweberDTO.getIdLibWeber());
		libweber.setEmail(libweberDTO.getEmail());
		libweber.setSenha(libweberDTO.getSenha());
		return libweber;
	}

}
