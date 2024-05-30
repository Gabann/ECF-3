package org.example.entity;

import org.example.service.OrderService;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article
{
	String description;
	ClothesType clothesType;
	double length;
	double price;
	int stockQuantity;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	public Article()
	{
	}


	private Article(Builder builder)
	{
		description = builder.description;
		clothesType = builder.clothesType;
		length = builder.length;
		price = builder.price;
		stockQuantity = builder.stockQuantity;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setClothesType(ClothesType clothesType)
	{
		this.clothesType = clothesType;
	}

	public void setLength(double length)
	{
		this.length = length;
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
		OrderService.makeOrder(customer, Set.of(this));
	}

	public enum ClothesType
	{
		MALE,
		FEMALE,
		CHILDREN
	}

	public static final class Builder
	{
		private String description;
		private ClothesType clothesType;
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

		public Builder clothesType(ClothesType val)
		{
			clothesType = val;
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

		public Article build()
		{
			return new Article(this);
		}
	}
}
