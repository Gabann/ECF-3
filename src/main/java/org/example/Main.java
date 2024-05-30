package org.example;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Sale;
import org.example.service.OrderService;
import org.example.service.ReportsService;
import org.example.util.DaoUtils;
import org.example.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class Main
{
	public static void main(String[] args)
	{
		var productDao = DaoUtils.getProductDao();
		var customerDao = DaoUtils.getCustomerDao();
		var saleDao = DaoUtils.getSaleDao();

		Customer customer1 = new Customer.Builder("a@a.com")
				.firstName("eda")
				.lastName("clawthorne")
				.build();

		customerDao.saveOrUpdate(customer1);

		Customer customer2 = new Customer.Builder("b@b.com")
				.firstName("Luz")
				.lastName("Noceda")
				.build();

		customerDao.saveOrUpdate(customer2);

		Product product1 =
				new Product.Builder().clothesType(Product.ClothesCategory.MALE)
						.description("t-shirt")
						.price(15)
						.length(100)
						.stockQuantity(20)
						.build();
		productDao.saveOrUpdate(product1);

		Product product2 =
				new Product.Builder().clothesType(Product.ClothesCategory.FEMALE)
						.description("pants")
						.price(10)
						.length(80)
						.stockQuantity(200)
						.build();
		productDao.saveOrUpdate(product2);

		Product product3 =
				new Product.Builder().clothesType(Product.ClothesCategory.CHILDREN)
						.description("coat")
						.price(999)
						.length(20)
						.stockQuantity(20)
						.build();
		productDao.saveOrUpdate(product3);

		product1.buy(customer1);
		product1.buy(customer1);
		product2.buy(customer1);
		product3.buy(customer1);

		Sale sale1 = OrderService.makeOrder(customer1, List.of(product1, product2, product3));
		OrderService.makeOrder(customer1, List.of(product3, product3, product3));
		OrderService.makeOrder(customer1, List.of(product2, product2, product3));
		System.out.println(sale1.generateReceipt());


		sale1.setStatus(Sale.SaleStatus.DONE);
		saleDao.saveOrUpdate(sale1);

		List<Sale> customer1Sales = customerDao.getSalesByCustomer(customer1);

		for (Sale sale : customer1Sales)
		{
			System.out.println(sale.generateReceipt());
		}

		System.out.println(saleDao.getTotalRevenues());
//
		System.out.println(saleDao.getAllSolProductsWithQuantity());

		System.out.println(saleDao.getSoldCountById(1L));
		System.out.println(saleDao.getTotalSaleRevenuesById(1L));

		System.out.println(saleDao.getByCategory(Product.ClothesCategory.MALE));

		System.out.println(saleDao.getBetweenDates(LocalDate.now().minusDays(10), LocalDate.now()));

		System.out.println(ReportsService.getStockReport());
		System.out.println(ReportsService.getProductsPerformance());
		System.out.println(ReportsService.getSalesReport());

		HibernateUtil.close();
	}
}
