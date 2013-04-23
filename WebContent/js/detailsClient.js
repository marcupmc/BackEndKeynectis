function signOUI(){
	$("#champsAjout").empty();
	$("#champsAjout").append("<label  class=\"control-label\" for=\"signame\">Nom de la signature : </label>" +
			"<input type=\"text\" id=\"signame\" name=\"signame\" placeholder=\"nom de la signature\" required=\"required\">");

	$("#containsSign").val("oui");
}

function signNON(){
	$("#champsAjout").empty();
	$("#containsSign").val("non");
}