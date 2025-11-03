<%@ page session="true" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add Product</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .container { width: 50%; margin: auto; }
        .message { color: green; font-weight: bold; }
    </style>
</head>
<body>

    <div class="container">
        <h2>Add New Product</h2>

        <% 
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p class="message"><%= message %></p>
        <%
            session.removeAttribute("message");
            }
        %>

        <form action="AddProductServlet" method="post">
            <label>Product Name:</label><br>
            <input type="text" name="name" required><br><br>

            <label>Description:</label><br>
            <textarea name="description" required></textarea><br><br>

            <label>Price:</label><br>
            <input type="number" name="price" step="0.01" required><br><br>

            <label>Image URL:</label><br>
            <input type="text" name="image" required><br><br>

            <button type="submit">Add Product</button>
        </form>
    </div>

</body>
</html>