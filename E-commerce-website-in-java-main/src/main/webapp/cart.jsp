<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .container { width: 80%; margin: auto; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
        th { background: #f4f4f4; }
        .button { padding: 8px 12px; background: #dc3545; color: white; text-decoration: none; border-radius: 5px; }
        .button:hover { background: #c82333; }
        .checkout-btn { display: block; margin: 20px auto; padding: 10px 20px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; width: 150px; }
        .checkout-btn:hover { background: #218838; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Your Shopping Cart</h2>

        <%
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) {
        %>
            <p>Your cart is empty.</p>
            <a href="products.jsp">Continue Shopping</a>
        <%
            } else {
        %>

        <table>
            <tr>
                <th>Product</th>
                <th>Image</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <%
                double totalAmount = 0;
                for (CartItem item : cart) {
                    double subtotal = item.getProduct().getPrice() * item.getQuantity();
                    totalAmount += subtotal;
            %>
            <tr>
                <td><%= item.getProduct().getName() %></td>
                <td><img src="<%= request.getContextPath() + "/" + item.getProduct().getImage() %>" width="50" height="50"></td>
                <td>$<%= item.getProduct().getPrice() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= subtotal %></td>
                <td>
                    <a href="RemoveFromCartServlet?productId=<%= item.getProduct().getId() %>" class="button">Remove</a>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="4"><strong>Total Amount:</strong></td>
                <td><strong>$<%= totalAmount %></strong></td>
                <td></td>
            </tr>
        </table>

        <a href="checkout.jsp" class="checkout-btn">Proceed to Checkout</a>
        <a href="products.jsp">Continue Shopping</a>

        <%
            }
        %>

    </div>

</body>
</html>