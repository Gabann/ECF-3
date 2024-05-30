package org.example.util;

import org.example.dao.AbstractDao;
import org.example.entity.Article;
import org.example.entity.Customer;
import org.example.entity.Sale;

public class DaoUtils
{
	private DaoUtils()
	{
	}

	public static AbstractDao<Article> getArticleDao()
	{
		return new AbstractDao<>(HibernateUtil.getSessionFactory(), Article.class);
	}

	public static AbstractDao<Customer> getCustomerDao()
	{
		return new AbstractDao<>(HibernateUtil.getSessionFactory(), Customer.class);
	}

	public static AbstractDao<Sale> getSaleDao()
	{
		return new AbstractDao<>(HibernateUtil.getSessionFactory(), Sale.class);
	}

}
