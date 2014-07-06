package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper
{
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String connectionString = "jdbc:sqlserver://\\SQLEXPRESS;databaseName=Armada";
	private static Connection instance = null;

	public static Connection getInstance()
	{
		try
		{
			if (instance == null || instance.isClosed())
			{
				Class.forName(driver);
				instance = DriverManager.getConnection(connectionString, "","");
			}
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return instance;
	}
}
