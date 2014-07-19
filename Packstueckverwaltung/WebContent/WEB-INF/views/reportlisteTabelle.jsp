<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Wichtig ist hier die id der Tabelle. Darüber wird die Tabelle im JQuery erkannt und aktualisiert -->
<table class="grid" id="reportTable">
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