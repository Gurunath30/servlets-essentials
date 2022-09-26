<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update form</title>
</head>
<body>
	<div align="center">
		<form action="updatecontroller" method="post"
			enctype="multipart/form-data" id="form1">
			<table>
				<tr>
					<td><label for="name">Name: </label></td>
					<td><input id="name" maxlength="50" name="name" type="text"
						value="${sessionUser['name']}" /></td>
				</tr>
				<tr>
					<td><label for="phone">Phone: </label></td>
					<td><input id="phone" maxlength="50" name="phone"
						type="number" value="${sessionUser['phone']}" /></td>
				</tr>
				<tr>
					<td><label for="username">UserId: </label></td>
					<td><input id="username" maxlength="50" name="user_name"
						type="text" value="${sessionUser['user_name']}" /></td>
				</tr>
				<tr>
					<td><label for="password">Password: </label></td>
					<td><input id="password" maxlength="50" name="password"
						type="password" value="${sessionUser['password']}" />
				</tr>
				<tr>
					<td><label>City: </label></td>
					<td><select name="city">
							<option>select</option>
							<option>Vijayawada</option>
							<option>Hyderabad</option>
							<option>Annanthapur</option>
							<option>warangal</option>
							<option>Bengaluru</option>
							<option>Khamam</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="photo">Photo: </label></td>
					<td><img src="images/${sessionUser['photo']}"
						width="60" height="80" /><input id="photo" type="file"
						name="photo" value="change"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>