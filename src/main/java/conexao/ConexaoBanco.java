package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco
{
	private static String banco = "jdbc:mysql://localhost:3306/shoes_happy";
	private static String usuario = "MatheusLP";
	private static String senha = "matheusluizperi";
	private static Connection connection = null;

	public static Connection getConnection()
	{
		return connection;
	}

	static
	{
		Conectar();
	}

	public ConexaoBanco()
	{
		Conectar();
	}

	private static void Conectar()
	{
		try
		{
			if(connection ==null)
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(banco,usuario,senha);
				connection.setAutoCommit(false);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
