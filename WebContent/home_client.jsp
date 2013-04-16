<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ma page de signature</title>

<!---------------------------------------------------------------------------------------->

<!-- Importation des librairies -->
<script src="js/jquery-1.9.1.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="libs/flashcanvas.js"></script>
	<![endif]-->
<script src="js/jSignature.min.js"></script>
<script src="js/modernizr.js"></script>
<script src="js/signature.js"></script>
<script src="js/bootstrap.min.js"></script>
<!---------------------------------------------------------------------------------------->


<!---------------------------------------------------------------------------------------->

<link href="css/signature.css" rel="stylesheet" media="screen">
<link href="css/style.css" rel="stylesheet" media="screen">
</head>
<body>
	<%@page import="domain.Utilisateur"%>
	<%@page import="domain.DocumentPDF"%>
	<%@page import="java.util.ArrayList"%>
	<%
		Utilisateur client = (Utilisateur) request.getAttribute("client");
	%>
	<!---------------------------------------------------------------------------------------->

	<h2>
		Bonjour
		<%=client.getFirstName()%>
		<%=client.getLastName()%></h2>
	<div id="parametre">
		<a href="#myModal2" data-toggle="modal" class="btn btn-primary">Editer
			votre signature</a>
	</div>
	<form id="certificationForm" name="certificationForm">
		<table class="table table-striped" id="listePDF">
			<tr>
				<th>Nom du document</th>
				<th>Est Certifié</th>
				<th>Sélection</th>
			</tr>
			<%
				for (DocumentPDF doc : client.getDocuments())
				{
			%>
			<tr>
				<td><a href="<%=doc.getUrl()%>"><%=doc.getName()%></a></td>
				<td>
					<%
						if (doc.isCertified())
							{
					%> Oui <%
						}
							else
							{
					%> Non <%
						}
					%>
				</td>
				<td><a
					href="DeleteDocument?idDocument=<%=doc.getId()%>&idClientDoc=<%=doc.getOwner().getId()%>"
					class="btn btn-small btn-danger">Supprimer</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<button type="submit" class="btn btn-success" id="certifier">Certifier
			la sélection</button>
	</form>

	<div>
		<p>Votre Signature :</p>
		<div id="displayarea">
			<div id="printPicture"></div>
		</div>
	</div>




	<!---------------------------------------------------------------------------------------->
	<div id="myModal2" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>Signer ici :</h3>
		</div>

		<div class="modal-body">
			<div id="signatureparent" style="width: 441px;">
				<div id="signature" style="height: 118px; width: 400px;"></div>
			</div>
			<div id="tools"></div>
		</div>
		<div class="modal-footer">
			<button onclick="showSignature()" class="btn btn-primary">Afficher</button>
			<button onclick="resetSignature()" class="btn btn-success">Effacer</button>
		</div>
	</div>

	<div id="scrollgrabber"></div>
</body>
</html>