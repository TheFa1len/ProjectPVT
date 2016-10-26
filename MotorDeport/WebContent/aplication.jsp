<%@ page contentType="text/html;charset=ISO-8859-1" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View all aplications</title>
</head>
<body>
	<div align="center">
<table border="1">


		<tr>
			<th><b>ID</b></th>
			<th><b>passengers</b></th>
			<th><b>cargo</b></th>
			<th><b>voyage id</b></th>
			
		</tr>
		<c:forEach var="r" items="${result_admin}">

			<tr>
				<td>${r.id}</td>
				<td>${r.numberOfPassengers}</td>
				<td>${r.cargo}</td>
				<td>${r.voyage_id}</td>
		</c:forEach>


	</table>

	<form action="ToCreateVoyage" method="post">
		<input type="submit" value="Create Voyage" />
	</form>
</div>
</body>