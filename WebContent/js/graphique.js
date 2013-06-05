var city =[[]];
var ci = [];
var success;
var failed;
var geocoder;
var numPage=0;


//Affiche tous les logs 
function getAllLog(){
	
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/getalllogs",
		datatype:"jsonp",
		success: function(msg){ 
			var data2 = new google.visualization.DataTable();
			data2.addColumn('number', 'Id');
			data2.addColumn('string', 'Date');
			data2.addColumn('string', 'Type');
			data2.addColumn('string', 'Adresse Ip');
			data2.addColumn('string', 'Identifiant');
			data2.addColumn('string', 'Emetteur');
		    
			var allLogs  = $.parseJSON(msg);
			var listeLog = allLogs.logs;
			for(i=0;i<listeLog.length;i++)
			{
				data2.addRow([listeLog[i].id,
				              listeLog[i].date,
				              listeLog[i].type,
				              listeLog[i].ipadresse,
				              listeLog[i].identifiant,
				              listeLog[i].event]);
				
			}
			var table = new google.visualization.Table(document.getElementById('table_div'));
			$("#waiterLog").remove();
			$("#table_div").css("display","inline");
			table.draw(data2, {showRowNumber: false,page:"enable"});
		}
	});
	return false; 
	
}


function getConnexionsPerHour(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/connexionperhours",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var data = new google.visualization.DataTable();
				data.addColumn('datetime', 'Date');
				data.addColumn('number', 'Nombre de Connexions : ');
				
				var JSONConnexion  = $.parseJSON(msg);

				$.each(JSONConnexion, function(key, value){
					data.addRow([new Date(key), value]);
				});
				var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div'));
				
				$("#waiter4").remove();
				$("#chart_div").css("visibility","visible");
				chart.draw(data, {displayZoomButtons: false,min:0});
			}
		}
	});
	return false; 
}

function getConnexionsReport(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/connexionreport",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var JSONConnexionReport  = $.parseJSON(msg);
				success = JSONConnexionReport.success;
				failed  = JSONConnexionReport.failed;

				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Topping');
				data.addColumn('number', 'Slices');
				data.addRows([ [ 'Réussies', success ],
				               [ 'Echouées',failed ] ]);
				var options = {
						title : 'Connexions réussies / échouées'
				};

				var chart = new google.visualization.PieChart(document
						.getElementById('graphs'));

				$("#waiter3").remove();
				$("#graphs").css("visibility","visible");
				
				chart.draw(data, options);
			}
		}
	});
	return false; 
}

function getErrorsPerType(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/errorpertype",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var JSONErrorReport  = $.parseJSON(msg);
				var erreur1 = JSONErrorReport.CONNEXION_FAILED;
				var erreur2 = JSONErrorReport.ERREUR_LECTURE_CONFIGURATION;
				var erreur3 = JSONErrorReport.ERREUR_HASHBASE64;
				var erreur4 = JSONErrorReport.ERREUR_GETBLOB;
				var erreur5 = JSONErrorReport.ERREUR_DECODING_BLOB_SIGNATURE;
				var erreur6 = JSONErrorReport.ERREUR_RTIFACTORY;
				var erreur7 = JSONErrorReport.ERREUR_ORIGINAL_METIER_FACTORY;
				var erreur8 = JSONErrorReport.ERREUR_ENCODING_PDF_SIGZONE;
				var erreur9 = JSONErrorReport.ERREUR_PDF2XML;
				var erreur10 = JSONErrorReport.ERREUR_KEYNECTIS_KWEBSIGN;
				
				failed  = 4;
				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Topping');
				data.addColumn('number', 'Slices');
				data.addRows([ 
				              ['Erreur de connexion',erreur1 ],
				              ['Erreur de lecture des configuration',erreur2],
				              ['Erreur de hashBase64',erreur3],
				              ['Erreur de recuperation du blob',erreur4],
				              ['Erreur de decodage de la signature',erreur5],
				              ['Erreur de construction du rti',erreur6],
				              ['Erreur de construction de l original metier',erreur7],
				              ['Erreur de d aposition des zones de signature',erreur8],
				              ['Erreur de pdf to xml',erreur9],
				              ['Envoie d un code d erreur de KWebSign',erreur10]
				              
				              ]);
				var options = {
						title : 'Répartition des erreurs par type'
				};

				var chart = new google.visualization.PieChart(document
						.getElementById('errorPie'));

				$("#waiter2").remove();
				$("#errorPie").css("visibility","visible");
				chart.draw(data, options);
			}
		}
	});
	return false; 
}

