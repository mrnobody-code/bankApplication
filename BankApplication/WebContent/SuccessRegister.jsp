<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bank1.css">
<title>Registered</title>
</head>
<body>
	<div align="center" class="main">
		<h1>Your Account Registered Successfully..</h1>
		<h3>Thank You For Being Part of Us..</h3><br>
		<a href="Register.html"><button class="btn">click here to re-register</button></a><br><br>
		<h3>Login Through Below Details</h3><br>
		<% 
			session = request.getSession();
			out.println("<h3>Your User Id</h3>");
			out.println(session.getAttribute("email"));
		%>
		<br><br>
		<a href="Login.html"><button class="btn">click here to login</button></a>
	</div>
</body>
</html>