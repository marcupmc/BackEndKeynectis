function buildZone(){
	var largeur = $("#largeur").val();
	var hauteur = $("#hauteur").val();
	
	$("#draggable").css("height",hauteur);
	
	$("#draggable").css("width",largeur);
}

function fixeZone(){
	var x = $("#draggable").offset().left;
	var y = $("#draggable").offset().top;
	
	$("#draggable").draggable("disable");
	$("#valider").css("display","none");
	$("#save").css("display","inline");
	
	// set les input hidden
	$("#valX").attr("value",x);
	$("#valY").attr("value",y);
	$("#valPDFX").attr("value",$("#pdfZone").offset().left);
	$("#valPDFY").attr("value",$("#pdfZone").offset().top);
	
}

function reset(){
	$("#save").css("display","none");
	$("#draggable").draggable("enable");
	$("#valider").css("display","inline");
}