package org.example.service;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Sale;
import org.example.util.DaoUtils;

import java.time.LocalDate;
import java.util.List;

public final class OrderService
{
	private OrderService()
	{
	}

	//TODO let user buy a product with a specified quantity (maybe with hashmap?)
	public static Sale makeOrder(Customer customer, List<Product> products, LocalDate purchaseDate)
	{
		try
		{
			for (Product product : products)
			{
				if (product.getStockQuantity() < 1)
				{
					throw new RuntimeException("Product " + product + " does not have enough stock");
				}
			}

			Sale sale = new Sale.Builder()
					.purchaseDate(purchaseDate)
					.customer(customer)
					.status(Sale.SaleStatus.ONGOING)
					.products(products)
					.build();

			DaoUtils.getSaleDao().saveOrUpdate(sale);

			for (Product product : products)
			{
				product.setStockQuantity(product.getStockQuantity() - 1);
				DaoUtils.getProductDao().saveOrUpdate(product);

			}
			return sale;
		} catch (Exception e)
		{
			System.out.println("Something went wrong: " + e);
			return null;
		}
	}

	public static Sale makeOrder(Customer customer, List<Product> products)
	{
		return makeOrder(customer, products, LocalDate.now());
	}
}
