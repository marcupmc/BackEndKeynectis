<%@page import="domain.EventType"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="sun.misc.Cleaner"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.TreeMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="domain.Log"%>
<%@page import="java.util.ArrayList"%>
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
	<%
		ArrayList<Log> liste_logs = (ArrayList<Log>) request
			.getAttribute("liste_logs");
			int connexion_reussie = 0;
			int connexion_echouee=0;
			HashMap<Date,Integer> map = new HashMap<Date,Integer>();
	%>
	<div class="containerLog">
		<h2>
			Liste des logs <a href="adminHome.jsp" class="btn btn-info">Accueil</a>
		</h2>

		<div class="alert alert-block alert-info fade in">Cette section
			regroupe des informations sur des évènements dont le serveur a eu
			connaissance</div>


		
		<div style="height: 320px; overflow: auto; " id="logZone">
			<img alt="test" src="img/loader.gif" id="waiterLog" class="loader" style="margin-left:50%;">
			<div id='table_div' style="display: none"></div>
		</div>

		<div id="grapheZone">
			<h2>Graphiques</h2>
			<table id="tableCharts">
				<tr>
					<td><img alt="test" src="img/loader.gif" id="waiter"
						class="loader">
						<div id="errorBar"
							style="width: 750px; height: 400px; visibility: hidden"></div></td>
					<td><img alt="test" src="img/loader.gif" id="waiter2"
						class="loader">
						<div id="errorPie"
							style="width: 750px; height: 400px; margin: 0 auto; visibility: hidden"></div>
					</td>

				</tr>
				<tr>
					<td><img alt="test" src="img/loader.gif" id="waiter3"
						class="loader">
						<div id="graphs"
							style="width: 750px; height: 400px; margin: 0 auto; visibility: hidden"></div>
					</td>
					<td><img alt="test" src="img/loader.gif" id="waiter4"
						class="loader">
						<div id="chart_div"
							style="width: 600px; height: 400px; visibility: hidden"></div></td>
				</tr>
				<tr>
					<td><img alt="test" src="img/loader.gif" id="waiter5"
						class="loader">
						<div id="documentReport"
							style="width: 750px; height: 400px; margin: 0 auto; visibility: hidden"></div>
					</td>
					<td><img alt="test" src="img/loader.gif" id="waiter6"
						class="loader">
						<div id="chart_div2"
							style="width: 750px; padding-left: 25px; height: 500px;"></div></td>
				</tr>

			</table>
		</div>


	</div>
	<script type='text/javascript' src='https://www.google.com/jsapi'></script>
	<script src="js/graphique.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	<script type="text/javascript">
		google.load('visualization', '1', {packages:['table']});
		google.load("visualization", "1.0", {
			packages : [ "corechart" ]
		});
		google.load('visualization', '1', {
			'packages' : [ 'annotatedtimeline' ]
		});
		google.load('visualization', '1', {
			'packages' : [ 'geochart' ]
		});
		
		google.setOnLoadCallback(drawChart);
		google.setOnLoadCallback(drawChart2);
		google.setOnLoadCallback(drawChart3);
		google.setOnLoadCallback(drawChart4);
		google.setOnLoadCallback(drawChart5);
		google.setOnLoadCallback(drawMarkersMap);
		google.setOnLoadCallback(getAllLog);
	</script>

</body>
</html>


