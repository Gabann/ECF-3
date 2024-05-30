package org.example.code_examples;

import org.example.entity.Product;
import org.example.util.DaoUtils;

public class ProductUsageExample
{
	public static void main(String[] args)
	{
		var productDao = DaoUtils.getProductDao();

		//Create
		Product product1 =
				new Product.Builder().clothesType(Product.ClothesCategory.MALE).description("product 1")
						.price(15)
						.length(100)
						.stockQuantity(100)
						.build();

		productDao.saveOrUpdate(product1);

		//Read
		System.out.println(productDao.getById(1L));

		//Update
		Product productToEdit = productDao.getById(1L);
		productToEdit.setDescription("new description");
		productToEdit.setLength(30);

		productDao.saveOrUpdate(productToEdit);

		//Delete
		//productDao.deleteById(1L);

		// OR

		Product productToDelete = productDao.getById(1L);
		productDao.delete(productToDelete);
	}
}
