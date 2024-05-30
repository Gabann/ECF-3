package org.example.util;

import org.example.dao.ArticleDao;
import org.example.dao.CustomerDao;
import org.example.dao.SaleDao;

public final class DaoUtils
{
	private static ArticleDao articleDao;
	private static CustomerDao customerDao;
	private static SaleDao saleDao;

	private DaoUtils()
	{
	}

	public static ArticleDao getArticleDao()
	{
		if (articleDao == null)
		{
			articleDao = new ArticleDao(HibernateUtil.getSessionFactory());
		}
		return articleDao;
	}

	public static CustomerDao getCustomerDao()
	{
		if (customerDao == null)
		{
			customerDao = new CustomerDao(HibernateUtil.getSessionFactory());
		}
		return customerDao;
	}

	public static SaleDao getSaleDao()
	{
		if (saleDao == null)
		{
			saleDao = new SaleDao(HibernateUtil.getSessionFactory());
		}
		return saleDao;
	}
}
