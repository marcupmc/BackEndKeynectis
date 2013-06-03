<%@page import="domain.DocumentPDF"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/pdfReader.js"></script>
<title>Signature PDF Editor</title>

<style type="text/css">
#pdfreader {
	height: 800px;
	width: 600px;
}

.imgSig {
	padding: 0.5em;
	border: 1px solid black;
	position: absolute;
}

.draggable {
	margin-top: 10px;
	margin-bottom: 10px;
	width: 200px;
	height: 60px;
	padding: 0.5em;
	border: 1px solid black;
	cursor: move;
}

#liste {
	vertical-align: top;
}

/* #fixer { */
/* 	display: none; */
/* } */

#loader {
	display: none;
}

#saveSignatures {
	display: none;
}

#previous{
	display : none;
}

#next{
	display : none;
}
</style>

</head>
<body>
	<%
		DocumentPDF doc = (DocumentPDF) request.getAttribute("doc");
	%>
	<input type="hidden" value="<%=doc.getUrl()%>" id="url" />
	<input type="hidden" value="<%=doc.getId()%>" id="idDoc" />
	<input type="hidden" value="<%=doc.getOwner().getId()%>" id="idOwner" />
	<div class="container">

		<h3>Lecteur PDF</h3>
		<button onclick="previousPage()" id="previous" class="btn btn-primary">
			<i class="icon-backward icon-white"></i>
		</button>
		<button id="next" onclick="nextPage()" class="btn btn-success">
			<i class="icon-forward icon-white"></i>
		</button>
		<button onclick="createSignature()" id="createSignature"
			class="btn btn-info">Ajouter une signature</button>
<!-- 		<button onclick="fixer()" id="fixer" class="btn btn-info">Fixer -->
<!-- 			la signature</button> -->

		<table>
			<tr>
				<td>
					<div id="pdfreader">
						<img src="img/loader.gif" id="loader" /> <img src="" id="imagePDF" />
					</div>

				</td>
				<td id="liste">

					<h5>Liste des signatures enregistrées :</h5>
					<ul id="listeSignatures">
					</ul>

					<div id="signatures"></div>
					<button class="btn btn-success" onclick="saveSignatures()"
						id="saveSignatures">Enregistrer les signatures</button>
				</td>
			</tr>

		</table>

	</div>

</body>
</html>