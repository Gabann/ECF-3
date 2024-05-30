package org.example.util;

import io.github.cdimascio.dotenv.Dotenv;

public class Environment
{
	private static final Dotenv dotenv = Dotenv.load();

	private Environment()
	{
	}

	public static String getEnv(String key)
	{
		return dotenv.get(key);
	}
}
