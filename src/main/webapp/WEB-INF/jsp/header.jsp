<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <style>
    	table, th, td {
    	padding: 2px;
  		border: 2px solid black;
		}
		th {
  		text-align: centre;
		}
		a{
    display: inline;
}
    </style>
</head>
<body>
	<a href="/shoppingcart/welcome">Home</a>
	<a href="/shoppingcart/viewcart">View Cart</a>
	<a href="/shoppingcart/search">Search</a>
	<a href="/shoppingcart/logout">Logout</a>
</body>
</html>