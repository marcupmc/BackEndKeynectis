<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Template pour client</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />
<!-- <link rel="stylesheet" href="css/orange_style.css" /> -->
<!-- <link rel="stylesheet" href="css/orange_style3.css" /> -->

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/templateToClient.js"></script>
</head>
<body>

	<div class="container">
		<h2>Génération de documents pour client</h2>

		<div>
			<a class="btn btn-primary" onclick="getTemplate()" href="#modalTemplate"	data-toggle="modal" >Sélectionner un template</a>
			<a class="btn btn-info" onclick="getClient()" href="#modalClient"	data-toggle="modal">Sélectionner un client</a>
			
		</div>
		
		<div id="infoClient" style="display: none;">
		</div>
		
		<div id="infoTemplate" style="display: none;">
		</div>
		
		<div>
			<form id="formLifecycle">
				<div id="champsClient"></div>
				<div id="champsTemplate"/></div>
			</form>
		</div>
	</div>
	
	<!-- 	MODAL Template -->
	<div id="modalTemplate" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 800px; margin-left: -400px;">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>Sélectionner un Template</h3>
		</div>
		<div class="modal-body" style="height: 500px;"id="clientViewer">
			<table class="table table-hover" id="tableTemplate">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Url</th>
			</tr>
			
			</table>
		</div>
		<div class="modal-footer">
			<a class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
		</div>

	</div>
	
	
	<!-- 	MODAL Client -->
	<div id="modalClient" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 800px; margin-left: -400px;">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>Sélectionner un client</h3>
		</div>
		<div class="modal-body" style="height: 500px;"id="clientViewer">
			<table class="table table-hover" id="tableClient">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prénom</th>
			</tr>
			
			</table>
		</div>
		<div class="modal-footer">
			<a class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
		</div>


	</div>
	<!-- 	FIN MODAL 	 -->

	<script src="js/bootstrap.min.js"></script>
</body>
</html>