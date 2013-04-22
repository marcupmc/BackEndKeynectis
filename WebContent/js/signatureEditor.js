function buildZone(){
	var largeur = $("#largeur").val();
	var hauteur = $("#hauteur").val();
	
	$("#draggable").css("height",hauteur);
	
	$("#draggable").css("width",largeur);
}

function fixeZone(){
	var x = $("#draggable").offset().left;
	var y = $("#draggable").offset().top;
	var largeur = $("#draggable").width();
	var hauteur = $("#draggable").height();
	
	
	alert('largeur : '+largeur+'\n longueur : '+hauteur);
	
	$("#draggable").draggable("disable");
	$("#valider").css("display","none");
	$("#save").css("display","inline");
	
	// set les input hidden
	$("#valX").attr("value",x);
	$("#valY").attr("value",y);
	
	$("#height").attr("value",hauteur);
	$("#width").attr("value",largeur);
	
	alert('pdf hauteur : '+$("#pdfZone").height()+'\nlargeur : '+$("#pdfZone").width());
	
	$("#valPDFX").attr("value",$("#pdfZone").offset().left);
	$("#valPDFY").attr("value",$("#pdfZone").offset().top);
	
}

function reset(){
	$("#save").css("display","none");
	$("#draggable").draggable("enable");
	$("#valider").css("display","inline");
}