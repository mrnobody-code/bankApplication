<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bank1.css">
<title>otp page</title>
</head>
<body>
	<div align="center" class="main">
		<% 
			session = request.getSession();
			out.println("<h3>");
			out.println("Otp is send on your registered mobile number mentioned below :");
			out.println(session.getAttribute("phone"));
			out.println("</h3>");
		%>
		<form action="OtpValidation">
			<table>
				<tr>
					<td><input type="text" name="otp" placeholder="Enter OTP" required/></td>
				</tr>
			</table><br>
			<button type="submit" value="submit" class="btn">submit</button>
		</form>
	</div>
</body>
</html>