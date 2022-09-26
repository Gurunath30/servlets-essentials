<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of users</title>
<style type="text/css">
th {
	text-align: left;
}
</style>
</head>
<body>
	<div align="center">
		<h3>Users List</h3>
		<table>
			<tr>
				<th>Name</th>
				<th>Phone Number</th>
				<th>User Name</th>
				<th>Photo</th>
			</tr>
			<c:forEach var="map" items="${maps}">
				<tr>
					<td>${map['name']}</td>
					<td>${map["phone"]}</td>
					<td>${map["user_name"]}</td>
					<td><img src="data:image/jpg;base64, ${map['photo']}" width="30" height="40"/></td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.html"> <input type="button" value="back" /></a>
	</div>
</body>
</html>