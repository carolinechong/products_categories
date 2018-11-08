<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
	<head>
		<title>New Category</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<div class="container">
			<h1>New Category</h1>
			<a href="/">Home</a> | <a href="/products/new">Create a Product</a>
			
			<form:form action="/categories/new" method="POST" modelAttribute="categoryObj">
				<div class="form-group">
					<form:label for="name" path="name">Name: 
					<form:input type="text" path="name" class="form-control" id="name"/>
					</form:label>
				</div>
					<input type="submit" value="Create" class="btn btn-default">
			</form:form>
			
			<% if(request.getAttribute("errors") != null){ %>
			<fieldset>
				<legend>Errors</legend>
				<c:forEach items="${errors}" var="error">
					<p><c:out value="${error.getDefaultMessage()}"/></p>
				</c:forEach>
			</fieldset>
			<% } %>
		</div>
	</body>
</html>