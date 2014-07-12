<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Packst�cke</h2>

<div>
	<a>Barcodefilter:</a>
	<input class="suchfeld" id="barcodesuchfeld" type="text"> 
	<input type="submit" name="barcodesuche" value="Suchen">
</div>

<a href="<c:url value="/updatepackstueck.html"/>">Packstueck hinzuf�gen</a>

<table class="grid">
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
			<td><a href="<c:url value="/profil2.html?id=${p.id}"/>"> <img
					src="<c:url value="/images/edit.png"/>">
			</a> <a href="<c:url value="/personloeschen.html?id=${p.id}"/>">
					l�schen </a></td>
		</tr>
	</c:forEach>
</table>
<a href="<c:url value="/addpackstueck.html"/>">Packst�ck hinzuf�gen</a>