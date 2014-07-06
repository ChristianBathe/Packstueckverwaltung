<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>FOM - Packstückverwaltung</title>
<meta name="description" content="">
<link rel="stylesheet"
	href="<%=this.getServletContext().getContextPath()%>/css/style.css">
<script type="text/javascript"
	src="<%=this.getServletContext().getContextPath()%>/js/wp.js"></script>
</head>
<body>
	<header>
		<hgroup>
			<h1>Überschrift 1</h1>
			<h3>weitere Überschrift</h3>
		</hgroup>
		<nav>
			<h2>Menü:</h2>
			<ul>
				<li><a href="#">Home</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<%
			if (request.getSession().getAttribute("global_message") != null)
			{
				out.write("<p class=\"message\">" + request.getSession().getAttribute("global_message") + "</p>");
				request.getSession().removeAttribute("global_message");
			}
		%>
		<jsp:include page="/WEB-INF/views/${contentpage}.jsp"></jsp:include>
	</section>
	<footer>
		<hr>
		  <a href="https://github.com/ChristianBathe/Packstueckverwaltung">GitHub Quellcode</a>	
	</footer>
</body>
</html>