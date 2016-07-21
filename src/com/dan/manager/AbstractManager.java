package com.dan.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import com.dan.entity.User;

/**
 * Abstract class responsible for carrying out 
 * low-level transactions on the ORM.
 * 
 * @author dan
 *
 * @param <T> The entity type.
 */
public abstract class AbstractManager<T> {

	private final Class<T> _clazz;

	private EntityManager _em;
	private EntityManagerFactory _emf;
	
	public AbstractManager(Class<T> clazz) {
		_clazz = clazz;
		_emf = Persistence.createEntityManagerFactory("Hibernate");
		_em = _emf.createEntityManager();
	}
	
	public void writeTransaction(T object) {
		_em.getTransaction().begin();
		
		_em.persist(object);
		_em.getTransaction().commit();
		
		_em.close();
		_emf.close();
	}
	
	public T readTransaction(long id) {
		_em.getTransaction().begin();
		
		T object = _em.find(_clazz, id);
		
		_em.close();
		_emf.close();
	
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> readAllTransaction() {
		_em.getTransaction().begin();
		
		List<T> resultList = 
			_em.createQuery("SELECT t from " + _clazz.getSimpleName() + " t")
				.getResultList();
		 
		_em.close();
		_emf.close();
		
		 return resultList;
	}
}
