
//4 pages
var urltest = "http://www.fr.capgemini.com/sites/default/files/resource/pdf/Enterprise_Content_Management.pdf";
//var urltest="D:\Users\magregoi\Desktop\Attestation";
//1 page
//var urltest="http://www.fr.capgemini.com/sites/default/files/resource/pdf/D__claration_transactions_sur_actions_propres_au_15_f__vrier_2013.pdf";


//40 pages
//http://www.fr.capgemini.com/sites/default/files/resource/pdf/wrbr_2013.pdf

var numPage=0;
var imagePdf;
var pageNumber;

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
	$.get('InfosPDF?url='+urltest+'&num='+numPage, function(data) {
		var retour = $.parseJSON(data);
		pageNumber=retour.nbPages;
		imagePdf = retour.image;
		$("#imagePDF").attr("src","data:image/jpg;base64,"+imagePdf);
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

