<%@page import="packstueckverwaltung.model.Gebucht"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/updatelagerwegedaten.html"/>" method="post">
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
			<td><input type="text" name="barcode" value="${form.barcode}" maxlength="20"></td>
		</tr>
		<tr>
			<td>Kartonid:</td>
			<td><input type="text" name="kartonid" value="${form.kartonid}" maxlength="20"></td>
		</tr>
		<tr>
			<td>Wege ID:</td>
			<td><input type="text" name="wegeid" value="${form.wegeid}" maxlength="20"></td>
		</tr>
		<tr>
			<td>Wegpunkt:</td>
			<td><input type="text" name="wegpunkt" value="${form.wegpunkt}" maxlength="10"></td>
		</tr>
		<tr>
			<td>Letztesupdate:</td>
			<td><input type="text" name="letztesupdate"
				value="${form.letztesupdate}" readonly="readonly" class="readonly"></td>
		</tr>
		<tr>
			<td>Erstellungsdatum:</td>
			<td><input type="text" name="erstellungsdatum"
				value="${form.erstellungsdatum}" readonly="readonly" class="readonly"></td>
		</tr>

	</table>
	<input type="submit" name="saveaction" value="Speichern">
</form>