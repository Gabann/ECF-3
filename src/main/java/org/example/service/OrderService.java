package org.example.service;

import org.example.entity.Article;
import org.example.entity.Customer;
import org.example.entity.Sale;
import org.example.util.DaoUtils;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Set;

public class OrderService
{
	private OrderService()
	{
	}

	//TODO let user buy article multiple times with hashset
	//TODO handle making order without enough stock
	public static Sale makeOrder(Customer customer, Set<Article> articles)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.openSession();

		Sale sale = new Sale.Builder()
				.purchaseDate(LocalDate.now())
				.customer(customer)
				.status(Sale.SaleStatus.ONGOING)
				.articles(articles)
				.build();

		DaoUtils.getSaleDao().saveOrUpdate(sale);

		for (Article article : articles)
		{
			article.setStockQuantity(article.getStockQuantity() - 1);
			DaoUtils.getArticleDao().saveOrUpdate(article);
		}

		return sale;
	}
}
