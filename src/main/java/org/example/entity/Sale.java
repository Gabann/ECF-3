package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Sale.findByCustomer",
				query = "select s from Sale s where s.customer = :customer"),
		@NamedQuery(name = "Sale.findTotalSaleRevenues",
				query = "select sum(article.price) from Sale sale join sale.products article"),
		@NamedQuery(name = "Sale.findTotalSaleRevenuesByArticleId",
				query = "select sum(article.price) from Sale sale join sale.products article where article.id = :id"),
		@NamedQuery(name = "Sale.findAllSoldArticleWithQuantity",
				query = "select a, count(s) from Sale s left join s.products a group by a"),
		@NamedQuery(name = "Sale.findByPurchaseDateBetween",
				query = "select s from Sale s where s.purchaseDate between :purchaseDateStart and :purchaseDateEnd"),
		@NamedQuery(name = "Sale.findByCategory",
				query = "select s from Sale s join s.products a where a.clothesCategory = :clothesCategory"),
		@NamedQuery(name = "Sale.findNumberOfSales",
				query = "select count(s) from Sale s"),
		@NamedQuery(name = "Sale.findSalesCountById",
				query = "select count(a) from Sale s join s.products a where a.id = :id")
})
public class Sale
{
	LocalDate purchaseDate;

	SaleStatus status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Sale_products",
			joinColumns = @JoinColumn(name = "sale_id"),
			inverseJoinColumns = @JoinColumn(name = "products_id"))
	private List<Product> products = new ArrayList<>();

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
		setArticles(builder.products);
		setCustomer(builder.customer);
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}

	@Override
	public String toString()
	{
		return "Sale{" +
				"purchaseDate=" + purchaseDate +
				", status=" + status +
				", id=" + id +
				", articles=" + products +
				", customer=" + customer +
				'}';
	}

	public List<Product> getArticles()
	{
		return products;
	}

	public void setArticles(List<Product> products)
	{
		this.products = products;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public SaleStatus getStatus()
	{
		return status;
	}

	public void setStatus(SaleStatus status)
	{
		this.status = status;
	}

	public String generateReceipt()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Client: ").append(this.customer.firstName).append(" ").append(this.customer.lastName);
		builder.append("\n");

		double totalPrice = 0;
		for (Product product : products)
		{
			builder.append("Article: ").append(product.description).append(", Price: ").append(product.price);
			builder.append("\n");
			totalPrice += product.price;
		}
		builder.append("Total price:").append(totalPrice);

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
		private List<Product> products;
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

		public Builder articles(List<Product> val)
		{
			products = val;
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
