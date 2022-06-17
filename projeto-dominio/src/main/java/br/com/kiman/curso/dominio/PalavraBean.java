package br.com.kiman.curso.dominio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.PalavraDAO;
import br.com.kiman.curso.dominio.entity.Palavra;
import br.com.kiman.curso.dominio.exception.DominioException;

@Stateless
public class PalavraBean {

	@Inject
	private PalavraDAO palavraDAO;
	
	public List<Palavra> buscaTodos() {
		return palavraDAO.buscaTodos();

	}

	public Palavra buscaPorId(Integer idPalavra) {
		Palavra palavra = palavraDAO.buscaPorId(idPalavra);
		if (palavra == null) {
			throw new DominioException("Palavra não encontrado.");
		}
		return palavra;
	}

	public List<Palavra> buscaPorStatus(String status) {
		List<Palavra> palavra = palavraDAO.buscaPorStatus(status);
		if (status == null) {
			throw new DominioException("Palavra inexistente");
		}
		return palavra;
	}
	
	public Palavra buscaPorPalavra(String pPalavra) {
		Palavra palavra = palavraDAO.buscaPorPalavra(pPalavra);
		if (pPalavra == null) {
			throw new DominioException("Palavra ou região inexistentes");
		}
		return palavra;
	}

	public Palavra salva(Palavra palavra) {
		return palavraDAO.salvar(palavra);
	}

	public void remove(Integer idPalavra) {
		Palavra palavra = buscaPorId(idPalavra);
		palavraDAO.remove(palavra);
	}
	
}