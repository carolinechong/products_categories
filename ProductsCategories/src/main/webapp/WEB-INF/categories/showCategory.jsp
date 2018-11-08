<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>Show Category by ID</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<div class="container">
		
			<a href="/">Home</a> | <a href="/products/new">Create a Product</a> | <a href="/categories/new">Create a Category</a>
			
			<h1><c:out value="${category.name}"/></h1>
			
			<h3>Products</h3>
			<ul>
				<c:forEach items="${category.products}" var="prod">
					<li><c:out value="${prod.name}"/></li>
				</c:forEach>
			</ul>
			
			<h3>Add a Product</h3>
			<form:form action="/categories/${category.id}" method="POST" modelAttribute="cpObj">
			
				<div class="form-group">
					<form:hidden path="category" value="${category.id}"></form:hidden>
					
					<form:label path="product">Product:</form:label>
					<form:select path="product">
						<c:forEach items="${availableProducts}" var="prod">
	        					<form:option value="${prod.id}"><c:out value="${prod.name}"/></form:option>
	   					</c:forEach>
					</form:select>
				</div>
				
				<input type="submit" value="Add Product" class="btn btn-default">
			</form:form>
		</div>
	</body>
</html>