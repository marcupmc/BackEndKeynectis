
//--------------------------------DOCUMENT TEST---------------------------------------
//4 pages
var urltest = "http://www.fr.capgemini.com/sites/default/files/resource/pdf/Enterprise_Content_Management.pdf";
//var urltest="D:\Users\magregoi\Desktop\Attestation";
//1 page
//var urltest="http://www.fr.capgemini.com/sites/default/files/resource/pdf/D__claration_transactions_sur_actions_propres_au_15_f__vrier_2013.pdf";

//40 pages
//http://www.fr.capgemini.com/sites/default/files/resource/pdf/wrbr_2013.pdf

//--------------------------------------------------------------------------------------

var signatures=[];
var numPage=0;
var imagePdf;
var pageNumber;
var nbSignature=1;

var minX;
var maxX;
var minY;
var maxY;

$(document).ready( function () { 
	checkNumPage();
	showPage();
});

function previousPage(){
	if(numPage>0){
		numPage--;
		showPage();
	}
	checkNumPage();
}


function nextPage(){
	if(numPage<pageNumber){
		numPage++;
		showPage();
	}
	checkNumPage();
}

function showPage(){
	for(key in signatures){
		$("#im"+signatures[key].nom).remove();
	}
	$("#imagePDF").css("display","none");
	$("#loader").css("display","inline");
	$.get('InfosPDF?url='+urltest+'&num='+numPage, function(data) {
		var retour = $.parseJSON(data);
		pageNumber=retour.nbPages;
		imagePdf = retour.image;
		$("#imagePDF").attr("src","data:image/jpg;base64,"+imagePdf);
		$("#loader").css("display","none");
		$("#imagePDF").css("display","inline");
		afficheSignature();
	});
}

function checkNumPage(){
	if(numPage==0)
		$("#previous").css("display","none");
	else
		$("#previous").css("display","inline");
	if(numPage+1==pageNumber)
		$("#next").css("display","none");
	else
		$("#next").css("display","inline");
}

function createSignature(){

	$("#fixer").css("display","inline");
	$("#signatures").append(""+
			"<div id=\"sign"+nbSignature+"\" class=\"draggable\" class=\"ui-widget-content\">"+
			"<p>Zone de Signature</p>" +
			" <a onclick=\"removeSign("+nbSignature+")\" class=\"btn btn-small btn-danger\" href=\"#\"><i class=\"icon-remove  icon-white\"></i></a>" +
			"</div>"
	);
	$(".draggable").resizable();
	$(".draggable").draggable();
	$("#createSignature").css("display","none");
}

//Supprime la signature créée mais non fixée
function removeSign(numSignature){
	$("#fixer").css("display","none");
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

function fixer(){
	var x = $("#sign"+nbSignature).offset().left;
	var y = $("#sign"+nbSignature).offset().top;
	var largeur = $("#sign"+nbSignature).width();
	var hauteur = $("#sign"+nbSignature).height();

	if(x>=minX && x+largeur<=maxX && y>=minY && y+hauteur<= maxY ){
		var signature = new Signature(nbSignature, x, y,largeur, hauteur,numPage);
		signatures.push(signature);

		$("#listeSignatures").append("<li id=\""+signature.nom+"\">"+signature.nom+"<a onclick=\"deleteSign("+signature.nom+")\" class=\"btn btn-small btn-danger\" href=\"#\"><i class=\"icon-remove  icon-white\"></i></a></li>")
		$("#sign"+nbSignature).remove();
		afficheSignature();
		$("#fixer").css("display","none");
		$("#createSignature").css("display","inline");
		nbSignature++;
	}else
		alert("Veuillez positionner la signature sur le document");
}

function afficheSignature(){
	for(key in signatures){
		if(signatures[key].num==numPage){
			$("body").append("" +
					"<div id=\"im"+signatures[key].nom+"\" class=\"imgSig\" >" +
					"<p>"+signatures[key].nom+"</p>" +
			"</div>");

			$("#im"+signatures[key].nom).offset({ top: signatures[key].posy, left: signatures[key].posx});
			$("#im"+signatures[key].nom).width(signatures[key].largeur);
			$("#im"+signatures[key].nom).height(signatures[key].hauteur);
		}else{
			$("#id"+signatures[key].nom).remove();
		}
	}
	minX = $("#imagePDF").offset().left;
	maxX = minX+$("#imagePDF").width();
	minY = $("#imagePDF").offset().top;
	maxY = minY+$("#imagePDF").height();
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








