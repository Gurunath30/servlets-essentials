<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
<style type="text/css">
h4 {
	color: red;
}
</style>
</head>
<body>
<div align="center">
		<form action="logincontroller" method="post" id="form1">
			<table>
				<tr>
					<td><label for="username">UserId:</label></td>
					<td><input id="username" maxlength="50" name="user_name"
						type="text" required/></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><input id="password" maxlength="50" name="password"
						type="password" required /></td>
			</table>
		</form>
		<table>
			<tr>
				<td><button type="submit" form="form1" value="Submit">login</button></td>
			</tr>
		</table>
		<h4>
			<c:out value="${msg}"></c:out>
		</h4>
	</div>
</body>
</html>