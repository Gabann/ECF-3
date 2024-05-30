package org.example;

import org.example.entity.Product;
import org.example.util.DaoUtils;

public class ProductUsageExample
{
	public static void main(String[] args)
	{
		var articleDao = DaoUtils.getArticleDao();

		//Create

		Product product1 =
				new Product.Builder().clothesType(Product.ClothesCategory.MALE).description("article 1")
						.price(15)
						.length(100)
						.stockQuantity(100)
						.build();

		articleDao.saveOrUpdate(product1);

		//Read

		System.out.println(articleDao.getById(1L));

		//Update

		Product productToEdit = articleDao.getById(1L);
		productToEdit.setDescription("new description");
		productToEdit.setLength(30);

		articleDao.saveOrUpdate(productToEdit);

		//Delete

		//articleDao.deleteById(1L);
		//or
		Product productToDelete = articleDao.getById(1L);
		articleDao.delete(productToDelete);
	}
}
