package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import model.CartItem;
import model.Product;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId=Integer.parseInt(request.getParameter("productId"));
		Product product=ProductDao.getProductByID(productId);
		if(product!=null)
		{
			HttpSession session=request.getSession();
			List<CartItem> cart=(List<CartItem>) session.getAttribute("cart");
			if(cart==null)
			{
				cart=new ArrayList<>();
				session.setAttribute("cart", cart);
			}
			boolean itemExists=false;
			for(CartItem item:cart)
			{
				if(item.getProduct().getId()==productId)
				{
					item.setQuantity(item.getQuantity()+1);
					itemExists=true;
					break;
				}
			}
			if(!itemExists)
			{
				cart.add(new CartItem(product, 1));
			}
			
					
		}
		
		
		response.sendRedirect("products.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
