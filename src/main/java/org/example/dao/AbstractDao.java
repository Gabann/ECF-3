package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AbstractDao<T>
{
	private final SessionFactory sessionFactory;
	Class<T> c;

	public AbstractDao(SessionFactory sessionFactory, Class<T> c)
	{
		this.sessionFactory = sessionFactory;
		this.c = c;
	}

	public void saveOrUpdate(T entity)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
		session.close();
	}

	public T getById(Long id)
	{
		Session session = sessionFactory.openSession();
		T entity = session.get(c, id);
		session.close();
		return entity;
	}

	public List<T> getAll()
	{
		Session session = sessionFactory.openSession();
		List<T> entities = session.createQuery("from " + c.getName(), c).list();
		session.close();
		return entities;
	}

	public void delete(T entity)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.delete(entity);
			transaction.commit();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			session.close();
		}
	}

	public void deleteById(Long id)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		delete(getById(id));
		transaction.commit();
		session.close();
	}
}
