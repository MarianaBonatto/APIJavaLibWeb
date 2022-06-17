package br.com.kiman.curso.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.exception.ValidacaoException;
import br.com.kiman.curso.dto.ErroDTO;

public class ErroFactory {

	private static ErroDTO constroiErro(String campo, String mensagem) {
		ErroDTO erroDTO = new ErroDTO();
		erroDTO.setCampo(campo);
		erroDTO.setMensagem(mensagem);
		return erroDTO;
	}

	public static ErroDTO constroiErro(DominioException ex) {
		return constroiErro(null, ex.getMessage());
	}

	public static List<ErroDTO> constroiErros(ValidacaoException validacaoException) {
		return validacaoException.getErros().stream().map(e -> constroiErro(e.getCampo(), e.getMensagem()))
				.collect(Collectors.toList());
	}

	public static ErroDTO mensagem(String mensagem) {
		return constroiErro(mensagem, null);
	}

}
