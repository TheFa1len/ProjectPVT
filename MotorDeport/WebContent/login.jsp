<%@ page contentType="text/html;charset=ISO-8859-1" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to Application</title>
</head>
<body>
	<form action="Login" method="post">
		<fieldset style="width: 300px">
			<legend> Login to App </legend>
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" name="username" required="required" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="userpass" required="required" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Log In" /></td>
				</tr>

			</table>
		</fieldset>
	</form>
	<form action="ToRegistration" method="post">
		<input type="submit" value="Sign In" />
	</form>
</body>
</html>
