package org.example.code_examples;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Sale;
import org.example.service.OrderService;
import org.example.service.ReportsService;
import org.example.util.DaoUtils;

import java.time.LocalDate;
import java.util.List;

public class ReportsUsageExample
{
	public static void main(String[] args)
	{
		var productDao = DaoUtils.getProductDao();
		var customerDao = DaoUtils.getCustomerDao();
		var saleDao = DaoUtils.getSaleDao();

		//Initial data
		Product product1 = new Product.Builder().clothesType(Product.ClothesCategory.MALE).description("t-shirt").price(15).length(100)
				.stockQuantity(20).build();
		productDao.saveOrUpdate(product1);
		Product product2 = new Product.Builder().clothesType(Product.ClothesCategory.FEMALE).description("product1").price(99).length(50)
				.stockQuantity(20).build();
		productDao.saveOrUpdate(product2);
		Customer customer1 = new Customer.Builder("a@a.com").firstName("firstName").lastName("lastName").build();
		customerDao.saveOrUpdate(customer1);

		product1.buy(customer1);
		product1.buy(customer1);
		product2.buy(customer1);
		Sale sale1 = OrderService.makeOrder(customer1, List.of(product1, product2));

		//Getting all sales for a customer
		customerDao.getSalesByCustomer(customer1).forEach(System.out::println);

		//Getting the sum of all sales revenues
		System.out.println(saleDao.getTotalRevenues());

		//Getting all products sold with their quantities
		System.out.println(saleDao.getAllSolProductsWithQuantity());

		//Getting the number of sold products by product id
		System.out.println(saleDao.getSoldCountById(1L));

		//Getting the total revenue of a product by product id
		System.out.println(saleDao.getTotalSaleRevenuesById(1L));

		//Getting all sales by product category
		System.out.println(saleDao.getByCategory(Product.ClothesCategory.MALE));

		//Getting all sales between two dates
		System.out.println(saleDao.getBetweenDates(LocalDate.now().minusDays(10), LocalDate.now()));

		//Getting all articles with their stock quantity
		System.out.println(ReportsService.getStockReport());

		//Getting the performance of all products
		System.out.println(ReportsService.getProductsPerformance());

		//Getting the sales report
		System.out.println(ReportsService.getSalesReport());
	}
}
