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
	padding: 4px;
}

.row {
	margin-left: 4px;
}

.column {
	float: left;
	padding: 4px;
}

.btn {
	background-color: green;
	color: white;
	border: 2px solid green;
	padding: 5px 5px;
	text-decoration: none;
	display: inline-block;
}

th {
	width: 50px;
	overflow: hidden;
}

td {
	text-align: center;
	width: 40px;
	overflow: hidden;
	padding: 0 5px;
}
</style>
</head>
<body>

	<a style="float: right;" href="logout">Logout</a>
	<h1 style="text-align: center;">Admin Dashboard</h1>

	<div class="row">
		<div class="column">
			<table>
				<thead>
					<tr>
						<th colspan="6"><h2>List of Employees</h2></th>
						<th colspan="2"><a class="btn" href="alterEmployee?act=add">Add
								Employee</a></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Employee ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Date of Birth</th>
						<th>Email</th>
						<th>Dept</th>
						<th colspan="2">Action</th>
					</tr>

					<c:forEach var="employee" items="${employeeList}">
						<tr>
							<td><c:out value="${employee.empid}" /></td>
							<td><c:out value="${employee.firstName}" /></td>
							<td><c:out value="${employee.lastName}" /></td>
							<td style="width: 100px;"><c:out value="${employee.dob}" /></td>
							<td><c:out value="${employee.email}" /></td>
							<td><c:out value="${employee.department.departmentName}" /></td>
							<td><a
								href="alterEmployee?id=<c:out value='${employee.empid}'/>&act=edit">Edit</a></td>
							<td><a
								href="alterEmployee?id=<c:out value='${employee.empid}'/>&act=delete">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="column">
			<table border="0" cellspacing=5px>

				<tr>
					<th colspan="2"><h2>List of Departments</h2></th>
					<th colspan="2"><a class="btn" href="alterDepartment?act=add">Add
							Department</a></th>
				</tr>

				<tr>
					<th>Department ID</th>
					<th>Department Name</th>
					<th colspan="2">Action</th>
				</tr>

				<c:forEach var="department" items="${departmentList}">
					<tr>
						<td><c:out value="${department.departmentId}" /></td>
						<td><c:out value="${department.departmentName}" /></td>
						<td><a
							href="alterDepartment?id=<c:out value='${department.departmentId}'/>&act=edit">Edit</a></td>
						<td><a
							href="alterDepartment?id=<c:out value='${department.departmentId}'/>&act=delete">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>

		<div class="column">
			<table border="0" cellspacing=5px>

				<tr>
					<th colspan="6"><h2>List of Regulations</h2></th>
					<th colspan="2"><a class="btn" href="alterCompliance?act=add">Add
							Regulation</a></th>
				</tr>

				<tr>
					<th>RL ID</th>
					<th>RL Type</th>
					<th>Description</th>
					<th>Creation Date</th>
					<th>Dept</th>
					<th>Status</th>
					<th colspan="2">Action</th>
				</tr>

				<c:forEach var="compliance" items="${complianceList}">
					<tr>
						<td><c:out value="${compliance.complianceId}" /></td>
						<td><c:out value="${compliance.rlType}" /></td>
						<td><c:out value="${compliance.details}" /></td>
						<td style="width: 100px;"><c:out
								value="${compliance.createDate}" /></td>
						<td><c:out value="${compliance.department.departmentName}" /></td>
						<td><c:out value="${compliance.status}" /></td>
						<td><a
							href="alterCompliance?id=<c:out value='${compliance.complianceId}'/>&act=edit">Edit</a></td>
						<td><a
							href="alterCompliance?id=<c:out value='${compliance.complianceId}'/>&act=delete">Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>