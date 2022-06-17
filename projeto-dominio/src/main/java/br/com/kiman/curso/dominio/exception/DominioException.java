package br.com.kiman.curso.dominio.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DominioException extends RuntimeException {

	private static final long serialVersionUID = -4360165776867697709L;

	public DominioException() {
	}

	public DominioException(String mensagem) {
		super(mensagem);
	}

}
