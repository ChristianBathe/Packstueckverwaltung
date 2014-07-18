<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Änderungsverfolgung</h2>

	<div>
		<a>Barcodefilter:</a> <input class="suchfeld" id="barcodesuchfeld" name="barcodesuchfeld"
			type="text" placeholder="Barcode eingeben"> <input
			type="submit" value="Suchen"
			onclick='ajaxAsyncRequest("reportliste.html")'>
	</div>

<jsp:include page="reportliste.jsp"/>  

<script type="text/javascript">
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuche : $("#barcodesuchfeld").val(),
		}, function(data) {
			alert("Data Loaded: " + data);
		});

	};
	function ajaxAsyncRequest(reqURL) {
		//Creating a new XMLHttpRequest object
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		}
		
		var barcodeValue = $("#barcodesuchfeld").val();
		var parameters = "?barcodesuchfeld="+barcodeValue+"&barcodesuche=barcodesuche";

		//Create a asynchronous POST request
		xmlhttp.open("POST", reqURL + parameters, true);
		
		//Send the proper header information along with the request
		xmlhttp.setRequestHeader("Content-type", "application/html");

		//When readyState is 4 then get the server output
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {					
					document.getElementById("reportTable").innerHTML = xmlhttp.responseText;
					//alert(xmlhttp.responseText);
				} else {
					alert('Something is wrong !!');
				}
			}
		};

		xmlhttp.send(null);
	}
</script>