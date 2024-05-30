package org.example.util;

import org.example.dao.CustomerDao;
import org.example.dao.ProductDao;
import org.example.dao.SaleDao;

public final class DaoUtils
{
	private static ProductDao productDao;
	private static CustomerDao customerDao;
	private static SaleDao saleDao;

	private DaoUtils()
	{
	}

	public static ProductDao getArticleDao()
	{
		if (productDao == null)
		{
			productDao = new ProductDao(HibernateUtil.getSessionFactory());
		}
		return productDao;
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
