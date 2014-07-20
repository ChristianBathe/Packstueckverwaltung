//Führt den Ajax-Request auf die übergebene URL aus. Als Parameter für den POST werden die URL und der eingegebene Barcode übergeben
	function jqueryAjaxRequest(url) {
		$.post(url, {
			barcodesuchfeld : $("#barcodesuchfeld").val(),
		},
		//Funktion zum Verarbeiten der Antwort/Response.
		function(data) {
			//Tabelle mit dem zurückgelieferten HTML ersetzen
			$("#tableDiv").html(data);

			hideMessageDivs();
		});
	};

//Blendet Erfolgs- und Fehlermeldung mit einer Animation aus
function hideMessageDivs(){	
	$("#successDiv").hide("slow");
	$("#errorDiv").hide("slow");
	
	//Höhe auf korrekten Wert setzen. Ansonsten ziehen sich die Tabellen zusammen. (Höhe wird weniger)
	$("#tableDiv").height( $('body').height());
};

//Führt den Ajax-Request auf die übergebene URL aus. Als Parameter für den POST wird nur die URL übergeben
function jqueryAjaxDelete(url) {
	
	var answer = confirm("Daten wirklich löschen?");
	if (answer){
		$.post(url,
				//Funktion zum Verarbeiten der Antwort/Response.
				function(data) {
					//Nur den <section> Bereich mit dem zurückgelieferten HTML ersetzen
					$('section').html(data);
				});
		}
};