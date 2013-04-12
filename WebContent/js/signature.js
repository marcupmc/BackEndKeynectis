$(document).ready( function () { 
		$("#signature").jSignature();
});

function showSignature() {

	$('#myModal2').modal('hide');

	//On supprime la zone d'affichage si elle existe déjà
	var toDele = document.getElementById("printPicture");
	var parent = toDele.parentNode;
	parent.removeChild(toDele);
	//On la rajoute vu qu'elle vient d'être supprimée
	$("#displayarea").append("<div id=\"printPicture\"></div>");
	var $sigdiv = $("#signature");
	var datapair = $sigdiv.jSignature("getData", "image");
	//Choix du format (ici "image png")
	//Il faut changer cette ligne pour changer de format de sortie var datapair = $sigdiv.jSignature("getData", "image");
	//Affichage de l'image produite:
	$("#printPicture").append("<div><img src=\"data:" + datapair[0] + "," + datapair[1]+"\"/>" );
}

//Fonction appelée lorsque l'on clique sur le bouton "Effacer"
function resetSignature(){
	$("#signature").jSignature("reset");
}