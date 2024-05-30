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

	public Long getTotalSoldById(Long id)
	{
		Session session = sessionFactory.openSession();
		Query<Long> query = session.createNamedQuery("Article.findTotalSoldById", Long.class);
		query.setParameter("id", id);
		Long result = query.getSingleResult();
		session.close();
		return result;
	}

	//TODO handle article that haven't been sold
	public double getTotalSoldAmountByArticleId(Long articleId)
	{
		Session session = sessionFactory.openSession();
		Query<Double> query = session.createNamedQuery("Sale.findTotalSoldAmountByArticleId", Double.class);
		query.setParameter("id", articleId);
		double result = query.getSingleResult();
		session.close();
		return result;
	}

	public String getArticlesPerformance()
	{
		StringBuilder builder = new StringBuilder();
		List<Article> articleList = DaoUtils.getArticleDao().getAll();

		for (Article article : articleList)
		{
			builder.append(article);
			builder.append("\n");

			builder.append("Sold count: ").append(getTotalSoldById(article.getId()));
			builder.append("\n");

			builder.append("Total sold amount: ").append(getTotalSoldAmountByArticleId(article.getId()));
			builder.append("\n");
			builder.append("\n");
		}

		return builder.toString();
	}
}
