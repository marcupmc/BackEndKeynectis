function drawChart() {
	
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([
	              ['Réussies',parseInt($("#connexion_reussie").val())],
	              ['Echouées',parseInt($("#connexion_echouee").val())]
	              ]);
	var options = {
			title: 'Connexions réussies / échouées'
	};

	var chart = new google.visualization.PieChart(document.getElementById('graphs'));

	chart.draw(data, options);
}

function drawChart2() {
	var data = new google.visualization.DataTable();
	data.addColumn('date', 'Date');
	data.addColumn('number', 'Connexions');

	for(key in dates){
		data.addRow([new Date(dates[key]), nbConnexion[key] ]);
	}
	
//	data.addRows([
//	              [new Date(2008, 1 ,1), 30000, undefined, undefined ],
//	              [new Date(2008, 1 ,2), 14045, undefined, undefined],
//	              [new Date(2008, 1 ,3), 55022, undefined, undefined],
//	              [new Date(2008, 1 ,4), 75284, undefined, undefined ],
//	              [new Date(2008, 1 ,5), 41476,  undefined, undefined],
//	              [new Date(2008, 1 ,6), 33322, undefined, undefined]
//	              ]);

	var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div'));
	chart.draw(data, {displayAnnotations: true});
}
