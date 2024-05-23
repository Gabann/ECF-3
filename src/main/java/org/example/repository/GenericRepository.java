package org.example.repository;

import javax.persistence.EntityManager;
import java.util.List;

public class GenericRepository<T>
{
	private final Class<T> entityClass;
	protected EntityManager em;

	public GenericRepository(EntityManager em, Class<T> entityClass)
	{
		this.em = em;
		this.entityClass = entityClass;
	}

	public void save(T element)
	{
		em.getTransaction().begin();
		em.persist(element);
		em.getTransaction().commit();
	}

	public void delete(T element)
	{
		em.getTransaction().begin();
		em.remove(element);
		em.getTransaction().commit();
	}

	public T findById(int id)
	{
		return em.find(entityClass, id);
	}

	public List<T> findAll()
	{
		return em.createQuery("SELECT e from " + entityClass.getSimpleName() + " e", entityClass).getResultList();
	}
}
