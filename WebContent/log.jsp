<%@page import="sun.misc.Cleaner"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>
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


<title>Insert title here</title>
</head>
<body>
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
			<table class="table table-striped">
				<tr>
					<th>Id</th>
					<th>Date</th>
					<th>Type</th>
					<th>Adresse IP</th>
					<th>Identifiant</th>
				</tr>
				<%
					for (int i = 0; i < liste_logs.size(); i++) {
																			Log log = liste_logs.get(i);
																			switch(log.getType()){
																			case CONNEXION :
																				connexion_reussie++;
																				Calendar cal =  Calendar.getInstance();
																				cal.setTime(log.getDate());
																				Date temp = new Date(cal.get(Calendar.YEAR),
																						cal.get(Calendar.MONTH),
																						cal.get(Calendar.DATE),
																						cal.get(Calendar.HOUR_OF_DAY),0);
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
				%>
			</table>
		</div>

		<!-- 		ZONE DE STATS -->
		<input type="hidden" id="connexion_reussie"
			value="<%=connexion_reussie%>" /> <input type="hidden"
			id="connexion_echouee" value="<%=connexion_echouee%>" />
		<div id="graphs"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<div id="chart_div" style="width: 900px; height: 500px;"></div>

		<script type="text/javascript">
			
		<%String tmpDates="";
				String tmpNbConnexion="";
				int i =0;
				for(Date mapKey : map.keySet()){
					tmpDates+="'"+mapKey.toGMTString()+"'";
// 					Calendar cal2 =  Calendar.getInstance();
// 					cal2.setTime(mapKey);
// 					tmpDates+="'["+cal2.YEAR+","+cal2.MONTH+","+cal2.DATE+","+cal2.HOUR_OF_DAY+",0]'";
					tmpNbConnexion+=+map.get(mapKey);
					if(i<map.size()-1){
						tmpDates+=",";
						tmpNbConnexion+=",";
					}
					i++;
				}%>
			var dates = [
		<%=tmpDates%>
			];
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