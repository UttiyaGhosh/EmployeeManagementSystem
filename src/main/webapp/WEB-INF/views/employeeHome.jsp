<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>EMS - Login</title>
<style>
body {
	background-color: #F0F2F5;
}

table {
	margin-top: 20px;
	background-color: white;
	padding: 5px;
}

.row {
	margin: 0 200px;
}

.column {
	float: left;
	padding: 5px;
}

.btn {
	background-color: green;
	color: white;
	border: 2px solid green;
	padding: 10px 20px;
	text-decoration: none;
	display: inline-block;
}

td {
	padding: 0 5px;
}
</style>
</head>
<body>

	<a style="float: right;" href="logout">Logout</a>
	<h1 style="text-align: center;">Employee Dashboard</h1>
	
	<div class="row">
		<div style="float: left;">
			<table>
				<thead>
					<tr>
						<th colspan="2"><h2>Employee Details</h2></th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td>Employee ID:</td>
						<td><c:out value="${employee.empid}" /></td>
					</tr>

					<tr>
						<td>First Name:</td>
						<td><c:out value="${employee.firstName}" /></td>
					</tr>

					<tr>
						<td>Last Name:</td>
						<td><c:out value="${employee.lastName}" /></td>
					</tr>

					<tr>
						<td>Date of Birth:
						</th>
						<td><c:out value="${employee.dob}" /></td>
					</tr>

					<tr>
						<td>Email:</td>
						<td><c:out value="${employee.email}" /></td>
					</tr>

					<tr>
						<td>Dept:</td>
						<td><c:out value="${employee.department.departmentName}" /></td>
					</tr>


					<tr>
						<th colspan="2"><a class="btn"
							href="alterEmployee?id=<c:out value='${employee.empid}'/>&act=edit">Edit
								details</a></th>
					</tr>


				</tbody>
			</table>
		</div>

		<div style="float: right;">
			<table border="0" cellspacing=5px>

				<tr>
					<th colspan="7"><h2>List of Regulations</h2></th>
				</tr>

				<tr>
					<th>RL ID</th>
					<th>RL Type</th>
					<th>Description</th>
					<th>Creation Date</th>
					<th></th>
				</tr>

				<c:forEach var="compliance" items="${complianceList}">

					<tr>
						<td><c:out value="${compliance.complianceId}" /></td>
						<td><c:out value="${compliance.rlType}" /></td>
						<td><c:out value="${compliance.details}" /></td>
						<td style="width: 100px;"><c:out
								value="${compliance.createDate}" /></td>
						<td><a
							href="alterStatusReport?cid=<c:out value='${compliance.complianceId}'/>&eid=<c:out value='${employee.empid}'/>">View/Edit
								Comment</a></td>

					</tr>

				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>