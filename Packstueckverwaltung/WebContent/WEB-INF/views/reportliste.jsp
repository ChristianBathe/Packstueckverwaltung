<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Änderungsverfolgung</h2>

<form method="post">
	<div>
		<a>Barcodefilter:</a> 
		<input class="suchfeld" name="barcodesuchfeld"
			type="text" placeholder="Barcode eingeben"> 
		<input type="submit" name="barcodesuche" value="Suchen">
	</div>
</form>

<table class="grid">
	<tr>
		<th>ID</th>
		<th>Barcode</th>
		<th>Alte Daten</th>
		<th>Neue Daten</th>
		<th>Benutzer</th>
		<th>Änderungsdatum</th>
	</tr>
	<c:forEach items="${reportliste}" var="r">
		<tr>
			<td><c:out value="${r.id}" /></td>
			<td><c:out value="${r.barcode}" /></td>
			<td><c:out value="${r.altedaten}" /></td>
			<td><c:out value="${r.neuedaten}" /></td>
			<td><c:out value="${r.useremail}" /></td>		
			<td><c:out value="${r.datum}" /></td>			
		</tr>
	</c:forEach>
</table>