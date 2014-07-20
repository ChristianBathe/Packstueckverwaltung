<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Wichtig ist hier die id. Darüber wird das DIV im JQuery erkannt und aktualisiert -->
<div class="tableDiv" id="tableDiv"> 
<table class="grid" >
	<tr>
		<th>ID</th>
		<th>Barcode</th>
		<th>Karton ID</th>
		<th>Lagernummer</th>
		<th>Mandant</th>
		<th>Istgewicht</th>
		<th>Minimalgewicht</th>
		<th>Maximalgewicht</th>
		<th>Lieferscheinnummer</th>
		<th>Tranportauftragsnummer</th>
		<th>Versandart</th>
		<th>Erstellungsdatum</th>
		<th>LetztesUpdate</th>
		<th>Bearbeitungsstation</th>
		<th>Scandatum</th>
		<th>Gebucht</th>
		<th>Buchungsdatum</th>
		<th>Manuell</th>
		<c:if test="${sessionScope.schreibrecht eq true}">
			<th>Editieren</th>
			<th>Löschen</th>
		</c:if>
	</tr>
	<c:forEach items="${packstueckliste}" var="p">
		<tr>
			<td><c:out value="${p.id}" /></td>
			<td><c:out value="${p.barcode}" /></td>
			<td><c:out value="${p.kartonid}" /></td>
			<td><c:out value="${p.lagernummer}" /></td>
			<td><c:out value="${p.mandant}" /></td>
			<td><c:out value="${p.istgewicht}" /></td>
			<td><c:out value="${p.minimalgewicht}" /></td>
			<td><c:out value="${p.maximalgewicht}" /></td>
			<td><c:out value="${p.lieferscheinnummer}" /></td>
			<td><c:out value="${p.transportauftragsnummer}" /></td>
			<td><c:out value="${p.versandart}" /></td>
			<td><c:out value="${p.erstellungsdatum}" /></td>
			<td><c:out value="${p.letztesupdate}" /></td>
			<td><c:out value="${p.bearbeitungsstation}" /></td>
			<td><c:out value="${p.scandatum}" /></td>
			<td><c:out value="${p.sapgebucht}" /></td>
			<td><c:out value="${p.buchungsdatum}" /></td>
			<td><c:out value="${p.manuellAngelegt}" /></td>
			<!-- Nur bei Schreibrechten einblenden -->
			<c:if test="${sessionScope.schreibrecht eq true}">
				<td><a
					href="<c:url value="/updatepackstueck.html?id=${p.id}"/>"> 
					<img src="<c:url value="/images/table_edit.png"/>">
				</a></td>
				<!-- Update über JQuery, damit vorher Bestätigungsbox angezeigt werden kann -->
				<td><img src="<c:url value="/images/cross.png"/>" onclick='jqueryAjaxDelete("deletepackstueck.html?id=${p.id}")'> </td>
			</c:if>
		</tr>
	</c:forEach>
</table>
</div>
