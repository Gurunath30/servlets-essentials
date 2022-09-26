<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("username") != null) {
		response.sendRedirect("dashboard");
	}
	%>
	<form action="Login-page" method="post">
		User Name : <input type="text" name="user" required><br>
		Password : <input type="password" name="pwd" required><br>
		<input type="submit" value="login"><br>
	</form>
</body>
</html>
