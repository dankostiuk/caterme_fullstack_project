package com.dan.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
