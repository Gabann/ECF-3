package org.example.dao;

import org.example.entity.Customer;
import org.example.entity.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao extends GenericDao<Customer>
{
	public CustomerDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, Customer.class);
	}

	public List<Sale> getSalesByCustomer(Customer customer)
	{
		Session session = sessionFactory.openSession();
		Query<Sale> query = session.createNamedQuery("Sale.findByCustomer", Sale.class);
		query.setParameter("customer", customer);
		List<Sale> results = query.getResultList();
		session.close();
		return results;
	}

	public List<Sale> getSalesByCustomerId(Long customerId)
	{
		return getSalesByCustomer(getById(customerId));
	}
}
