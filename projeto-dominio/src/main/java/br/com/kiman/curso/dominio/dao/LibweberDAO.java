package br.com.kiman.curso.dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.kiman.curso.dominio.entity.Libweber;

public class LibweberDAO extends GenericDAO<Libweber, Integer> {

	@PersistenceContext
	private EntityManager em;

	public Libweber buscaPorEmail(String email) {
		try {
			return em.createNamedQuery(Libweber.BUSCA_POR_EMAIL, Libweber.class).setParameter("pEmail", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
