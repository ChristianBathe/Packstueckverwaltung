<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>FOM - Packstückverwaltung</title>
<link rel="stylesheet"
	href="<%=this.getServletContext().getContextPath()%>/css/style.css">
</head>

<body>
	<form method="post"
		action="<%=this.getServletContext().getContextPath()%>/LoginServlet">
		<span class="error">${global_error}</span>
		<table>
			<tr>
				<td>E-Mail:</td>
				<td><input type="email" name="email" id="email"
					autocomplete="off" placeholder="E-Mail eingeben"></td>
			</tr>
			<tr>
				<td>Passwort:</td>
				<td><input type="password" name="passwort" id="passwort"
					placeholder="Passwort eingeben"></td>
			</tr>
		</table>
		<input type="submit" name="login" value="login">
	</form>
</body>
</html>