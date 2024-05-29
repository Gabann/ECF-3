package org.example;

import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main
{
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	}
}
