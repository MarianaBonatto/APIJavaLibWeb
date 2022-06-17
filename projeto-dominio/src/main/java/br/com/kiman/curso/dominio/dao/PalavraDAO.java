package br.com.kiman.curso.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.kiman.curso.dominio.entity.Palavra;

public class PalavraDAO extends GenericDAO<Palavra, Integer> {

	@PersistenceContext
	private EntityManager em;
	
	public List<Palavra> buscaPorStatus(String status) {
		try {
			return em.createNamedQuery(Palavra.BUSCA_POR_STATUS, Palavra.class).setParameter("pStatus", status)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Palavra buscaPorPalavra(String palavra) {
		try {
			return em.createNamedQuery(Palavra.BUSCA_POR_PALAVRA, Palavra.class).setParameter("pPalavra", palavra)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
