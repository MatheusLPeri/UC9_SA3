package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection
{
	private static String dataBase = "jdbc:mysql://localhost:3306/shoes_happy";
	private static String user = "MatheusLP";
	private static String password = "matheusluizperi";
	private static Connection connection = null;

	public static Connection getConnection()
	{
		return connection;
	}

	static
	{
		Conect();
	}

	public DatabaseConnection()
	{
		Conect();
	}

	private static void Conect()
	{
		try
		{
			if(connection ==null)
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(dataBase,user,password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
