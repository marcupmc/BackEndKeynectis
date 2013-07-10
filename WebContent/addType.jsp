<%@page import="model.TagParameter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter un nouveau Type de certification</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />
</head>
<body>
	<div class="container">
		<h2>
			Ajouter un nouveau Type
			<div class="rightAlign">
				<a href="adminHome.jsp" class="btn btn-info">Accueil</a>
			</div>
		</h2>

		<!-- 		Zone réservée aux messages d'erreurs -->
		<%
			String msg = (String) request.getParameter("error");
			if (msg != null)
			{
				String message = "";
				if ((("adding_type_error").equals(msg)))
				{

					message = "Un autre type possède déjà cet identifiant et/ou ce nom. Veuillez en changer et sauvegarder de nouveau.";
		%>
		<div class="alert alert-block alert-error fade in">
			Erreur :
			<%=message%></div>
		<%
				}
			}
		%>
		<!-- 		Fin de la zone des messages d'erreurs -->

		<%
			String action = "AddType?action=save";
			TagParameter type = new TagParameter();
			String option = (String) request.getParameter("option");
			if ("modify".equals(option))
			{
				type = (TagParameter) request.getAttribute("type");
				if (type != null)
				{
					action = "AddType?action=edit&id=" + type.getId_type()
							+ "&name=" + type.getName();
				}
			}
		%>

		<!-- 		Formulaire d'ajout d'un type -->
		<form class="form-horizontal" method="post" action=<%=action%>>

			<div class="control-group">
				<label class="control-label" for="typeIdentifiant">Identifiant
					: </label>
				<div class="controls">
					<input type="text" id="typeIdentifiant" name="typeIdentifiant"
						placeholder="Code du type" value="<%=type.getId_type()%>"
						required="required">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="typeName">Nom : </label>
				<div class="controls">
					<input type="text" id="typeName" name="typeName"
						placeholder="Nom du type" value="<%=type.getName()%>"
						required="required">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="reason">Motif : </label>
				<div class="controls">
					<input type="text" id="reason" name="reason"
						placeholder="Motif de la signature"
						value="<%=type.getPDF_REASON()%>">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="location">Lieu : </label>
				<div class="controls">
					<input type="text" id="location" name="location"
						placeholder="Lieu de la signature"
						value="<%=type.getPDF_LOCATION()%>">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="contact">Contact : </label>
				<div class="controls">
					<input type="text" id="contact" name="contact"
						placeholder="Téléphone du responsable de la signature"
						value="<%=type.getPDF_CONTACT()%>">
				</div>
			</div>
			
			<div class="control-group">
				<label class="checkbox" >Définir comme type par défaut				
					<div class="controls">
						<input type="checkbox" name="default" id="default" value="ON" <%if (type.isDefaut())
					{%> checked="checked" <%}%>>
					</div>					
				</label>
				
			</div>

			<div class="control-group">
				<div class="controls">
					<a href= "TypeCertifConfig"
						class="btn btn-danger">Annuler</a> <!-- "parametrage.jsp?error=success&messType=KWS" -->
					<button type="submit" class="btn btn-primary">Enregistrer</button>


				</div>
			</div>
		</form>



		<!-- 		Fin du formulaire d'ajout d'un type -->

	</div>

</body>
</html>
