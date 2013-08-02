<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un nouveau Client</title>
<!-- <link rel="stylesheet" href="css/style.css" /> -->
<link rel="stylesheet" href="css/orange_style.css" />
</head>
<body>
	<div class="container">
		<h2>
			Ajouter un nouveau Client <a href="administration.jsp"
				class="btn btn-info">Accueil</a>
		</h2>

		<!-- 		Zone réservée aux messages d'erreurs -->
		<%
			String msg = (String) request.getParameter("error");
			if (msg != null)
			{
				String message = "";
				if (msg.equals("error_add"))
					message = "Erreur lors de l'ajout du client dans la base de données";
				else if (msg.equals("empty_field"))
					message = "Veuillez remplir tous les champs";
				else if (msg.equals("missmatch_pwd"))

					message = "Les mots de passes doivent être identiques";
		%>
		<div class="alert alert-block alert-error fade in">
			Erreur :
			<%=message%></div>
		<%
			}
		%>
		<!-- 		Fin de la zone des messages d'erreurs -->


		<!-- 		Formulaire d'ajout d'un client -->
		<form class="form-horizontal" method="post" action="AddNewClient">

			<div class="control-group">
				<label class="control-label" for="inputIdentifiant">Identifiant
					: </label>
				<div class="controls">
					<input type="text" id="inputIdentifiant" name="inputIdentifiant"
						placeholder="Identifiant" required="required">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="inputName">Nom : </label>
				<div class="controls">
					<input type="text" id="inputName" name="inputName"
						placeholder="Nom" required="required">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputFirstName">Prénom : </label>
				<div class="controls">
					<input type="text" id="inputFirstName" name="inputFirstName"
						placeholder="Prénom" required="required">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEmail">Email : </label>
				<div class="controls">
					<input type="email" id="inputEmail" name="inputEmail"
						placeholder="Email" required="required">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPhone">Telephone : </label>
				<div class="controls">
					<input type="text" id="inputPhone" name="inputPhone"
						placeholder="Telephone" required="required">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="inputPassword">Mot de
					passe : </label>
				<div class="controls">
					<input type="password" id="inputPassword" name="inputPassword"
						placeholder="Mot de passe" required="required">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="inputConfirm">Confirmer le
					mot de passe : </label>
				<div class="controls">
					<input type="password" id="inputConfirm" name="inputConfirm"
						placeholder="Confirmation mot de passe" required="required">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">

					<button type="submit" class="btn">Enregistrer</button>
				</div>
			</div>
		</form>
		<!-- 		Fin du formulaire d'ajout d'un client -->

	</div>

</body>
</html>
