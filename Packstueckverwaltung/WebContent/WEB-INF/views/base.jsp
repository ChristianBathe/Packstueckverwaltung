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
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>
<body>
	<header>
		<hgroup>
			<h1>Packstückverwaltung</h1>
		</hgroup>
		<nav>
			<ul>
				<li><a
					href="<%=this.getServletContext().getContextPath()%>/login.html">Login</a></li>
				<li><a
					href="<%=this.getServletContext().getContextPath()%>/packstueckliste.html">Packstueckliste</a></li>
					<li><a
					href="<%=this.getServletContext().getContextPath()%>/lagerwegedatenliste.html">Lagerwegedaten</a></li>
					<li><a
					href="<%=this.getServletContext().getContextPath()%>/reportliste.html">Änderungslog</a></li>
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
		<a href="https://github.com/ChristianBathe/Packstueckverwaltung">GitHub
			Quellcode</a>
	</footer>
</body>
</html>