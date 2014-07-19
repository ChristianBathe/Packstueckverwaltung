<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Lagerwegedaten</h2>

<div>
	<a>Barcodefilter:</a> <input class="suchfeld" id="barcodesuchfeld"
		type="text" placeholder="Barcode eingeben"> 
		<input type="submit" name="barcodesuche" value="Suchen"
		onclick='jqueryAjaxRequest("lagerwegedatenliste.html")'>
</div>

<!-- Nur bei Schreibrechten einblenden -->
<c:if test="${sessionScope.schreibrecht eq true}">
	<a href="<c:url value="/updatelagerwegedaten.html"/>">Wegdaten
		hinzuf�gen</a>
</c:if>

<jsp:include page="lagerwegedatenlisteTabelle.jsp" />

<script type="text/javascript">
	//F�hrt den Ajax-Request auf die �bergebene URL aus. Als Parameter f�r den POST werden die URL und der eingegebene Barcode �bergeben
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