<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
</head>
<body>
	<div align="center">
		<form action="Registration" method="post">
			<fieldset style="width: 400px">
					<div align="center">
					<p> Registration of new employee </p>
					<table>
						<tr>
							<td>Your name</td>
							<td><input type="text" name="name" required="required" /></td>
						</tr>
						<tr>
							<td>Your last name</td>
							<td><input type="text" name="lastName" required="required" /></td>
						</tr>
						<tr>
							<td>Your telephone</td>
							<td><input type="text" name="phone" required="required" /></td>
						</tr>
						<tr>
							<td>Your login</td>
							<td><input type="text" name="login" required="required" /></td>
						</tr>
						<tr>
							<td>Your password</td>
							<td><input type="password" name="password"
								required="required" /></td>
						</tr>
						<tr>
							<td>Choose your post</td>
							<td><select name="post">
									<option disabled>Choose post</option>
									<c:forEach items="${posts}" var="p">
										<option value="${p.id}">${p.post}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Choose your car</td>
							<td><select name="car">
									<option disabled>Choose car</option>
									<option value="0">none</option>
									<c:forEach items="${cars}" var="car">
										<option value="${car.id}">${car.label}</option>
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