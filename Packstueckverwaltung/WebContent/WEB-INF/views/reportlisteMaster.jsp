<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Änderungsverfolgung</h2>

<div class="filterDiv">
	<a>Barcodefilter:</a> <input class="suchfeld" id="barcodesuchfeld"
		name="barcodesuchfeld" type="text" placeholder="Barcode eingeben">
	<input type="submit" value="Suchen" class="addbutton"
		onclick='jqueryAjaxRequest("reportliste.html")'>
</div>

<jsp:include page="reportlisteTabelle.jsp" />

<script type="text/javascript">
	//Führt den Ajax-Request auf die übergebene URL aus. Als Parameter für den POST werden die URL und der eingegebene Barcode übergeben
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuchfeld : $("#barcodesuchfeld").val(),
		},
		//Funktion zum Verarbeiten der Antwort/Response.
		function(data) {
			$("#reportTable").html(data);

		});

	};
</script>