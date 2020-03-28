<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html lang="en">

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>Search</title>
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
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
	</div>
	
	<div align="center">
			    <form:form action="/shoppingcart/product/search" method="get" modelAttribute="productSearch">
			    	<table>
			    	<caption><c:out value="Enter Product Details"/></caption>
					<tr><td><form:label path="productId"><c:out value="Product Id" /></form:label></td>
						<td><form:input path="productId" type="number" placeholder="Starts With 100 to 104" /></td></tr>
					   	    	
			    	<tr><td><form:label path="productName"><c:out value="Product Name" /></form:label></td>
						<td><form:input path="productName" type="text" placeholder="Enter Product Name" /></td></tr>
							
					<tr><td><form:label path="category"><c:out value="Product Category"/></form:label></td>
						<td><form:select path="category" items="${category}" placeholder="Enter Product Category" /></td></tr>
						
			    	</table>
			    	<div align="center"><input type="submit" value="Search"></div>
			    </form:form>
	    </div>
    
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>