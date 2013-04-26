//----------------------------------------------------------------------------------------------------------------------------------
//
// Function used by the signatureEditor.jsp
//
//----------------------------------------------------------------------------------------------------------------------------------

//Define the dimensions of the signature zone
function buildZone(){
	var largeur = $("#largeur").val();
	var hauteur = $("#hauteur").val();
	$("#draggable").css("height",hauteur);
	$("#draggable").css("width",largeur);
}

//----------------------------------------------------------------------------------------------------------------------------------

//Set the signature to be undraggable
function fixeZone(){
	var x = $("#draggable").offset().left;
	var y = $("#draggable").offset().top;
	var largeur = $("#draggable").width();
	var hauteur = $("#draggable").height();
	
	x=(x*596)/805;
	largeur = (largeur*596)/805;
	y=(y*842)/1132;
	hauteur=(hauteur*842)/1132;
	
	$("#draggable").draggable("disable");
	$("#draggable").resizable('destroy');
	$("#valider").css("display","none");
	$("#save").css("display","inline");
	
	$("#valX").attr("value",x);
	$("#valY").attr("value",y);
	
	$("#height").attr("value",hauteur);
	$("#width").attr("value",largeur);
	
	//Mettre 596 * 842
	var pdfX  =($("#pdfZone").offset().left*596)/805;
	var pdfy  = ($("#pdfZone").offset().top*842)/1132;
	$("#valPDFX").attr("value",pdfX);
	$("#valPDFY").attr("value",pdfy);
}

//----------------------------------------------------------------------------------------------------------------------------------

//Make the signature zone draggable
function reset(){
	$("#save").css("display","none");
	$("#draggable").draggable("enable");
	$("#valider").css("display","inline");
}