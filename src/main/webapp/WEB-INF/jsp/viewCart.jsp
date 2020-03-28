<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
	$( document ).ready(function() {
	$("input[name = 'removeFromCart']").click(function(e) {
		  e.preventDefault();		  
		  $("#hideRow"+this.id).hide();
		  var removeItemId = "calTotal"+this.id;
		  var removeItemAmount = document.getElementById(removeItemId).textContent;
		  var total = document.getElementById("cartTotal").textContent ;
		  var amountToBePaid = total - removeItemAmount;
		  $("#cartTotal").text(amountToBePaid);		 
		  $.ajax({
		    type:'PUT',
		    contentType: 'application/json',
		    data:this.id,
		    url:'/shoppingcart/removeFromCart',
		    success:function(data) {
		    	$("#responseDiv").text(data);
		    	$("#responseDiv").show().delay(2000).fadeOut();
		       }
		  });
		});
	
	$("input[name = 'updateCart']").click(function(e) {
		  e.preventDefault();	  
		  var updateItemId = this.id.slice(10, this.id.length);
		  var input = document.getElementById('quantity'+updateItemId);
		  input.setAttribute('value', input.value);
		  var itemQuantity = input.value;
		  
		  if(itemQuantity == 0 ){
			  $("#hideRow"+updateItemId).hide();
			
		  }
		  var itemId = "calTotal"+updateItemId;
		  var itemAmount = document.getElementById(itemId).textContent;
		  var initialQuantity = document.getElementById('quantity'+updateItemId).getAttribute("placeholder");
		  var itemTotal = itemQuantity * (itemAmount/initialQuantity);
		  		  
		  var jsonData = {
				  productId:updateItemId,
				  quantity:itemQuantity
				  }		 
		  $.ajax({
		    type:'PUT',
		    contentType: 'application/json',
		    data:JSON.stringify(jsonData),
		    url:'/shoppingcart/updateCart',
		    success:function(data) {
		    	document.getElementById(itemId).innerHTML=itemTotal;
		    	$("#cartTotal").text(data);	
		    	$("#responseDiv").text("Cart Updated Successfully");
		    	$("#responseDiv").show().delay(2000).fadeOut();
		       }
		  });
		});
	
	$("input[name = 'removeAll']").click(function(e) {
		  e.preventDefault();
		  $.ajax({
		    type:'DELETE',
		    contentType: 'application/json',
		    data:this.id,
		    url:'/shoppingcart/removeAllItems',
		    success:function(data) {
		    	$('.hideClass').hide();
				$("#cartTotal").text(0.0);
		    	$("#responseDiv").text(data);
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
		}
		.hideClass{
		}
    </style>
</head>
<body>
	<div align="center">
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
	</div>
	
	<div align="center">
	<c:choose>
	  <c:when test="${not empty cartItems}">
		<table class="hideClass">
				<caption>Cart Items</caption>
				
				<tr><th><c:out value="Product Name" /></th>
				    <th><c:out value="Quantity" /></th> 
				    <th><c:out value="Total" /></th>
				    <th colspan="2"><c:out value="Action" /></th></tr>
				    
		        <c:forEach items="${cartItems}" var="item">
		        
				    <tr id="hideRow${item.productId}"><td><c:out value="${item.productName}" /></td>
				        <td><input id="quantity${item.productId}" type="number" placeholder="${item.quantity}" min="0"/></td>
			
				        <td id="calTotal${item.productId}"><c:out value="${item.total}" /></td>
				        <form:form action="" method="put" >
				        <td><input id="${item.productId}" name="removeFromCart" type="submit" value="Remove"></td>
				        </form:form>
				        <form:form action="" method="put" >
				        <td><input id="updateItem${item.productId}" name="updateCart" type="submit" value="Update"></td>
				        </form:form>
				        </tr>
				</c:forEach>
		</table>
		<form:form action="" method="delete" >
			<div class="hideClass" align="center"><input id="" name="removeAll" type="submit" value="Remove All"></div>
		</form:form>
		</c:when>
		<c:otherwise>
			<div align="center">Cart Does Not Have Any Items At The Movement</div>
		</c:otherwise>
	</c:choose>	
	</div>
	<div align="center"><c:out value="Total Amount" /> <div align="center" id="cartTotal">${cartTotal}</div></div>
	<div align="center" id="responseDiv"></div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>