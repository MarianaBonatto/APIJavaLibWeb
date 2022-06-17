package br.com.kiman.curso.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.kiman.curso.dominio.entity.Video;

public class VideoDAO extends GenericDAO<Video, Integer> {

	@PersistenceContext
	private EntityManager em;

	public List<Video> buscaPorRegiao(String regiao, String palavra) {
		try {
			return em.createNamedQuery(Video.BUSCA_POR_REGIAO, Video.class)
					.setParameter("pRegiao", regiao)
					.setParameter("pPalavra", palavra)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Video> buscaPorPalavra(String palavra) {
		try {
			return em.createNamedQuery(Video.BUSCA_POR_PALAVRA, Video.class).setParameter("pPalavra", palavra)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Video> buscaPorLibweber(String email) {
		try {
			return em.createNamedQuery(Video.BUSCA_POR_POSTER, Video.class).setParameter("pEmail", email)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
