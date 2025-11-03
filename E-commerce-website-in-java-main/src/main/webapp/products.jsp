<%@page import="dao.ProductDao"%>
<%@ page import="java.util.List" %>

<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Products</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .container { width: 80%; margin: auto; padding: 20px; }
        .product { border: 1px solid #ddd; padding: 15px; margin: 10px; display: inline-block; width: 30%; }
        .button { padding: 8px 12px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; display: inline-block; margin-top: 10px; }
        .button:hover { background: #0056b3; }
        .checkout-btn { background: #28a745; }
        .checkout-btn:hover { background: #218838; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Available Products</h2>

        <%
            User loggedInUser = (User) session.getAttribute("user");
            List<Product> products = ProductDao.getAllProducts();
            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
        %>
            <div class="product">
                <img src="<%= product.getImage() %>" width="100" height="100"><br>
                <strong><%= product.getName() %></strong><br>
                Price: $<%= product.getPrice() %><br>

                <% if (loggedInUser != null) { %>
                    <a href="AddToCartServlet?productId=<%= product.getId() %>" class="button">Add to Cart</a>
                <% } else { %>
                    <a href="login.jsp" class="button">Login to Add to Cart</a>
                <% } %>
            </div>
        <%
                }
            } else {
        %>
            <p>No products available.</p>
        <%
            }
        %>

        <!-- Checkout Button (Only visible if user is logged in) -->
        <% if (loggedInUser != null) { %>
            <br><br>
            <a href="checkout.jsp" class="button checkout-btn">Proceed to Checkout</a>
        <% } %>

    </div>

</body>
</html>