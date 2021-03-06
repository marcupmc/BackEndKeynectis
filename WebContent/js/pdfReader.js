var db=0;

var signatures=[];
var numPage=0;
var imagePdf;
var pageNumber;
var nbSignature=1;

var minX;
var maxX;
var minY;
var maxY;

var idDoc;
var idOwner;

//Initialisation de la page
$(document).ready( function () { 

	idDoc = $("#idDoc").val();
	idOwner = $("#idOwner").val();
	showPage();

});

//Affiche la page pr�c�dente
function previousPage(){
	if(numPage>0){
		numPage--;
		showPage();
	}
	checkNumPage();
}

//Affiche la page suivante
function nextPage(){
	if(numPage<pageNumber){
		numPage++;
		showPage();
	}
	checkNumPage();
}

//Affiche la page en cours du pdf
function showPage(){
	for(key in signatures){
		$("#im"+signatures[key].nom).remove();
	}
	$("#imagePDF").css("display","none");
	$("#loader").css("display","inline");
	$.get('InfosPDF?id='+idDoc+'&num='+numPage, function(data) {
		var retour = $.parseJSON(data);
		pageNumber=retour.nbPages;
		imagePdf = retour.image;
		$("#imagePDF").attr("src","data:image/jpg;base64,"+imagePdf);
		$("#loader").css("display","none");
		$("#imagePDF").css("display","inline");
		afficheSignature();
		if(db==0){
			minX = $("#pdfreader").offset().left;
			maxX = minX+$("#imagePDF").width();
			minY = $("#pdfreader").offset().top;
			maxY = minY+$("#imagePDF").height();
			db++;
		}
		checkNumPage();
	});
}

//Affiche les boutons de changements de page
function checkNumPage(){
	if(numPage==0)
		$("#previous").css("display","none");
	else
		$("#previous").css("display","inline");
	if(numPage+1>=pageNumber)
		$("#next").css("display","none");
	else
		$("#next").css("display","inline");
}

//Cr�e une zone de signature � fixer
function createSignature(){

	//$("#fixer").css("display","inline");
	$("#signatures").append(""+
			"<div id=\"sign"+nbSignature+"\" class=\"draggable\" class=\"ui-widget-content\">"+
			"<p>Zone de Signature</p>" +
			"<button onclick=\"fixer()\" id=\"fixer\" class=\"btn btn-info\">Fixer la signature</button> " +
			"<a onclick=\"removeSign("+nbSignature+")\" class=\"btn btn-small btn-danger\" href=\"#\"><i class=\"icon-remove  icon-white\"></i></a>" +
			"</div>"
	);
	$(".draggable").resizable();
	$(".draggable").draggable();
	$("#createSignature").css("display","none");
}

//Supprime la signature cr��e mais non fix�e
function removeSign(numSignature){
	//$("#fixer").css("display","none");

	$("#fixer").remove();
	$("#sign"+numSignature).remove();
	$("#createSignature").css("display","inline");
}

//Supprime la signature de la liste des signatues
//Supprime du tableau  et actualise la page
function deleteSign(nomSignature){
	for(key in signatures){
		if(signatures[key].nom==$(nomSignature).attr("id")){
			signatures = $.grep(signatures,function(value){
				return signatures[key]!=value;
			});
		}
	}
	$("#im"+$(nomSignature).attr("id")).remove();
	afficheSignature();
	$(nomSignature).remove();
}

//Fixe la zone de signature sur le document
function fixer(){
//	alert('toto');
//	alert('dimX='+maxX+' & dimY='+maxY+' & pdfX='+minX+' & pdfY='+minY);
	var x = $("#sign"+nbSignature).offset().left;
	var y = $("#sign"+nbSignature).offset().top;
	var largeur = $("#sign"+nbSignature).width();
	var hauteur = $("#sign"+nbSignature).height();

//	if(x>=minX && x+largeur<=maxX && y>=minY && y+hauteur<= maxY ){
	var signature = new Signature(nbSignature, x, y,largeur, hauteur,numPage);
	signatures.push(signature);

	$("#listeSignatures").append("" +
			"<li id=\""+signature.nom+"\">"+signature.nom+"" +
			"<a onclick=\"deleteSign("+signature.nom+")\" class=\"btn btn-small btn-danger\" href=\"#\">" +
			"<i class=\"icon-remove  icon-white\"></i></a>" +
	"</li>");
	$("#sign"+nbSignature).remove();
	afficheSignature();
//	$("#fixer").css("display","none");
	$("#createSignature").css("display","inline");
	nbSignature++;
//	}else
//	alert("Veuillez positionner la signature sur le document");
//	$("#fixer").remove();
}

//Affiche toutes les signatures enregistr�es
function afficheSignature(){
	$(".imgSig").remove();
	if(signatures.length==0)
		$("#saveSignatures").css("display","none");
	else
		$("#saveSignatures").css("display","inline");
	for(key in signatures){
		if(signatures[key].num==numPage){
			$("body").append("" +
					"<div id=\"im"+signatures[key].nom+"\" class=\"imgSig\" >" +
					"<p>"+signatures[key].nom+"</p>" +
			"</div>");

			$("#im"+signatures[key].nom).offset({ top: signatures[key].posy, left: signatures[key].posx});
			$("#im"+signatures[key].nom).width(signatures[key].largeur);
			$("#im"+signatures[key].nom).height(signatures[key].hauteur);

		}

	}

}

//Sauvegarde toutes zones de signature du document
//Redirige vers la page du propri�taire du document 
function saveSignatures(){

	var myJsonString = JSON.stringify(signatures);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data: { jsondata : myJsonString},
		url : 'SaveSignatures?idDoc='+idDoc+'&dimX='+maxX+'&dimY='+maxY+'&pdfX='+minX+'&pdfY='+minY,
		timeout : 5000,
		success : function(msg) {
			document.location.href="DetailsClient?id="+idOwner;
		},
		error : function(xhr, textStatus, errorThrown) {
			document.location.href="DetailsClient?id="+idOwner;
		}
	});
}


//------------Objet signature------------------------------------------
function Signature(numSignature,posx,posy,largeur,hauteur,num) { 
	this.nom = "signature"+numSignature; 
	this.posx=posx;
	this.posy=posy;
	this.num=num;
	this.largeur=largeur;
	this.hauteur=hauteur;
} 
//---------------------------------------------------------------------








