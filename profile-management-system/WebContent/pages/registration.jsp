<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>registration</title>
<style type="text/css">
h4 {
	color: blue;
}
</style>
</head>
<h2 ALIGN="CENTER">Register Here</h2>
<body>
	<form action="registercontroller" method="post"
		enctype="multipart/form-data" id="form1">
		<div align="center">
			<table>

				<tr>
					<td><label for="name">Name: </label></td>
					<td><input id="name" maxlength="50" name="name" type="text" required/></td>
				</tr>

				<tr>
					<td><label for="phone">Phone: </label></td>
					<td><input id="phone" maxlength="50" name="phone"
						type="number" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><input type="radio" name="gender" value="Male">
						Male <input type="radio" name="gender" value="Female">
						Female <input type="radio" name="gender" value="Others">
						Others</td>
				</tr>
				<tr>
					<td><label for="username">UserId: </label></td>
					<td><input id="username" maxlength="50" name="user_name"
						type="text" required/></td>
				</tr>

				<tr>
					<td><label for="password">Password: </label></td>
					<td><input id="password" maxlength="50" name="password"
						type="password" required/></td>
				</tr>
				<tr>
					<td><label>City: </label></td> 
					<td><select name="city">
							<option>-----select-----</option>
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
					<td><input id="photo" type="file" name="photo" accept=" /jpeg, /png,/jpg">
				</tr>
				<tr>
					<td align="right"><button type="submit" form="form1"
							value="Submit"> Submit </button></td>
				</tr>
				</tbody>
			</table>
			<h4>
				<c:out value="${msg}"></c:out>
			</h4>
		</div>
	</form>
</body>
</html>