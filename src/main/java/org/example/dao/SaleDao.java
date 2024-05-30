package org.example.dao;

import org.example.entity.Article;
import org.example.entity.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class SaleDao extends GenericDao<Sale>
{
	public SaleDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, Sale.class);
	}

	public double getTotalRevenues()
	{
		Session session = sessionFactory.openSession();
		Query<Double> query = session.createNamedQuery("Sale.findTotalSaleRevenues", Double.class);
		double result = query.getSingleResult();
		session.close();
		return result;
	}

	public List<Sale> getByCategory(Article.ClothesCategory clothesCategory)
	{
		Session session = sessionFactory.openSession();
		Query<Sale> query = session.createNamedQuery("Sale.findByCategory", Sale.class);
		query.setParameter("clothesCategory", clothesCategory);
		List<Sale> result = query.getResultList();
		session.close();
		return result;
	}

	public List<Sale> getBetweenDates(LocalDate startDate, LocalDate endDate)
	{
		Session session = sessionFactory.openSession();
		Query<Sale> query = session.createNamedQuery("Sale.findByPurchaseDateBetween", Sale.class);
		query.setParameter("purchaseDateStart", startDate);
		query.setParameter("purchaseDateEnd", endDate);
		List<Sale> results = query.getResultList();
		session.close();
		return results;
	}

	public String getAllSoldArticlesWithQuantity()
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
