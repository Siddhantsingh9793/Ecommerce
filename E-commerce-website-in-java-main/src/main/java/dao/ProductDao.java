package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDao {

	
	public static List<Product> getAllProducts() {
		
		
		List<Product> products=new ArrayList<>();
		
		try
		{
			Connection connection=DbConnection.getConnection();
			String query="select * from products";
			PreparedStatement psft=connection.prepareStatement(query);
			ResultSet set=psft.executeQuery();
			while(set.next())
			{
				Product product=new Product(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getDouble(4),
						set.getString(5));
				products.add(product);// 
			}
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return products;
		
		
		
	}
	
	// this method fetch single product with ID
public static Product getProductByID(int pid) {
		
		
	Product product=null;
		
		try
		{
			Connection connection=DbConnection.getConnection();
			String query="select * from products where id=?";//15
			PreparedStatement psft=connection.prepareStatement(query);
			psft.setInt(1, pid);
			ResultSet set=psft.executeQuery();
			if(set.next())
			{
				 product=new Product(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getDouble(4),
						set.getString(5));
				 
			}
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return product;
			
	}
	
	
// Add a new product

public static boolean addProduct(Product product) {
	
	
	try
	{
		
		Connection connection=DbConnection.getConnection();
		String query="INSERT into products(name,description,price,image) VALUES(?,?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1, product.getName());
		statement.setString(2, product.getDescription());
		statement.setDouble(3, product.getPrice());
		statement.setString(4, product.getImage());
		
		return statement.executeUpdate()>0;
		
	}
	
	catch (Exception e) {
		System.out.println(e);
	}
	return false;
	
}
	


	
	
	
	
}
