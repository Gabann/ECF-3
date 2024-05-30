package org.example.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil
{
	private static SessionFactory sessionFactory;

	private HibernateUtil()
	{
	}

	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			try
			{
				sessionFactory = buildSessionFactory();
			} catch (RuntimeException ex)
			{
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory()
	{
		try
		{
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e)
		{
			System.out.println("Something went wrong: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void close()
	{
		getSessionFactory().close();
	}
}
