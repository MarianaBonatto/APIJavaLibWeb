package br.com.kiman.curso.dominio.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.kiman.curso.dominio.entity.Entidade;

public class GenericDAO<T extends Entidade<ID>, ID> {

	@PersistenceContext
	private EntityManager em;

	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T buscaPorId(ID id) {
		if (id == null) {
			return null;
		}
		return em.find(clazz, id);
	}

	public List<T> buscaTodos() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		query.from(clazz);
		return em.createQuery(query).getResultList();
	}

	public T salvar(T t) {
		if (t.getId() == null) {
			em.persist(t);
			em.flush();
		}
		t = em.merge(t);
		return t;
	}

	public void remove(T t) {
		if (t != null) {
			t = buscaPorId(t.getId());
			if (t != null) {
				em.remove(t);
			}
		}
	}

}
