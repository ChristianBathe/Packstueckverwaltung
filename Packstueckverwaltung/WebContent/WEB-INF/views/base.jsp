<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>FOM - Packstückverwaltung</title>
<meta name="description" content="">
<link rel="stylesheet"
	href="<%=this.getServletContext().getContextPath()%>/css/style.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
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
		<jsp:include page="/WEB-INF/views/${contentpage}.jsp"></jsp:include>
	</section>
	<footer>
		<hr>
			<address>
			
			<a href="mailto:christian.bathe@bertelsmann.de">christian.bathe@bertelsmann.de</a> <a href="mailto:sven.hoischen@bertelsmann.de">sven.hoischen@bertelsmann.de</a>
		
		</address>
	</footer>
</body>
</html>