package org.example.entity;

import javax.persistence.*;

@Entity
public class Test
{
	String name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	public Test()
	{
	}

	public Test(Builder builder)
	{
		name = builder.name;
	}

	@Override
	public String toString()
	{
		return "Test{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}

	public static final class Builder
	{
		private String name;

		public Builder()
		{
		}

		public Builder name(String val)
		{
			name = val;
			return this;
		}

		public Test build()
		{
			return new Test(this);
		}
	}
}
