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
    </style>
</head>
<body>	
	<div align="center">
		<%@ include file="/WEB-INF/jsp/productList.jsp" %>
	</div>
</body>
</html>