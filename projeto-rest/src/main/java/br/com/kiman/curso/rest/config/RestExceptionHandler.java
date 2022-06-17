package br.com.kiman.curso.rest.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.exception.ValidacaoException;
import br.com.kiman.curso.dto.factory.ErroFactory;

@Provider
public class RestExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		if (e instanceof ValidacaoException) {
			ValidacaoException validacaoException = (ValidacaoException) e;
			return Response.status(Status.BAD_REQUEST).entity(ErroFactory.constroiErros(validacaoException)).build();
		}
		if (e instanceof DominioException) {
			DominioException dominioException = (DominioException) e;
			return Response.status(Status.BAD_REQUEST).entity(ErroFactory.constroiErro(dominioException)).build();
		}
		e.printStackTrace();
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(ErroFactory.mensagem("Ocorreu um erro ao processar sua solicitação"))
				.header("Content-Type", "application/json").build();
	}

}
