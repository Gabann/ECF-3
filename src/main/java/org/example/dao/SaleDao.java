package org.example.dao;

import org.example.entity.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SaleDao extends GenericDao<Sale>
{

	public SaleDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, Sale.class);
	}

	public double getTotalPrice()
	{
		Session session = sessionFactory.openSession();
		Query<Double> query = session.createNamedQuery("Sale.findTotalSoldAmount", Double.class);
		double result = query.getSingleResult();
		session.close();
		return result;
	}

	public String getAllSoldArticles()
	{
		StringBuilder builder = new StringBuilder();
		Session session = sessionFactory.openSession();
		Query query = session.createNamedQuery("Sale.findAllSoldArticle");
		List results = query.getResultList();
		for (Object result : results)
		{
			Object[] p = (Object[]) result;
			builder.append(p[0]);
			builder.append("\n");
			builder.append("Quantity sold: ").append(p[1]);
			builder.append("\n");
			builder.append("\n");
		}
		session.close();
		return builder.toString();
	}
}
