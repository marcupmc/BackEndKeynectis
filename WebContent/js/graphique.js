var city =[[]];
var ci = [];

function drawChart() {

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([ [ 'Réussies', parseInt($("#connexion_reussie").val()) ],
			[ 'Echouées', parseInt($("#connexion_echouee").val()) ] ]);
	var options = {
		title : 'Connexions réussies / échouées'
	};

	var chart = new google.visualization.PieChart(document
			.getElementById('graphs'));

	chart.draw(data, options);
}

function drawChart2() {

	var data = new google.visualization.DataTable();
	data.addColumn('datetime', 'Date');
	data.addColumn('number', 'Nombre de Connexions : ');

	for(key in dates){
		data.addRow([dates[key], nbConnexion[key] ]);
	}

	var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div'));
	chart.draw(data, {displayZoomButtons: false,min:0});
}


function initCities(ip){
	//ci.push(ipToCity(ip));

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
