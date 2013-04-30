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
		<div class="underTitle">
			<h3>ADMINISTRATION DU SERVEUR METIER</h3>
		</div>

		<ul class="thumbnails" id="todo" style="text-align: center;">
			<li class="span3"><a href="#">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="Image Fichier de configuration" src="img/tools.png">
						<div class="caption">
							<h3>Configurations</h3>
						</div>
					</div>
			</a></li>

			<li class="span3"><a href="#">
					<div class="thumbnail">
						<img style="width: 140px; height: 140px;" class="img-rounded"
							alt="stylo" src="img/stylo.png">
						<div class="caption">
							<h3>Signature</h3>
						</div>
					</div>
			</a></li>
		</ul>


		<!-- 	<div class="text-info"><h3>ADMINISTRATION DES DONNEES METIERS</h3></div> -->

		<!-- 	<div class="row-fluid"> -->
		<!-- 		<div class="span12"> -->
		<!-- 			<div class="row-fluid"> -->
		<!-- 				<div class="span2"> -->
		<!-- 					<div class="icones"> -->
		<!-- 						<a class="btn btn-primary" href="#"> -->
		<!-- 							<img class="img-rounded" alt="Image Fichier de configuration" src="img/icone_fichier_config.png"> -->
		<!-- 							Configuration -->
		<!-- 						</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->

		<!-- 				<div class="span2"> -->
		<!-- 					<div class="icones"> -->
		<!-- 						<a class="btn btn-primary" href="#"> -->
		<!-- 							<img class="img-rounded" alt="Image Signature M�tier" src="img/signature_papier.png"> -->
		<!-- 							Signature -->
		<!-- 						</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div>			 -->
		<!-- 		</div> -->
		<!-- 	</div> -->

		<div class="underTitle">
			<h3>ADMINISTRATION DES CLIENTS</h3>
		</div>

		<!-- 		<div class="row-fluid"> -->
		<!-- 			<div class="span12"> -->
		<!-- 				<div class="row-fluid"> -->
		<!-- 					<div class="span2"> -->
		<!-- 						<div class="icones"> -->
		<!-- 							<a class="btn btn-primary" href="addClient.jsp"> <img -->
		<!-- 								class="img-rounded" alt="Image Fichier de configuration" -->
		<!-- 								src="img/ajout_client.png"> Ajout -->
		<!-- 							</a> -->
		<!-- 						</div> -->
		<!-- 					</div> -->

		<!-- 					<div class="span2"> -->
		<!-- 						<div class="icones"> -->
		<!-- 							<a class="btn btn-primary" href="administration.jsp"> <img -->
		<!-- 								class="img-rounded" alt="Image Signature M�tier" -->
		<!-- 								src="img/gestion_clients.png"> Gestion -->
		<!-- 							</a> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->

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