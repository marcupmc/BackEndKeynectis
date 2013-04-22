<%@page import="domain.DocumentPDF"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/signatureEditor.js"></script>
<title>Insert title here</title>
<style>
#draggable {
	margin-top: 10px;
	margin-bottom: 10px;
	width: 150px;
	height: 50px;
	padding: 0.5em;
	border: 1px solid black;
	cursor: move;	
}

#save{
	display:none;
}

#pdfZone {
	height: 842px;
	width: 596px;
}
</style>
<script>
	$(function() {
		$("#draggable").draggable();
	});
</script>
</head>
<body>
	<div class="container">
		<%
			DocumentPDF doc = (DocumentPDF) request.getAttribute("doc");
		%>

		<h2>Editeur de zone de signature</h2>
		<h3>Infos du document</h3>
		<ul>
			<li>Nom : <%=doc.getName()%></li>
			<li>Url : <%=doc.getUrl()%></li>
			<li>Propriétaire : <%=doc.getOwner().getFirstName()%> <%=doc.getOwner().getLastName()%></li>
		</ul>

		<label for="hauteur">Hauteur (en pixel) : <input type="number"
			id="hauteur"></label> <label for="largeur">Largeur (en pixel)
			: <input type="number" id="largeur">
		</label>
		<button class="btn btn-success" id="valider" onclick="buildZone()">Valider</button>

		<div id="draggable" class="ui-widget-content">
			<p>Zone de Signature</p>
		</div>
		<div>
			<embed id="pdfZone" src="http://www.marc-gregoire.fr/pdf/cv.pdf">
		</div>
		<button class="btn btn-info" onclick="fixeZone()">Définir</button>
		<button class="btn btn-warning" onclick="reset()">Reset</button>
		
		<form action="AddSignaturePDF" method="POST">
			<input type="hidden" id="id" name="id" value="<%=doc.getId() %>" />
			<input type="hidden" id="height" name="height" />
			<input type="hidden" id="width" name="width" />
			<input type="hidden" id="valPDFX" name="valPDFX" />
			<input type="hidden" id="valPDFY" name="valPDFY" />
			<input type="hidden" id="valX" name="valX" />
			<input type="hidden" id="valY" name="valY" />
			<button class="btn btn-primary" type="submit" id="save">Enregistrer</button>
		</form>
	</div>
</body>
</html>