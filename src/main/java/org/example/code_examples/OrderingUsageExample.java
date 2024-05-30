package org.example.code_examples;

import org.example.dao.CustomerDao;
import org.example.dao.ProductDao;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.service.OrderService;
import org.example.util.DaoUtils;

import java.util.List;

public class OrderingUsageExample
{
	public static void main(String[] args)
	{
		//Initial data
		ProductDao productDao = DaoUtils.getProductDao();
		CustomerDao customerDao = DaoUtils.getCustomerDao();
		Product product1 = new Product.Builder().clothesType(Product.ClothesCategory.MALE).description("t-shirt").price(15).length(100)
				.stockQuantity(20).build();
		productDao.saveOrUpdate(product1);
		Product product2 = new Product.Builder().clothesType(Product.ClothesCategory.MALE).description("product1").price(99).length(50)
				.stockQuantity(20).build();
		productDao.saveOrUpdate(product2);
		Customer customer1 = new Customer.Builder("a@a.com").firstName("firstName").lastName("lastName").build();
		customerDao.saveOrUpdate(customer1);

		//Buying products
		product1.buy(customer1);
		product2.buy(customer1);

		//Or

		OrderService.makeOrder(customer1, List.of(product1));
		OrderService.makeOrder(customer1, List.of(product1, product1, product1));
		OrderService.makeOrder(customer1, List.of(product1, product2, product2));
	}
}
