package org.example;

import org.example.entity.Article;
import org.example.entity.Customer;
import org.example.entity.Sale;
import org.example.service.OrderService;
import org.example.util.DaoUtils;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Set;

public class Main
{
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		var articleDao = DaoUtils.getArticleDao();
		var customerDao = DaoUtils.getCustomerDao();
		var saleDao = DaoUtils.getSaleDao();

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

		Article article3 =
				new Article.Builder().clothesType(Article.ClothesType.CHILDREN).description("t-shirt").price(999).length(20)
						.stockQuantity(10)
						.build();
		articleDao.saveOrUpdate(article3);

		article1.buy(customer1);
		article1.buy(customer1);
		article1.buy(customer1);
		article2.buy(customer1);
		article3.buy(customer1);

		OrderService.makeOrder(customer1, Set.of(article1, article2));

		Sale sale1 = saleDao.getById(2L);

//		System.out.println(sale1.generateReceipt());

		sale1.setStatus(Sale.SaleStatus.DONE);
		saleDao.saveOrUpdate(sale1);
//
//		List<Sale> customer1Sales = customerDao.getSalesByCustomer(customer1);
//
//		for (Sale sale : customer1Sales)
//		{
//			System.out.println(sale.generateReceipt());
//		}

//		System.out.println(saleDao.getTotalPrice());
//
//		System.out.println(saleDao.getAllSoldArticles());
//
//		System.out.println(ReportsService.generateStockReport());

//		System.out.println(articleDao.getTotalSoldById(1L));
//		System.out.println(articleDao.getTotalSoldAmountByArticleId(1L));
//
//		System.out.println(articleDao.getArticlesPerformance());

		System.out.println(saleDao.getAllSoldArticles());
	}
}
