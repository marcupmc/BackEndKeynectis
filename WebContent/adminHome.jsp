<%@page import="model.AuthorityParameters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AUTOSign Administration Page</title>

<link rel="stylesheet" href="css/generalHomePages.css" />

</head>
<body>

	<div class="container">

		<h2>ADMINISTRATION DE LA SESSION METIER</h2>
		
		<%
			AuthorityParameters autho = (AuthorityParameters)request.getAttribute("authorityParameter");
			request.setAttribute("authorityParameter", autho);
			
			String authorit = "";
			String urlTypes ="typeCertifConfig.jsp?autho=";
			if (null == autho) 
			{
				
			}
			else
			{
				 authorit = autho.getAuthority();
				 urlTypes+=authorit;
			}
		%>
		
		<!-- 		Zone réservée aux messages d'erreurs -->
		<%
			String msg = (String) request.getParameter("error");
			if (msg != null)
			{
				String message = "";
				if ((("error").equals(msg)))
				{

					message = "Une erreur s'est produite lors du processus de sauvegarde des types. Merci de contacter l'IT pour débuggage.";
		%>
		<div class="alert alert-block alert-error fade in">
			Erreur :
			<%=message%></div>
		<%
				}
			}
		%>
		<!-- 		Fin de la zone des messages d'erreurs -->
		
		<div class="underTitle">
			<h3>ADMINISTRATION DU SERVEUR METIER</h3>
		</div>

		<ul class="thumbnails" style="text-align: center;">
			<li class="span3"><a href="parametrage.jsp">

					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="Image Fichier de configuration" src="img/tools.png">
						<div class="caption">
							<h3>Configurations</h3>
						</div>
					</div>
			</a></li>

<!-- 			<li class="span3"><a href="#"> -->
<!-- 					<div class="thumbnail"> -->
<!-- 						<img style="width: 140px; height: 140px;" class="img-rounded" -->
<!-- 							alt="stylo" src="img/stylo.png"> -->
<!-- 						<div class="caption"> -->
<!-- 							<h3>Signature</h3> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 			</a></li> -->

			<li class="span3"><a href="<%=urlTypes%>">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded" alt="stylo" src="img/stylo.png">
						<div class="caption">
							<h3>Certifications</h3>
						</div>
					</div>
			</a></li>

			<li class="span3"><a href="ShowLogs">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="stylo" src="img/logs.png">
						<div class="caption">
							<h3>Logs</h3>
						</div>
					</div>
			</a></li>
		</ul>


		<div class="underTitle">
			<h3>ADMINISTRATION DES CLIENTS</h3>
		</div>

		<ul class="thumbnails" style="text-align: center;">
			<li class="span3"><a href="addClient.jsp">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="ajout client" src="img/add.png">
						<div class="caption">
							<h3>Ajouter</h3>
						</div>
					</div>
			</a></li>

			<li class="span3"><a href="administration.jsp">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="stylo" src="img/group.png">
						<div class="caption">
							<h3>Gestion</h3>
						</div>
					</div>
			</a></li>
		</ul>



	</div>

</body>
</html>
