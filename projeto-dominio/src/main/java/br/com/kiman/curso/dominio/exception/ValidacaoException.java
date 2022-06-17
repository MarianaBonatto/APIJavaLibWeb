package br.com.kiman.curso.dominio.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ValidacaoException extends DominioException {

	private static final long serialVersionUID = -4360165776867697709L;

	private final List<ErroInfo> erros;

	public ValidacaoException(String mensagem) {
		super(mensagem);
		this.erros = new ArrayList<ErroInfo>();
	}

	public ValidacaoException(List<ErroInfo> erros) {
		this.erros = erros;
	}

	public List<ErroInfo> getErros() {
		return erros;
	}

}