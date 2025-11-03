<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RegisterServlet" method="post">
Name <input type="text" name="name" required="required"> <br>
Email <input type="email" name="email" required="required"> <br>
password <input type="password" name="password" required="required"> <br>
<input type="submit" value="Register">
</form>
</body>
</html>