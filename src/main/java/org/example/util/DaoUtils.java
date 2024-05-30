package org.example.util;

import org.example.dao.ArticleDao;
import org.example.dao.CustomerDao;
import org.example.dao.SaleDao;

public class DaoUtils
{
	private DaoUtils()
	{
	}

	public static ArticleDao getArticleDao()
	{
		return new ArticleDao(HibernateUtil.getSessionFactory());
	}

	public static CustomerDao getCustomerDao()
	{
		return new CustomerDao(HibernateUtil.getSessionFactory());
	}

	public static SaleDao getSaleDao()
	{
		return new SaleDao(HibernateUtil.getSessionFactory());
	}
}
