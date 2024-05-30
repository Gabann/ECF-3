package org.example.entity;

import org.example.service.OrderService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Product
{
	String description;
	ClothesCategory clothesCategory;
	double length;
	double price;
	int stockQuantity;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	public Product()
	{
	}

	private Product(Builder builder)
	{
		description = builder.description;
		clothesCategory = builder.clothesCategory;
		length = builder.length;
		price = builder.price;
		stockQuantity = builder.stockQuantity;
	}

	public Long getId()
	{
		return id;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setClothesType(ClothesCategory clothesCategory)
	{
		this.clothesCategory = clothesCategory;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	@Override
	public String toString()
	{
		return "Product{" +
				"description='" + description + '\'' +
				", clothesType=" + clothesCategory +
				", length=" + length +
				", price=" + price +
				", stockQuantity=" + stockQuantity +
				", id=" + id +
				'}';
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getStockQuantity()
	{
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity)
	{
		this.stockQuantity = stockQuantity;
	}

	public void buy(Customer customer)
	{
		OrderService.makeOrder(customer, List.of(this));
	}

	public void buy(Customer customer, LocalDate purchaseDate)
	{
		OrderService.makeOrder(customer, List.of(this), purchaseDate);
	}

	public enum ClothesCategory
	{
		MALE,
		FEMALE,
		CHILDREN
	}

	public static final class Builder
	{
		private String description;
		private ClothesCategory clothesCategory;
		private double length;
		private double price;
		private int stockQuantity;

		public Builder()
		{
		}

		public Builder description(String val)
		{
			description = val;
			return this;
		}

		public Builder clothesType(ClothesCategory val)
		{
			clothesCategory = val;
			return this;
		}

		public Builder length(double val)
		{
			length = val;
			return this;
		}

		public Builder price(double val)
		{
			price = val;
			return this;
		}

		public Builder stockQuantity(int val)
		{
			stockQuantity = val;
			return this;
		}

		public Product build()
		{
			return new Product(this);
		}
	}
}
