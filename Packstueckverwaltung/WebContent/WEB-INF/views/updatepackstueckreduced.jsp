<%@page import="packstueckverwaltung.model.Gebucht"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/updatepackstueck.html"/>" method="post">
	<input type="hidden" name="id" value="${form.id}">

	<table>
		<tr>
			<td>Gebucht:</td>
			<td>
				<%
					for (int gb : Gebucht.values())
					{
						pageContext.setAttribute("gb", gb);
				%> <input type="radio" name="sapgebucht" value="${gb}" id="${gb}"
				<c:if test="${gb eq form.sapgebucht}"> checked="checked"</c:if>>
				<label for="${gb}">${gb}</label> <%
 	}
 %>
			</td>
		</tr>
		<tr>
			<td>ID:</td>
			<td><input name="id" value="${form.id}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Barcode:</td>
			<td><input name="barcode" value="${form.barcode}"
				readonly="readonly"></td>
		</tr>
		<tr>
			<td>Kartonid:</td>
			<td><input type="text" name="kartonid" value="${form.kartonid}"></td>
		</tr>
		<tr>
			<td>Lagernummer:</td>
			<td><input type="text" name="lagernummer" readonly="readonly"
				value="${form.lagernummer}"></td>
		</tr>
		<tr>
			<td>Mandant:</td>
			<td><input type="text" name="mandant" value="${form.mandant}"
				readonly="readonly"></td>
		</tr>
		<tr>
			<td>Istgewicht:</td>
			<td><input type="number" name="istgewicht"
				value="${form.istgewicht}"></td>
		</tr>
		<tr>
			<td>Minimalgewicht:</td>
			<td><input type="number" name="minimalgewicht"
				value="${form.minimalgewicht}"></td>
		</tr>
		<tr>
			<td>Maximalgewicht:</td>
			<td><input type="number" name="maximalgewicht"
				value="${form.maximalgewicht}"></td>
		</tr>
		<tr>
			<td>Lieferscheinnummer:</td>
			<td><input type="text" name="lieferscheinnummer"
				value="${form.lieferscheinnummer}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Transportauftragsnummer:</td>
			<td><input type="text" name="transportauftragsnummer"
				value="${form.transportauftragsnummer}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Versandart:</td>
			<td><input type="text" name="versandart"
				value="${form.versandart}"></td>
		</tr>
		<tr>
			<td>Bearbeitungsstation:</td>
			<td><input type="text" name="bearbeitungsstation"
				value="${form.bearbeitungsstation}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Letztesupdate:</td>
			<td><input type="text" name="letztesupdate"
				value="${form.letztesupdate}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Buchungsdatum:</td>
			<td><input type="text" name="buchungsdatum"
				value="${form.buchungsdatum}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Scandatum:</td>
			<td><input type="text" name="scandatum"
				value="${form.scandatum}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Erstellungsdatum:</td>
			<td><input type="text" name="erstellungsdatum"
				value="${form.erstellungsdatum}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>ManuellAngelegt:</td>
			<td><input name="manuellAngelegt"
				value="${form.manuellAngelegt}"></td>
		</tr>

	</table>
	<input type="submit" name="saveaction" value="speichern">
</form>