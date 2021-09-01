<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>EMS</title>
<style>
body {
	background-color: #F0F2F5;
}

table {
	border: 1px white;
	margin-top: 200px;
	margin-left: 500px;
	background-color: white;
	padding: 20px
}

.btn {
	width: 200px;
	height: 50px;
	background-color: #42B72A;
}
</style>
</head>
<body>
	<form action="login" method="post">
		<table border=0 cellspacing=5px>
			<tr>
				<th colspan=3>Employee Management System</th>
			</tr>
			<tr>
				<td colspan=3 align = "center"><input class=in =text name=userid
					placeholder="Enter your User ID"></td>
			</tr>
			<tr>
				<td colspan=3 align = "center" ><input class=in type=password name=password
					placeholder="Enter your Password"></td>
			</tr>
			<tr>
				<td>Select role:</td>
				<td><input type="radio" name="role" value="admin">  
					Admin</td>
				<td>  <input type="radio" name="role" value="user">  
					User
				</td>
			</tr>
			<tr>
				<td colspan=3 align = "center"><input type=submit value="Login" class="btn"></td>
			</tr>
			<tr>
				<td colspan=3 style="color: red;" align = "center">${message}</td>
			</tr>

		</table>
	</form>
</body>
</html>