package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
public class Sale
{
	LocalDate purchaseDate;
	SaleStatus status;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Sale_articles",
			joinColumns = @JoinColumn(name = "sale_id"),
			inverseJoinColumns = @JoinColumn(name = "articles_id"))
	private Set<Article> articles = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Sale()
	{
	}

	private Sale(Builder builder)
	{
		purchaseDate = builder.purchaseDate;
		status = builder.status;
		setArticles(builder.articles);
		setCustomer(builder.customer);
	}

	public Set<Article> getArticles()
	{
		return articles;
	}

	public void setArticles(Set<Article> articles)
	{
		this.articles = articles;
	}

	public void setStatus(SaleStatus status)
	{
		this.status = status;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public String generateReceipt()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Client: " + this.customer.firstName + " " + this.customer.lastName + "\n");
		double totalPrice = 0;
		for (Article article : articles)
		{
			builder.append("Article: " + article.description + ", Price: " + article.price + "\n");
			totalPrice += article.price;
		}
		builder.append("Total price:" + totalPrice);

		return builder.toString();
	}

	public enum SaleStatus
	{
		DONE,
		CANCELLED,
		ONGOING
	}

	public static final class Builder
	{
		private LocalDate purchaseDate;
		private SaleStatus status;
		private Set<Article> articles;
		private Customer customer;

		public Builder()
		{
		}

		public Builder purchaseDate(LocalDate val)
		{
			purchaseDate = val;
			return this;
		}

		public Builder status(SaleStatus val)
		{
			status = val;
			return this;
		}

		public Builder articles(Set<Article> val)
		{
			articles = val;
			return this;
		}

		public Builder customer(Customer val)
		{
			customer = val;
			return this;
		}

		public Sale build()
		{
			return new Sale(this);
		}
	}
}
