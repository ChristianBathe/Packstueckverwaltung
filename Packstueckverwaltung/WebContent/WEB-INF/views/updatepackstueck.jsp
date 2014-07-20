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
			<td><input name="id" value="${form.id}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>Barcode:</td>
			<td><input type="text" name="barcode" value="${form.barcode}" maxlength="20"
			<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Kartonid:</td>
			<td><input type="text" name="kartonid" value="${form.kartonid}" maxlength="20"></td>
		</tr>
		<tr>
			<td>Lagernummer:</td>
			<td><input type="text" name="lagernummer" maxlength="3"
				value="${form.lagernummer}"
				<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Mandant:</td>
			<td><input type="text" name="mandant" value="${form.mandant}" maxlength="3"
				<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Istgewicht:</td>
			<td><input type="number" name="istgewicht" step="any" min="0" maxlength="13"
				value="${form.istgewicht}"></td>
		</tr>
		<tr>
			<td>Minimalgewicht:</td>
			<td><input type="number" name="minimalgewicht" step="any" min="0" maxlength="3"
				value="${form.minimalgewicht}"></td>
		</tr>
		<tr>
			<td>Maximalgewicht:</td>
			<td><input type="number" name="maximalgewicht" step="any" min="0" maxlength="3"
				value="${form.maximalgewicht}"></td>
		</tr>
		<tr>
			<td>Lieferscheinnummer:</td>
			<td><input type="text" name="lieferscheinnummer" maxlength="20"
				value="${form.lieferscheinnummer}"
				<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Transportauftragsnummer:</td>
			<td><input type="text" name="transportauftragsnummer" maxlength="20"
				value="${form.transportauftragsnummer}"
				<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Versandart:</td>
			<td><input type="text" name="versandart" maxlength="10"
				value="${form.versandart}"></td>
		</tr>
		<tr>
			<td>Bearbeitungsstation:</td>
			<td><input type="text" name="bearbeitungsstation" maxlength="10"
				value="${form.bearbeitungsstation}"
				<c:if test="${bearbeitungsmodus eq 'reduced'}"> readonly="readonly" class="readonly"</c:if>></td>
		</tr>
		<tr>
			<td>Letztesupdate:</td>
			<td><input type="text" name="letztesupdate"
				value="${form.letztesupdate}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>Buchungsdatum:</td>
			<td><input type="text" name="buchungsdatum"
				value="${form.buchungsdatum}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>Scandatum:</td>
			<td><input type="text" name="scandatum"
				value="${form.scandatum}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>Erstellungsdatum:</td>
			<td><input type="text" name="erstellungsdatum"
				value="${form.erstellungsdatum}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>ManuellAngelegt:</td>
			<td><input name="manuellAngelegt"
				value="${form.manuellAngelegt}" readonly="readonly" class="readonly"></td>
		</tr>

	</table>
	<input type="submit" name="saveaction" value="Speichern">
</form>