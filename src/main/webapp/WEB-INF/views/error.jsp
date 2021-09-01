<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Oops!</h1>
		<h2>${status.value}</h2>
		<h2>${code}: ${message}</h2>
		<h3>${Exception}</h3>
		<a href="logout">Click Here</a> to go to login page.
	</center>
</body>
</html>