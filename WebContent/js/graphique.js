function drawChart() {

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([ [ 'R�ussies', parseInt($("#connexion_reussie").val()) ],
			[ 'Echou�es', parseInt($("#connexion_echouee").val()) ] ]);
	var options = {
		title : 'Connexions r�ussies / �chou�es'
	};

	var chart = new google.visualization.PieChart(document
			.getElementById('graphs'));

	chart.draw(data, options);
}

function drawChart2() {

	var data = new google.visualization.DataTable();
	data.addColumn('datetime', 'Date');
	data.addColumn('number', 'Nombre de Connexions : ');

	for (key in dates) {
		data.addRow([ dates[key], nbConnexion[key] ]);
	}

	var chart = new google.visualization.AnnotatedTimeLine(document
			.getElementById('chart_div'));
	chart.draw(data, {
		displayZoomButtons : false,
		min : 0
	});

}
