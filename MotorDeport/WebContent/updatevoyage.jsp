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
		<p>You are logged as <c:out value="${name}" /></p>
		<form action="UpdateVoyage" method="post">
			<fieldset style="width: 400px">
					<div align="center">
					<p> Form for update voyage </p>
					<table>
						<tr>
							<td>Set voyage id</td>
							<td><select name="app">
									<option disabled>Choose voyage</option>
									<option value ="0">None</option>
									<c:forEach items="${result_voy}" var="res">
										<option value="${res.id}">${res.id}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><input type="submit" value="Confirm" /></td>
						</tr>
					</table>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>
