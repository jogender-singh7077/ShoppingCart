<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
$( document ).ready(function() {
$("input[name = 'addToCart']").click(function(e) {
	  $.ajax({
	    type:'GET',	    
	    url:'/shoppingcart/addToCart/'+this.id,
	    success:function(data) {
	    	$("#responseDiv").text(data);
	    	$("#responseDiv").show().delay(2000).fadeOut();
	       }
	  });
	});
  });	
</script>
	
    <meta charset="UTF-8">
    <title>Products</title>
    <style>
    	table, th, td {
    	padding: 2px;
  		border: 2px solid black;
		}
		th {
  		text-align: centre;
		}td { white-space:pre }
    </style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
	</div> 
	
	<div align="center">
		<table>
				<caption>Product List</caption>
				
				<tr><th><c:out value="Product" /></th>
				    <th><c:out value="Price" /></th> 
				    <th><c:out value="Add" /></th></tr>
				    
		        <c:forEach items="${products}" var="product">
		        
				    <tr><td><c:out value="${product.productDetails}" /></td>
				        <td><c:out value="${product.price}" /></td>
				        
				        <td><input id="${product.productId}" name="addToCart" type="button" value="Add To Cart">
				        </td></tr>
				        
				</c:forEach>
		</table>
	</div>
	<div align="center" id="responseDiv" style="display:none;"></div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	
</body>
</html>