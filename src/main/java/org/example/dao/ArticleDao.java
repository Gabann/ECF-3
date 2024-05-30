package org.example.dao;

import org.example.entity.Article;
import org.example.util.DaoUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ArticleDao extends GenericDao<Article>
{
	public ArticleDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, Article.class);
	}

	public Long getSoldCountById(Long id)
	{
		Session session = sessionFactory.openSession();
		Query<Long> query = session.createNamedQuery("Article.findSoldCountById", Long.class);
		query.setParameter("id", id);
		System.out.println(query.getSingleResult());
		Long result = query.getSingleResult();
		session.close();
		return result;
	}

	public double getTotalSaleRevenueById(Long articleId)
	{
		Session session = sessionFactory.openSession();
		Query<Double> query = session.createNamedQuery("Sale.findTotalSaleRevenuesByArticleId", Double.class);
		query.setParameter("id", articleId);
		Double result = query.getSingleResult();
		session.close();
		return result == null ? 0.0 : result;
	}
	
	public String getArticlesPerformance()
	{
		StringBuilder builder = new StringBuilder();
		List<Article> articleList = DaoUtils.getArticleDao().getAll();

		for (Article article : articleList)
		{
			builder.append(article);
			builder.append("\n");

			builder.append("Sold count: ").append(getSoldCountById(article.getId()));
			builder.append("\n");

			builder.append("Total sold revenues: ").append(getTotalSaleRevenueById(article.getId()));
			builder.append("\n");
			builder.append("\n");
		}

		return builder.toString();
	}
}
