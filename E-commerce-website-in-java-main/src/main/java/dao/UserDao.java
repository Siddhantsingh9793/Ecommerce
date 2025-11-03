package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {

	
	public static boolean registerUser(User user ) {
		
		try
		{
			
			Connection connection=DbConnection.getConnection();
			PreparedStatement psft=connection.prepareStatement("Insert into users(name,email,password) VALUES(?,?,?) ");
			psft.setString(1, user.getName());
			psft.setString(2, user.getEmail());
			psft.setString(3, user.getPassword());
			return psft.executeUpdate()>0;
			
			
			
			
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
public static User loginUser(String email, String password)
{
	
	try
	{
		Connection connection=DbConnection.getConnection();
		
		PreparedStatement psft=connection.prepareStatement("Select * from users where email=? AND password=?");
		psft.setString(1, email);
		psft.setString(2, password);
		ResultSet set=psft.executeQuery();
		if(set.next())
		{
			return new User( set.getString(2), set.getString(3), set.getString(4));
			
		}
		
		
		
	}
	
	catch (Exception e) {
		System.out.println(e);
	}
	return null;
}
	
	
	
	
	
	
	
	
	
	
	
	
}
