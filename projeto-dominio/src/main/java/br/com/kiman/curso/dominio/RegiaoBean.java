package br.com.kiman.curso.dominio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.RegiaoDAO;
import br.com.kiman.curso.dominio.entity.Regiao;
import br.com.kiman.curso.dominio.exception.DominioException;

@Stateless
public class RegiaoBean {

	@Inject
	private RegiaoDAO regiaoDAO;
	
	public List<Regiao> buscaTodos() {
		return regiaoDAO.buscaTodos();

	}

	public Regiao buscaPorId(Integer idRegiao) {
		Regiao regiao = regiaoDAO.buscaPorId(idRegiao);
		if (regiao == null) {
			throw new DominioException("Regiao não encontrado.");
		}
		return regiao;
	}
	
	public Regiao buscaPorRegiao(String pRegiao) {
		Regiao regiao = regiaoDAO.buscaPorRegiao(pRegiao);
		if (regiao == null) {
			throw new DominioException("Palavra ou região inexistentes");
		}
		return regiao;
	}

	public Regiao salva(Regiao regiao) {
		return regiaoDAO.salvar(regiao);
	}

	public void remove(Integer idRegiao) {
		Regiao regiao = buscaPorId(idRegiao);
		regiaoDAO.remove(regiao);
	}
	
}