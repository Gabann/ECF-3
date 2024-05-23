package org.example;

import org.example.entity.Test;
import org.example.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main
{
	public static void main(String[] args)
	{
		EntityManager entityManager = Persistence.createEntityManagerFactory("ecf3").createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		GenericRepository<Test> testRepository = new GenericRepository<>(entityManager, Test.class);

		transaction.begin();

		Test test = new Test.Builder().name("test").build();
		entityManager.persist(test);
		System.out.println(testRepository.findAll());

		transaction.commit();
	}
}
