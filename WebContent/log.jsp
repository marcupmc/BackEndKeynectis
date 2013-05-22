<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="sun.misc.Cleaner"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.TreeMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logs</title>
<link rel="stylesheet" href="css/style.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<!-- Pick a theme, load the plugin & initialize plugin -->
<link href="css/tablesorter/theme.bootstrap.css" rel="stylesheet">
<script src="js/tablesorter/jquery.tablesorter.js"></script>
<script src="js/tablesorter/jquery.tablesorter.widgets.js"></script>

<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		$(function() {

			$.extend($.tablesorter.themes.bootstrap, {
				// these classes are added to the table. To see other table classes available,
				// look here: http://twitter.github.com/bootstrap/base-css.html#tables
				table : 'table table-bordered',
				header : 'bootstrap-header', // give the header a gradient background
				footerRow : '',
				footerCells : '',
				icons : '', // add "icon-white" to make them white; this icon class is added to the <i> in the header
				sortNone : 'bootstrap-icon-unsorted',
				sortAsc : 'icon-chevron-up',
				sortDesc : 'icon-chevron-down',
				active : '', // applied when column is sorted
				hover : '', // use custom css here - bootstrap class may not override it
				filterRow : '', // filter row class
				even : '', // odd row zebra striping
				odd : '' // even row zebra striping
			});

			// call the tablesorter plugin and apply the uitheme widget
			$("table").tablesorter({
				// this will apply the bootstrap theme if "uitheme" widget is included
				// the widgetOptions.uitheme is no longer required to be set
				theme : "bootstrap",

				widthFixed : true,

				headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!

				// widget code contained in the jquery.tablesorter.widgets.js file
				// use the zebra stripe widget if you plan on hiding any rows (filter widget)
				widgets : [ "uitheme", "filter", "zebra" ],

				widgetOptions : {
					// using the default zebra striping class name, so it actually isn't included in the theme variable above
					// this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
					zebra : [ "even", "odd" ],

					// reset filters button
					filter_reset : ".reset"

				// set the uitheme widget to use the bootstrap theme class names
				// this is no longer required, if theme is set
				// ,uitheme : "bootstrap"

				}
			}).tablesorterPager({

				// target the pager markup - see the HTML block below
				container : $(".pager"),

				// target the pager page select dropdown - choose a page
				cssGoto : ".pagenum",

				// remove rows from the table to speed up the sort of large tables.
				// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
				removeRows : false,

				// output string - default is '{page}/{totalPages}';
				// possible variables: {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
				output : '{startRow} - {endRow} / {filteredRows} ({totalRows})'

			});

		});
	</script>

	<%@page import="domain.Log"%>
	<%@page import="java.util.ArrayList"%>
	<%
		ArrayList<Log> liste_logs = (ArrayList<Log>) request
			.getAttribute("liste_logs");
			
			int connexion_reussie = 0;
			int connexion_echouee=0;
			HashMap<Date,Integer> map = new HashMap<Date,Integer>();
	%>

	<div class="container">
		<h2>Liste des logs</h2>
		<div class="alert alert-block alert-info fade in">Cette section
			regroupe des informations sur des évènements dont le serveur a eu
			connaissance</div>
		<div style="height: 300px; overflow: auto;">
			<!-- 			<table class="tablesorter table table-bordered hasFilters tablesorter-bootstrap"> -->
			<table class="tablesorter">
				<thead>
					<tr>
						<th>Id</th>
						<th>Date</th>
						<th>Type</th>
						<th>Adresse IP</th>
						<th>Identifiant</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < liste_logs.size(); i++)
						{
							Log log = liste_logs.get(i);
							switch(log.getType()){
							case CONNEXION :
								connexion_reussie++;
								Calendar cal =  Calendar.getInstance();
								cal.setTime(log.getDate());
// 																				Date temp = new Date(cal.get(Calendar.YEAR),
// 																						cal.get(Calendar.MONTH),
// 																						cal.get(Calendar.DATE),
// 																						cal.get(Calendar.HOUR_OF_DAY),0);
								Date temp = log.getDate();
								temp.setMinutes(0);
								temp.setSeconds(0);
								if(map.containsKey(temp))
									map.put(temp, map.get(temp) + 1);
								else
									map.put(temp, 1);
							break;
							
							case CONNEXION_FAILED:
								connexion_echouee++;
								break;								
						}
					%>
					<tr>
						<td><%=log.getId()%></td>
						<td><%=log.getDate().toString()%></td>
						<td><%=log.getType().name()%></td>
						<td><%=log.getIpadresse()%></td>
						<td><%=log.getIdentifiant_client()%></td>
					</tr>
					<%
						}
									Map<Date, Integer> treeMap = new TreeMap<Date, Integer>(map);
									
									System.out.println("map : "+treeMap.toString());
					%>
				</tbody>
			</table>
		</div>

		<!-- 		ZONE DE STATS -->
		<input type="hidden" id="connexion_reussie"
			value="<%=connexion_reussie%>" /> <input type="hidden"
			id="connexion_echouee" value="<%=connexion_echouee%>" />
		<h2>Graphiques</h2>
		<div id="graphs"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<div id="chart_div" style="width: 900px; height: 500px;"></div>

		<script type="text/javascript">
			var dates = []
		<%String tmpDates="";
				String tmpNbConnexion="";
				int i =0;
				for(Date mapKey : treeMap.keySet()){
					Calendar cal2 =  Calendar.getInstance();
					cal2.setTime(mapKey);
					System.out.println("cal2: "+cal2.get(Calendar.DATE));
					tmpNbConnexion+=+treeMap.get(mapKey);
					if(i<map.size()-1){
						tmpNbConnexion+=",";
					}
					i++;%>
			dates.push(new Date(
		<%=cal2.get(Calendar.YEAR)%>
			,
		<%=cal2.get(Calendar.MONTH)%>
			,
		<%=cal2.get(Calendar.DATE)%>
			,
		<%=cal2.get(Calendar.HOUR_OF_DAY)%>
			, 0, 0));
		<%}%>
			var nbConnexion = [
		<%=tmpNbConnexion%>
			];
		</script>
	</div>

	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/graphique.js"></script>
	<script type="text/javascript">
		google.load("visualization", "1.0", {
			packages : [ "corechart" ]
		});
		google.load('visualization', '1', {
			'packages' : [ 'annotatedtimeline' ]
		});
		google.setOnLoadCallback(drawChart);
		google.setOnLoadCallback(drawChart2);
	</script>

</body>
</html>
