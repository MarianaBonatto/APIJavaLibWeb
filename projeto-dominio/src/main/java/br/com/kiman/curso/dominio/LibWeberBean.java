package br.com.kiman.curso.dominio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.LibweberDAO;
import br.com.kiman.curso.dominio.entity.Libweber;
import br.com.kiman.curso.dominio.exception.DominioException;

@Stateless
public class LibWeberBean {

	@Inject
	private LibweberDAO libweberDAO;
	
	public List<Libweber> buscaTodos() {
		return libweberDAO.buscaTodos();

	}

	public Libweber buscaPorId(Integer idLibweber) {
		Libweber libweber = libweberDAO.buscaPorId(idLibweber);
		if (libweber == null) {
			throw new DominioException("Usuário não encontrado.");
		}
		return libweber;
	}

	public Libweber buscaPorEmail(String email) {
		Libweber libweber = libweberDAO.buscaPorEmail(email);
		if (email == null) {
			throw new DominioException("Email inexistente");
		}
		return libweber;
	}

	public Libweber salva(Libweber libweber) {
		return libweberDAO.salvar(libweber);
	}

	public void remove(Integer idLibweber) {
		Libweber libweber = buscaPorId(idLibweber);
		libweberDAO.remove(libweber);
	}
	
}