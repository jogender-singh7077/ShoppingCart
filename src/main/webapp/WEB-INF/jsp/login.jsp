<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	
	<script>	
</script>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
    	table, th, td {
    	padding: 2px;
		}
		th {
  		text-align: centre;
		}
    </style>
</head>
<div align="center">
<form:form action="/shoppingcart/userLogin" method="post" modelAttribute="userDetails">
			    	<table>   	    	
			    	<tr><td><form:label path="name"><c:out value="Username" /></form:label></td>
						<td><form:input path="name" type="text" placeholder="username" /></td></tr>
							
					<tr><td><form:label path="password"><c:out value="Password" /></form:label></td>
						<td><form:input path="password" type="text" placeholder="password" /></td></tr>
						
			    	</table>
			    	<div align="center"><input type="submit" value="Login"></div>
			    	<div align="center"><c:out value="${message }"/></div>
			    </form:form>
			    </div>
</html>			    