<%@page import="com.dev.bss.beans.Admin"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.dev.bss.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Ticket</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/Spring.css"
	rel="stylesheet">

</head>
<body>
	<%@ include file="adminheader.jsp"%>
	<%
		Admin user = (Admin) session.getAttribute("detailsadmin");
		if (user != null) {
			PrintWriter output = response.getWriter();
			output.println("<h1> Welcome " + user.getAdminId() + " </h1>");

		} else {
			response.sendRedirect("./adminlogin");
		}
	%>
	<div class="col-md-4 offset-4 mt-4 card">
		<div class="card-body">
			<div class="form-group">
				<h1>Search Buses</h1>
				<form action="./searchbus" method="post">
					<label name="source">Source</label> <input class="form-control"
						type="text" name="source" /><br> <label name="destination">Destination</label>
					<input class="form-control" type="text" name="destination" /><br>
					<input class="form-control" type="submit" value="Search Buses">
				</form>
			</div>
		</div>
	</div>

</body>
</html>