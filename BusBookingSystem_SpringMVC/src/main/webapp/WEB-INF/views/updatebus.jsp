<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
    rel="stylesheet">
      <link href="${pageContext.request.contextPath}/resources/Spring.css"
    rel="stylesheet"> 

</head>
<body>
<%@ include file="adminheader.jsp" %>
<div class="col-md-4 offset-4 mt-4 card">
    <div class="card-body">
    <h1>Update Bus</h1>
    <div class = "form-group">
 <form action="./updatebus" method="post">
 <label name = "busId">Bus Id</label>
<input class="form-control" type="text" name="busId"/><br>
<label name = "busName">Bus Name</label>
<input class="form-control" type="text" name="busName"/><br>
<label name = "source">Source</label>
<input class="form-control" type = "text" name = "source"><br>
<label name = "destination">Destination</label>
<input class="form-control" type = "text" name = "destination"><br>
<label name = "price">Price</label>
<input class="form-control" type = "text" name = "price"><br>
<label name = "TotalSeats">Contact</label>
<input class="form-control" type = "text" name = "totalSeats"><br>
<input class="form-control" type = "submit" value="Update">
</form>
</div>
</div>
</div>
</body>
</html>