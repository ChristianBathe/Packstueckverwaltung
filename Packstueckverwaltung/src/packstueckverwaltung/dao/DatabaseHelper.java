package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper
{
	static String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static String connectionString = "jdbc:microsoft:sqlserver://dealsad11wvm01.disversmold.net\\SQLEXPRESS;databaseName=Armada";
	public static Connection instance = null;

	static
	{
		try
		{			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(connectionString, "sa", "Dba2003!");			
	
					
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
