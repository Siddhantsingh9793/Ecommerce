package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dao.ProductDao;
import model.Cart;
import model.Product;
import model.User;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");//test
		if(user==null)
		{
			response.sendRedirect("login.jsp");
		}
		
		List<Cart> cartItems=CartDao.getCartItems(user.getId());
		session.setAttribute("cartItems", cartItems);
		response.sendRedirect("cart.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");//test
		if(user==null)
		{
			response.sendRedirect("login.jsp");
		}
		
		String action=request.getParameter("action");
		if("add".equals(action))
		{
			int productId=Integer.parseInt(request.getParameter("productId"));
			Product product=ProductDao.getProductByID(productId);
		
		if(product!=null)
		{
			boolean added=CartDao.addToCart(user.getId(), productId);
			if(added)
			{
				session.setAttribute("message", "Item added to cart");
			}
			else
			{
				session.setAttribute("message", "Failed to  add item");
			}
		}
		response.sendRedirect("product.jsp");
		}
		else if("remove".equals(action))
		{
			int cartId=Integer.parseInt(request.getParameter("cartId"));
			boolean removed=CartDao.removeCartItem(cartId);
			if(removed)
			{
				session.setAttribute("message", "Item removed from Cart");
			}
			response.sendRedirect("cart");
					
		}
		else if("clear".equals(action))
		{
			boolean removed=CartDao.clearCart(user.getId());
			session.setAttribute("message", "Cart Cleared");
		}
		
		
		
		
		doGet(request, response);
		
		
		
	}

}
