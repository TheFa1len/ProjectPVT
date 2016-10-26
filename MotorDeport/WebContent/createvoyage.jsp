<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new voyage</title>
</head>
<body>


	<div align="center">
		You are logged as
		<c:out value="${name}" />
		<form action="CreateVoyage" method="post">
			<fieldset style="width: 400px">
				<div align="center">
					<p>
						<b>Form for new voyage</b>
					</p>
					<table>

						<tr>
							<td>Set application id</td>
							<td><select name="app">
									<option disabled>Choose app</option>
									<c:forEach items="${apps}" var="a">
										<option value="${a.id}">${a.id}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Set auto id</td>
							<td><select name="cars">
									<option disabled>Choose car</option>
									<c:forEach items="${cars}" var="c">
										<option value="${c.id}">${c.label}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Set duration</td>
							<td><input type="text" name="duration" required="required" /></td>
						</tr>

						<tr>
							<td><input type="submit" value="Confirm" style="" /></td>
						</tr>

					</table>
				</div>
			</fieldset>
		</form>
		<c:forEach var="r" items="${cars}">
			<table border="1">


				<tr>
					<th><b>ID</b></th>
					<th><b>passengers</b></th>
					<th><b>cargo</b></th>
					<th><b>release</b></th>
				</tr>
				<tr>
					<td>${r.id}</td>
					<td>${r.label}</td>
					<td>${r.passengers}</td>
					<td>${r.cargo}</td>
				</tr>

			</table>
		</c:forEach>
	</div>
</body>
</html>