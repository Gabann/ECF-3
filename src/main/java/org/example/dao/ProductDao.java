package org.example.dao;

import org.example.entity.Product;
import org.hibernate.SessionFactory;

public class ProductDao extends GenericDao<Product>
{
	public ProductDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, Product.class);
	}
}
