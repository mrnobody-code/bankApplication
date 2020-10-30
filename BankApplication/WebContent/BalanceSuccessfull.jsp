<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bank1.css">
<title>view balance</title>
</head>
<body>
	<div align="center" class="main">
		<h1>Your Current Balance Is</h1><br>
		<%
			session = request.getSession();
			out.println("<h3>");
			out.println(session.getAttribute("balance"));
			out.println("</h3>");
		%>
		<br>
		<a href="MainApp.html"><button class="btn">back</button></a>
	</div>
</body>
</html>