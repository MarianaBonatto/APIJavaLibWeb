package br.com.kiman.curso.dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.kiman.curso.dominio.entity.Regiao;

public class RegiaoDAO extends GenericDAO<Regiao, Integer> {

	@PersistenceContext
	private EntityManager em;

	public Regiao buscaPorRegiao(String regiao) {
		try {
			return em.createNamedQuery(Regiao.BUSCA_POR_REGIAO, Regiao.class).setParameter("pRegiao", regiao)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
