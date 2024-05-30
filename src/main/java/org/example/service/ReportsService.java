package org.example.service;

import org.example.entity.Article;
import org.example.entity.Sale;
import org.example.util.DaoUtils;

import java.util.List;

public class ReportsService
{
	private ReportsService()
	{
	}

	public static String generateStockReport()
	{
		List<Article> articleList = DaoUtils.getArticleDao().getAll();


		StringBuilder builder = new StringBuilder();
		for (Article article : articleList)
		{
			builder.append(article).append(". Stock: ").append(article.getStockQuantity()).append("\n");
		}

		return builder.toString();
	}

	public static String generateSaleReport()
	{
		List<Sale> saleList = DaoUtils.getSaleDao().getAll();


		return "";
	}
}
