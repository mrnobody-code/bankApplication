<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bank1.css">
<title>loan pass</title>
</head>
<body>
	<div align="center" class="main">
		<h1>Loan Apply Successfully</h1><br>
		<%
			session = request.getSession();
			out.println("<h3>");
			out.println("Dear, "+session.getAttribute("name")+" your loan will be pass withine few days.");
			out.println("<br>");
			out.println("our executive will be contact you soon on below mentioned email");
			out.println("<br>");
			out.println(session.getAttribute("email"));
			out.println("</h3>");
			out.println("<br>");
		%>
		<a href="MainApp.html"><button class="btn">back</button></a>
	</div>
</body>
</html>