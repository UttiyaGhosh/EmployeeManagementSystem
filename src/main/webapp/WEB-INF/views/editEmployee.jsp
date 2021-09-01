<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("userid") == null) {
		response.sendRedirect(request.getContextPath() + "/");
	}
%>

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

	<a style="float: right;" href="logout">Logout</a>
	<form action="saveEmployee" method="post">
		<table border=0 cellspacing=5px>
			<tr>
				<th colspan="2">Employee Details</th>
			</tr>
			<tr>
				<th>Employee id:</th>
				<td><input type="text" name=empid value="${employee.empid}"
					readonly="readonly"></td>
			</tr>

			<tr>
				<th>First Name:</th>
				<td><input type="text" name=firstName
					value="${employee.firstName}"></td>
			</tr>

			<tr>
				<th>Last Name:</th>
				<td><input type="text" name=lastName
					value="${employee.lastName}"></td>
			</tr>

			<tr>
				<th>Date of Birth:</th>
				<td><input type="date" name=dob value="${employee.dob}"></td>
			</tr>

			<tr>
				<th>Email:</th>
				<td><input type="text" name=email value="${employee.email}"></td>
			</tr>

			<tr>
				<th>Password:</th>
				<td><input type="password" name=password
					value="${loginMaster.password}"></td>
			</tr>

			<tr>
				<th>Department:</th>
				<td><select name="departmentid">
						<c:forEach var="department" items="${departmentList}">
							<option value="${department.departmentId}">${department.departmentName}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td colspan=2 align="center"><input type=submit value="Submit"
					class="btn"></td>
			</tr>

			<tr>
				<td colspan=2 style="color: red;" align="center">${message}</td>
			</tr>

		</table>
	</form>
</body>
</html>