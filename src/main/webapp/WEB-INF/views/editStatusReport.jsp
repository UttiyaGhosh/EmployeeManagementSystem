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

.logoutLblPos {
	position: fixed;
	right: 10px;
	top: 5px;
}
</style>
</head>
<body>

	<a class="logoutLblPos" href="logout">Logout</a>
	<form action="saveStatusReport" method="post">
		<table border=0 cellspacing=5px>
			<tr>
				<th colspan="2">Compliance Details</th>
			</tr>
			<tr>
				<th>Regulation id:</th>
				<td><input type="text" name=complianceId
					value="${compliance.complianceId}" readonly="readonly"></td>
			</tr>

			<tr>
				<th>Regulation Type:</th>
				<td><input type="text" name=rlType value="${compliance.rlType}"
					readonly="readonly"></td>
			</tr>

			<tr>
				<th>Details:</th>
				<td><input type="text" name=details
					value="${compliance.details}" readonly="readonly"></td>
			</tr>


			<tr>
				<th>Creation Date:</th>
				<td><input type="date" name=createDate
					value="${compliance.createDate}" readonly="readonly"></td>
			</tr>

			<tr>
				<th>Department:</th>
				<td><input type="text" name=departmentName
					value="${compliance.department.departmentName}" readonly="readonly"></td>
			</tr>

			<tr>
				<th>Submission Date:</th>
				<td><input type="date" name=submissionDate
					value="${statusReport.createDate}"></td>
			</tr>

			<tr>
				<th>Comments:</th>
				<td><input type="text" name=comments
					value="${statusReport.comments}"></td>
			</tr>

			<tr>
				<td colspan=2 align="center"><input type=submit value="Submit"
					class="btn"></td>
			</tr>

			<tr>
				<td colspan=2 style="color: red;" align="center">${message}</td>
			</tr>

		</table>
		<input type="hidden" name=empid value="${empid}"> <input
			type="hidden" name=departmentId
			value="${compliance.department.departmentId}">
	</form>
</body>
</html>