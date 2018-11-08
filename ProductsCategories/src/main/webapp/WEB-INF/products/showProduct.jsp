<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>Show Product by ID</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<div class="container">
		
			<a href="/">Home</a> | <a href="/products/new">Create a Product</a> | <a href="/categories/new">Create a Category</a>
			
			<h1><c:out value="${product.name}"/></h1>
			<p><c:out value="${product.description}"/></p>
			
			<h3>Categories</h3>
			<ul>
				<c:forEach items="${product.categories}" var="cat">
					<li><c:out value="${cat.name}"/></li>
				</c:forEach>
			</ul>
			
			<h3>Add a Category</h3>
			<form:form action="/products/${product.id}" method="POST" modelAttribute="cpObj">
			
				<div class="form-group">
					<!-- specific product that we're adding category to -->
					<form:hidden path="product" value="${product.id}"></form:hidden>
					
					<form:label path="category">Category:</form:label>
					<form:select path="category">
						<c:forEach items="${availableCategories}" var="cat">
							<form:option value="${cat.id}"> <c:out value="${cat.name}"/> </form:option>
						</c:forEach>
					</form:select>
				</div>
				
				<input type="submit" value="Add Category" class="btn btn-default">
			</form:form>
		</div>
	</body>
</html>