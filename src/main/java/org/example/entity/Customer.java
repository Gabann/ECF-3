package org.example.entity;

import javax.persistence.*;

@Entity
public class Customer
{
	String firstName;
	String lastName;
	String email;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	public Customer()
	{
	}

	private Customer(Builder builder)
	{
		firstName = builder.firstName;
		lastName = builder.lastName;
		email = builder.email;
	}


	public static final class Builder
	{
		private String firstName;
		private String lastName;
		private String email;

		public Builder()
		{
		}

		public Builder firstName(String val)
		{
			firstName = val;
			return this;
		}

		public Builder lastName(String val)
		{
			lastName = val;
			return this;
		}

		public Builder email(String val)
		{
			email = val;
			return this;
		}

		public Customer build()
		{
			return new Customer(this);
		}
	}
}
