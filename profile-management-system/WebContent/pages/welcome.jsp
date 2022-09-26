<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome</title>
<style type="text/css">
th {
	text-align: left;
}
</style>
</head>
<body>
	<div align="center">
		Welcome :${sessionUser['name']}
		<h3>info</h3>
		<table>
			<tr>
				<th>Name</th>
				<th>Phone Number</th>
				<th>User Name</th>
			</tr>
			<tr>
				<td>${sessionUser['name']}</td>
				<td>${sessionUser["phone"]}</td>
				<td>${sessionUser["user_name"]}</td>
			</tr>
		</table>
		Photo <img src="images/${sessionUser['photo']}" height="80" width="60">
		<a href="updatecontroller"> <input type="button" value="Update" /></a>
		<a href="deletecontroller"> <input type="button"
			value="DeleteAccount" /></a> <a href="logoutcontroller"> <input
			type="button" value="Logout" /></a>
		<h4>
			<c:out value="${msg}"></c:out>
		</h4>
	</div>
</body>
</html>