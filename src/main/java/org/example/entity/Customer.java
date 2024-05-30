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

	@Override
	public String toString()
	{
		return "Customer{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", id=" + id +
				'}';
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public static final class Builder
	{
		private final String email;
		private String firstName;
		private String lastName;

		public Builder(String email)
		{
			this.email = email;
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

		public Customer build()
		{
			return new Customer(this);
		}
	}
}
