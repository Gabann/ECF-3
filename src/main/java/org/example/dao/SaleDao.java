package org.example.dao;

import org.example.entity.Product;
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
		Double result = query.getSingleResult();
		session.close();
		return result == null ? 0.0 : result;
	}

	public List<Sale> getByCategory(Product.ClothesCategory clothesCategory)
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

	public Long getSalesCount()
	{
		Session session = sessionFactory.openSession();
		Query<Long> query = session.createNamedQuery("Sale.findNumberOfSales", Long.class);
		Long result = query.getSingleResult();
		session.close();
		return result;
	}

	public String getAllSolProductsWithQuantity()
	{
		StringBuilder builder = new StringBuilder();
		Session session = sessionFactory.openSession();
		Query query = session.createNamedQuery("Sale.findAllSoldProductsWithQuantity");
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

	public Long getSoldCountById(Long id)
	{
		Session session = sessionFactory.openSession();
		Query<Long> query = session.createNamedQuery("Sale.findSalesCountById", Long.class);
		query.setParameter("id", id);
		Long result = query.getSingleResult();
		session.close();
		return result;
	}

	public double getTotalSaleRevenuesById(Long productId)
	{
		Session session = sessionFactory.openSession();
		Query<Double> query = session.createNamedQuery("Sale.findTotalSaleRevenuesByProductId", Double.class);
		query.setParameter("id", productId);
		Double result = query.getSingleResult();
		session.close();
		return result == null ? 0.0 : result;
	}
}
