//F�hrt den Ajax-Request auf die �bergebene URL aus. Als Parameter f�r den POST werden die URL und der eingegebene Barcode �bergeben
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuchfeld : $("#barcodesuchfeld").val(),
		},
		//Funktion zum Verarbeiten der Antwort/Response.
		function(data) {
			//Tabelle mit dem zur�ckgelieferten HTML ersetzen
			$("#tableDiv").html(data);

			hideMessageDivs();
		});
	};

//Blendet Erfolgs- und Fehlermeldung mit einer Animation aus
function hideMessageDivs(){	
	$("#successDiv").hide("slow");
	$("#errorDiv").hide("slow");
	
	//H�he auf korrekten Wert setzen. Ansonsten ziehen sich die Tabellen zusammen. (H�he wird weniger)
	$("#tableDiv").height( $('body').height());
};

//F�hrt den Ajax-Request auf die �bergebene URL aus. Als Parameter f�r den POST wird nur die URL �bergeben
function jqueryAjaxDelete(url) {
	
	var answer = confirm("Daten wirklich l�schen?");
	if (answer){
		$.post(url,
				//Funktion zum Verarbeiten der Antwort/Response.
				function(data) {
					//Nur den <section> Bereich mit dem zur�ckgelieferten HTML ersetzen
					$('section').html(data);
				});
		}
};