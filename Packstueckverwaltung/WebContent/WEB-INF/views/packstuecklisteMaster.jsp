<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Packstücke</h2>
<div class="success">${global_message}</div>
<div class="filterDiv">
	<a>Barcodefilter:</a> <input class="suchfeld" id="barcodesuchfeld"
		type="text" placeholder="Barcode eingeben"> 
		<input
		type="submit" value="Suchen" class="addbutton"
		onclick='jqueryAjaxRequest("packstueckliste.html")'>

	<!-- Nur bei Schreibrechten einblenden -->
	<c:if test="${sessionScope.schreibrecht eq true}">
		<input type="button"
			onclick="window.location='/Packstueckverwaltung/updatepackstueck.html'"
			value="Packstück hinzufügen" class="addbutton">
	</c:if>
</div>



<jsp:include page="packstuecklisteTabelle.jsp" />

<script type="text/javascript">
	//Führt den Ajax-Request auf die übergebene URL aus. Als Parameter für den POST werden die URL und der eingegebene Barcode übergeben
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuchfeld : $("#barcodesuchfeld").val(),
		},
		//Funktion zum Verarbeiten der Antwort/Response.
		function(data) {
			$("#packstueckTable").html(data);

		});

	};
</script>