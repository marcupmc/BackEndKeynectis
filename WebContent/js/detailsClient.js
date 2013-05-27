//----------------------------------------------------------------------------------------------------------------------------------

//Used by detailsClient.jsp

//----------------------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------------------

//Create an input text for the signature name
function signOUI(){
	$("#champsAjout").empty();
	$("#champsAjout").append("<label  class=\"control-label\" for=\"signame\">Nom de la signature : </label>" +
	"<input type=\"text\" id=\"signame\" name=\"signame\" placeholder=\"nom de la signature\" required=\"required\">");

	$("#containsSign").val("oui");
}

//Hide the input text for the signature name
function signNON(){
	$("#champsAjout").empty();
	$("#containsSign").val("non");
}

function showPDF(id){
	$.get('SendBase64PDF?id='+id, function(data) {
		$("#pdfViewer").empty();
		$("#pdfViewer").append("<iframe width=\"98%\" height=\"90%\" name=\"plugin\" src=\"data:application/pdf;base64,"+data+"\" type=\"application/pdf\">");
	});
}


