package org.example;

import org.example.dao.AbstractDao;
import org.example.entity.Article;
import org.example.entity.Customer;
import org.example.entity.Sale;
import org.example.service.OrderService;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Set;

public class Main
{
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		var articleDao = new AbstractDao<>(sessionFactory, Article.class);
		var customerDao = new AbstractDao<>(sessionFactory, Customer.class);
		var saleDao = new AbstractDao<>(sessionFactory, Sale.class);

		Customer customer1 = new Customer.Builder().firstName("eda").lastName("clawthorne").email("a@a.com").build();

		customerDao.saveOrUpdate(customer1);

		Article article1 =
				new Article.Builder().clothesType(Article.ClothesType.MALE).description("t-shirt").price(15).length(100).stockQuantity(100)
						.build();
		articleDao.saveOrUpdate(article1);

		Article article2 =
				new Article.Builder().clothesType(Article.ClothesType.FEMALE).description("t-shirt").price(10).length(80).stockQuantity(200)
						.build();
		articleDao.saveOrUpdate(article2);

		article1.buy(customer1);

		OrderService.makeOrder(customer1, Set.of(article1, article2));

		Sale sale1 = saleDao.getById(2L);

		System.out.println(sale1.generateReceipt());

		sale1.setStatus(Sale.SaleStatus.DONE);
		saleDao.saveOrUpdate(sale1);
	}
}
