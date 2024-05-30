package org.example;

import org.example.entity.Article;
import org.example.util.DaoUtils;

public class ArticleUsageExample
{
	public static void main(String[] args)
	{
		var articleDao = DaoUtils.getArticleDao();

		//Create
		
		Article article1 =
				new Article.Builder().clothesType(Article.ClothesType.MALE).description("article 1")
						.price(15)
						.length(100)
						.stockQuantity(100)
						.build();

		articleDao.saveOrUpdate(article1);

		//Read

		System.out.println(articleDao.getById(1L));

		//Update

		Article articleToEdit = articleDao.getById(1L);
		articleToEdit.setDescription("new description");
		articleToEdit.setLength(30);

		articleDao.saveOrUpdate(articleToEdit);

		//Delete

		//articleDao.deleteById(1L);
		//or
		Article articleToDelete = articleDao.getById(1L);
		articleDao.delete(articleToDelete);
	}
}
