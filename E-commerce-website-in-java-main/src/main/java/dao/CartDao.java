package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Product;

public class CartDao {

	
	public static boolean addToCart(int userId, int productId) {
		
		
		try
		{
			
			Connection connection=DbConnection.getConnection();
			
			String checkQuery="select * from cart Where user_id=? AND product_id=?";
			
			PreparedStatement statement=connection.prepareStatement(checkQuery);
			statement.setInt(1, userId);
			statement.setInt(1, productId);
			ResultSet set=statement.executeQuery();
			if(set.next())
			{
				String updateQuery="Update Cart SET qunatity=quantity+1 where user_id=? and product_id=?";
				PreparedStatement updatePsft=connection.prepareStatement(updateQuery);
				updatePsft.setInt(1, userId);
				updatePsft.setInt(2, productId);
				return updatePsft.executeUpdate()>0;
			}
			else
			{
				String insertQuery="insert into cart(user_id,product_id,qunatity) VALUES(?,?,?)";
				PreparedStatement insertpsft=connection.prepareStatement(insertQuery);
				insertpsft.setInt(1, userId);
				insertpsft.setInt(2, productId);
				return insertpsft.executeUpdate()>0;
				
				
			}
					
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
	
	
	
	//Fetch Cart items for user
	
	public static List<Cart> getCartItems(int userid) {
		
		
		List<Cart> carts=new ArrayList<>();
		try
		{
			Connection connection=DbConnection.getConnection();
			String query="SELECT c.id,p.id AS product_id, p.name,p.price,p.image,c.qunatity"
					+ "FROM cart c JOIN products p ON c.product_id=p.id where c.user_id=?";
			PreparedStatement psft=connection.prepareStatement(query);
			psft.setInt(1, userid);
			ResultSet set=psft.executeQuery();
			while(set.next())
			{
				Product product=new Product(
						set.getInt("product_id"), 
						set.getString("name"),
						set.getString(3),
						set.getInt("price"),
						set.getString("image"));
				
				Cart cart=new Cart(set.getInt("id"),
						userid, product, set.getInt("qunatity"));
				carts.add(cart);
				
				
				
			}
			// Remove itme from Cart
			//clear cart after checkout
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return carts;
		
		
		
		
		
		
	}



	public static boolean removeCartItem(int cartId) {
		// 
		try
		{
			Connection connection=DbConnection.getConnection();
			String query="DELETE FROM cart where id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, cartId);
			return statement.executeUpdate()>0;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return false;
	}



	public static boolean clearCart(int userId) {
	
		try
		{
			Connection connection=DbConnection.getConnection();
			String query="DELETE FROM cart where user_id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, userId);
			return statement.executeUpdate()>0;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	
	
	
	
	
	


}
