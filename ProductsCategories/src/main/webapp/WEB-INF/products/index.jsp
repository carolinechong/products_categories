<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>Products and Categories</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<h1>Products and Categories</h1>
			<a href="/products/new">Create a Product</a> | <a href="/categories/new">Create a Category</a>
			
			<h3>Categories</h3>
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Category Name</th>
						<th>Product Count</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${categories}" var="category">
						<tr>
							<td><a href="/categories/${category.id}"><c:out value="${category.name}"/></a></td>
							<td><c:out value="${category.getProducts().size()}"></c:out></td>
						</tr>				
					</c:forEach>
				</tbody>		
			</table>
			
			<h3>Products</h3>
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Product Description</th>
						<th>Category Count</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<td><a href="/products/${product.id}"><c:out value="${product.name}"/></a></td>
							<td><c:out value="${product.description}"/></td>
							<td><c:out value="${product.getCategories().size()}"></c:out></td>
						</tr>				
					</c:forEach>
				</tbody>		
				
			</table>
		</div>
	</body>
</html>