var city =[[]];
var ci = [];
var success;
var failed;

var geocoder;

function getConnexionsPerHour(){
	$.ajax({ 
		type: "GET", 
		url: "http://localhost:5546/TestRest/rest/statistiques/connexionperhours",
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
				chart.draw(data, {displayZoomButtons: false,min:0});
			}
		}
	});
	return false; 
}

function getConnexionsReport(){
	$.ajax({ 
		type: "GET", 
		url: "http://localhost:5546/TestRest/rest/statistiques/connexionreport",
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

				chart.draw(data, options);
			}
		}
	});
	return false; 
}

function getErrorsPerType(){
	$.ajax({ 
		type: "GET", 
		url: "http://localhost:5546/TestRest/rest/statistiques/errorpertype",
		datatype:"jsonp",
		success: function(msg){ 
			if(msg=="error") 
				alert('Erreur ! ');
			else{
				var JSONErrorReport  = $.parseJSON(msg);
				//success = JSONConnexionReport.success;
				//failed  = JSONErrorReport.failed;
				failed  = 4;
				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Topping');
				data.addColumn('number', 'Slices');
				data.addRows([ 
				              [ 'Erreurs',failed ] ]);
				var options = {
						title : 'Répartition des erreurs'
				};

				var chart = new google.visualization.PieChart(document
						.getElementById('errorPie'));

				chart.draw(data, options);
			}
		}
	});
	return false; 

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
		type: "GET", 
		url: "http://localhost:5546/TestRest/rest/statistiques/errorperday",
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
	chart.draw(data, options);
};
