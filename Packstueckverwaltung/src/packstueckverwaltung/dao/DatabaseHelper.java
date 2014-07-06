package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper
{
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String connectionString = "jdbc:sqlserver://\\SQLEXPRESS;databaseName=Armada";
	public static Connection instance = null;

	static
	{
		try
		{			
			Class.forName(driver);
			instance = DriverManager.getConnection(connectionString,"","");			
	
					
//			Context initCtx = new InitialContext();
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//			instance = (DataSource) envCtx.lookup("jdbc/TestDB");
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}	
}
