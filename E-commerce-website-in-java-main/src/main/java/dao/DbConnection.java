package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private static Connection connection;
	
	
	public static Connection getConnection() {
		
		try
		{
			
			if(connection==null)
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root","root");
			
			
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}
	
}
