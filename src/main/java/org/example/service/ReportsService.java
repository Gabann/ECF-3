package org.example.service;

import org.example.dao.SaleDao;
import org.example.entity.Product;
import org.example.entity.Sale;
import org.example.util.DaoUtils;

import java.util.List;

public final class ReportsService
{
	private ReportsService()
	{
	}

	public static String getStockReport()
	{
		List<Product> productList = DaoUtils.getProductDao().getAll();


		StringBuilder builder = new StringBuilder();
		for (Product product : productList)
		{
			builder.append(product).append(". Stock: ").append(product.getStockQuantity());
			builder.append("\n");
		}

		return builder.toString();
	}

	public static String getSalesReport()
	{
		StringBuilder builder = new StringBuilder();
		List<Sale> saleList = DaoUtils.getSaleDao().getAll();
		SaleDao saleDao = DaoUtils.getSaleDao();

		builder.append("Number of sales: ").append(saleDao.getSalesCount());
		builder.append("\n");

		builder.append("Total revenues: ").append(saleDao.getTotalRevenues());
		builder.append("\n");

		return builder.toString();
	}

	public static String getProductsPerformance()
	{
		StringBuilder builder = new StringBuilder();
		List<Product> productList = DaoUtils.getProductDao().getAll();
		SaleDao saleDao = DaoUtils.getSaleDao();

		for (Product product : productList)
		{
			builder.append(product);
			builder.append("\n");

			builder.append("Sold count: ").append(saleDao.getSoldCountById(product.getId()));
			builder.append("\n");

			builder.append("Total sold revenues: ").append(saleDao.getTotalSaleRevenuesById(product.getId()));
			builder.append("\n");
			builder.append("\n");
		}

		return builder.toString();
	}
}
