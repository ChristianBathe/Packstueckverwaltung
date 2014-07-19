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
		<table>
			<tr>
				<td><a>E-Mail:</a></td>
				<td><input type="email" name="email" id="email" class="loginInput"
					autocomplete="off" placeholder="E-Mail eingeben"></td>
			</tr>
			<tr>
				<td><a>Passwort:</a></td>
				<td><input type="password" name="passwort" id="passwort" class="loginInput"
					placeholder="Passwort eingeben"></td>
					
			</tr>
		</table>
		<input type="submit" name="login" value="login" class="addbutton">
	</form>
</body>
</html>