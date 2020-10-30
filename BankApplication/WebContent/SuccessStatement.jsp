<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bank1.css">
<title>statement</title>
</head>
<body>
	<div align="center" class="main">
		<h1>Your Statement Details..</h1><br><br>
		<%
			session = request.getSession();
			out.println("<h2>");
			out.println(session.getAttribute("sal"));
			out.println("<br>");
			out.println(session.getAttribute("ral"));
			out.println("<br>");
			out.println(session.getAttribute("al"));
			out.println("</h2>");
			out.println("<br>");
		%>
		<a href="MainApp.html"><button class="btn">back</button></a>
	</div>
</body>
</html>