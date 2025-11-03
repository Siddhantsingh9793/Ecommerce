<%@ page import="model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Home - eCommerce</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .container { width: 80%; margin: auto; }
        .nav { background-color: #333; padding: 10px; }
        .nav a { color: white; margin: 10px; text-decoration: none; }
        .message { color: green; font-weight: bold; }
    </style>
</head>
<body>

    <div class="nav">
        <a href="index.jsp">Home</a>
        <a href="products.jsp">Products</a>
        <a href="cart.jsp">Cart</a>
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
        %>
            <a href="logout.jsp">Logout</a>
            <span style="color:white;">Welcome, <%= user.getName() %>!</span>
        <%
            } else {
        %>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        <%
            }
        %>
    </div>

    <div class="container">
        <h1>Welcome to Our eCommerce Store!</h1>
        <p>Find the best products at the best prices.</p>
        
        <% 
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p class="message"><%= message %></p>
        <%
            session.removeAttribute("message");
            }
        %>

        <a href="products.jsp">
            <button>Shop Now</button>
        </a>
    </div>

</body>
</html>
