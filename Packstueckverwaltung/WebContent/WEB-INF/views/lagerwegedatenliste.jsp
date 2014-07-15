<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Lagerwegedaten</h2>

<form method="post">
	<div>
		<a>Barcodefilter:</a> 
		<input class="suchfeld" name="barcodesuchfeld"
			type="text" placeholder="Barcode eingeben"> 
		<input type="submit" name="barcodesuche" value="Suchen">
	</div>
</form>
<a href="<c:url value="/updatelagerwegedaten.html"/>">Wegdaten
	hinzufügen</a>

<table class="grid">
	<tr>
		<th>ID</th>
		<th>Barcode</th>
		<th>Karton ID</th>
		<th>Wege ID</th>
		<th>Wegpunkt</th>
		<th>Erstellungsdatum</th>
		<th>LetztesUpdate</th>		
		<th>Gebucht</th>
	</tr>
	<c:forEach items="${lagerwegedatenliste}" var="lwd">
		<tr>
			<td><c:out value="${lwd.id}" /></td>
			<td><c:out value="${lwd.barcode}" /></td>
			<td><c:out value="${lwd.kartonid}" /></td>
			<td><c:out value="${lwd.wegeid}" /></td>
			<td><c:out value="${lwd.wegpunkt}" /></td>		
			<td><c:out value="${lwd.erstellungsdatum}" /></td>		
			<td><c:out value="${lwd.letztesupdate}" /></td>		
			<td><c:out value="${lwd.sapgebucht}" /></td>		
			<td><a href="<c:url value="/updatelagerwegedaten.html?id=${lwd.id}"/>">
					<img src="<c:url value="/images/edit.png"/>">
			</a> <a href="<c:url value="/deletelagerwegedaten.html?id=${lwd.id}"/>">
					löschen </a></td>
		</tr>
	</c:forEach>
</table>