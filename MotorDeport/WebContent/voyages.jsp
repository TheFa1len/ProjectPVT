<%@ page contentType="text/html;charset=ISO-8859-1" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Driver  <c:out value="${name}" /></title>
</head>
<body>
	Your car is:
	<c:out value="${car_driver}" />
	<form action="UpdateStatement" method="post">
		<p>Update statement</p>
		<p>
			<select name="state">
				<option disabled>Choose statement</option>
				<c:forEach items="${statements}" var="p">
					<option value="${p.id}">${p.statement}</option>
				</c:forEach>
			</select>
		</p>
		<input type="submit" value="Update" />
	</form>
	<table border="1">


		<tr>
			<th><b>ID</b></th>
			<th><b>Start Date</b></th>
			<th><b>Duration</b></th>
			<th><b>statement</b></th>

		</tr>
		<c:forEach items="${result_driver}" var="res">

			<tr>
				<td>${res.id}</td>
				<td>${res.startDate}</td>
				<td>${res.duration}</td>
				<td>${res.statement}</td>
		</c:forEach>
	</table>
	<form action="ToUpdateVoyage" method="post">

		<input type="submit" value="Update" />

	</form>
</body>
</html>
