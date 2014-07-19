<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tableDiv"> 
<!-- Wichtig ist hier die id der Tabelle. Darüber wird die Tabelle im JQuery erkannt und aktualisiert -->
	<table id="lagerwegedatenTable">
		<tr>
			<th>ID</th>
			<th>Barcode</th>
			<th>Karton ID</th>
			<th>Wege ID</th>
			<th>Wegpunkt</th>
			<th>Erstellungsdatum</th>
			<th>LetztesUpdate</th>
			<th>Gebucht</th>
			<c:if test="${sessionScope.schreibrecht eq true}">
				<th>Editieren</th>
				<th>Löschen</th>
			</c:if>
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
				<!-- Nur bei Schreibrechten einblenden -->
				<c:if test="${sessionScope.schreibrecht eq true}">
					<td><a
						href="<c:url value="/updatelagerwegedaten.html?id=${lwd.id}"/>">
							<img src="<c:url value="/images/table_edit.png"/>">
					</a></td>
					<td><a
						href="<c:url value="/deletelagerwegedaten.html?id=${lwd.id}"/>">
							<img src="<c:url value="/images/cross.png"/>"> </a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</div>