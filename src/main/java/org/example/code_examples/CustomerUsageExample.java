package org.example.code_examples;

import org.example.entity.Customer;
import org.example.util.DaoUtils;

public class CustomerUsageExample
{
	public static void main(String[] args)
	{
		var customerDao = DaoUtils.getCustomerDao();

		//Create
		Customer customer1 = new Customer.Builder("a@a.com")
				.firstName("firstName")
				.lastName("lastName")
				.build();

		customerDao.saveOrUpdate(customer1);

		//Read
		System.out.println(customerDao.getById(1L));

		//Update
		Customer customerToEdit = customerDao.getById(1L);
		customerToEdit.setFirstName("new first name");
		customerToEdit.setEmail("new email");

		customerDao.saveOrUpdate(customerToEdit);

		//Delete
		//customerDao.deleteById(1L);

		// OR

		Customer customerToDelete = customerDao.getById(1L);
		customerDao.delete(customerToDelete);
	}
}