function getDocumentReport(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/documentreport",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var JSONDocumentReport  = $.parseJSON(msg);
				var certifie = JSONDocumentReport.certifie;
				var error   = JSONDocumentReport.error;
				var waiting = JSONDocumentReport.waiting;

				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Topping');
				data.addColumn('number', 'Slices');
				data.addRows([ [ 'Certifies', certifie ],
				               [ 'Erreurs',error ],
				               [ 'En attente',waiting]
				               ]);
				var options = {
						title : 'Statut des documents'
				};

				var chart = new google.visualization.PieChart(document
						.getElementById('documentReport'));
				
				
				$("#waiter5").remove();
				$("#documentReport").css("visibility","visible");
				chart.draw(data, options);
			}
		}
	});
	return false; 
}

function drawChart5() {
	getDocumentReport();
}

function drawChart() {
	getConnexionsReport();
}

function drawChart2() {
	getConnexionsPerHour();
}

function drawChart3()
{
	getErrorsPerType();	
}

function drawChart4() {
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:8080/TestRest/rest/statistiques/errorperday",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var data = new google.visualization.DataTable();
				data.addColumn('datetime', 'Date');
				data.addColumn('number', 'Fréquence des erreurs : ');

				var JSONConnexion  = $.parseJSON(msg);
				$.each(JSONConnexion, function(key, value){
					data.addRow([new Date(key), value]);
				});

				
				var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('errorBar'));
				
				$("#waiter").remove();
				$("#errorBar").css("visibility","visible");
				chart.draw(data, {displayZoomButtons: false,min:0,colors:["red"]});

			}
		}
	});
	return false; 
}





function initCities(){
	//ci.push(ipToCity(ip));
//	getCityFromCoordinates(40.714224, -73.961452);
	getCityFromCoordinates(44.837789,-0.5791799999999512) ;
}

function getCityFromCoordinates(lat,lng) {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(lat, lng);
	geocoder.geocode({'latLng': latlng}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[1]) {
				var add  = results[1].address_components;
				var ville = add[1].long_name;
				return ville;
			} else {
				alert('No results found');
			}
		} else {
			alert('Geocoder failed due to: ' + status);
		}
	});
}




function ipToCity(ip){
	if (window.XMLHttpRequest) xmlhttp = new XMLHttpRequest();
	else xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

	xmlhttp.open("GET","http://api.hostip.info/get_html.php?ip="+ip+"&position=true",false);
	xmlhttp.send();

	hostipInfo = xmlhttp.responseText.split("\n");

	for (i=0; hostipInfo.length >= i; i++) {
		ipAddress = hostipInfo[i].split(":");
		if ( ipAddress[0] == "City" ) return[1];

	}

	return false;

}


function drawMarkersMap() {
	initCities();
	var data = google.visualization.arrayToDataTable([
	                                                  ['City',   'Population', 'Area'],
	                                                  ['Paris',      120,    120],
	                                                  ['Lyon',     70,   70],
	                                                  ['Bordeaux',  29,      29],
	                                                  ['Marseille',  60,     60]
	                                                  ]);


	var options = {
			region: 'FR',
			displayMode: 'markers',
			colorAxis: {colors: ['green', 'blue']}
	};

	var chart = new google.visualization.GeoChart(document.getElementById('chart_div2'));
	
	$("#waiter6").remove();
	$("#chart_div2").css("visibility","visible");
	chart.draw(data, options);
};
