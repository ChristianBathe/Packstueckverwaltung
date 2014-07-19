<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Lagerwegedaten</h2>
<!-- Für erfolgreiches Speichern der Daten -->
<c:if test="${not empty global_message}">
	<div class="success">${global_message}</div>
</c:if>
<!-- Für Fehlermeldungen -->
<c:if test="${not empty global_error}">
	<div class="error">${global_error}</div>
</c:if>

<div class="filterDiv">
	<a>Barcodefilter:</a> <input class="suchfeld" id="barcodesuchfeld"
		type="text" placeholder="Barcode eingeben"> <input
		type="submit" name="barcodesuche" value="Suchen" class="addbutton"
		onclick='jqueryAjaxRequest("lagerwegedatenliste.html")'>

	<!-- Nur bei Schreibrechten einblenden -->
	<c:if test="${sessionScope.schreibrecht eq true}">
		<input type="button"
			onclick="window.location='/Packstueckverwaltung//updatelagerwegedaten.html'"
			value="Packstück hinzufügen" class="addbutton">
	</c:if>
</div>



<jsp:include page="lagerwegedatenlisteTabelle.jsp" />

<script type="text/javascript">
	//Führt den Ajax-Request auf die übergebene URL aus. Als Parameter für den POST werden die URL und der eingegebene Barcode übergeben
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuchfeld : $("#barcodesuchfeld").val(),
		},
		//Funktion zum Verarbeiten der Antwort/Response.
		function(data) {
			$("#lagerwegedatenTable").html(data);

		});
	};
</script>