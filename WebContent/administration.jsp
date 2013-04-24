<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des clients</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />

</head> 
<body>
	<%@page import="domain.Utilisateur"%>
	<%@page import="java.util.ArrayList"%>
	<%
		boolean affichetab;
		ArrayList<Utilisateur> lcontact = new ArrayList<Utilisateur>();
		if (request.getAttribute("liste") == null)
		{
			affichetab=false;
		}
		else
		{
			affichetab=true;
			lcontact = (ArrayList<Utilisateur>) request
					.getAttribute("liste");
		}
	%>
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h2>Gérer vos Clients <a href="adminHome.jsp" class="btn btn-info">Accueil</a></h2>
				
				<div class="rightAlign">
					<form class="form-search" method="get" action="FindClient">
						<input type="text" name="recherche" class="input-medium search-query">
						<button type="submit" class="btn">Search</button>
					</form>
				</div>
				<a class="btn btn-primary" href="addClient.jsp">Ajouter un client</a>
			</div>
		</div>
		
		
		<div class="row-fluid">
<!-- 		<div class="span12"> -->
<!-- 			<div class="row-fluid">				 -->
				
<!-- 					<a class="btn btn-primary" href="addClient.jsp">Ajouter un client</a> -->
						
<!-- 			</div>			 -->
<!-- 		</div> -->
	</div>
	
	<%if(affichetab){ %>
	<div class="underTitle"><h3>LISTE DES CLIENTS</h3></div>
	<%} %>
		
		
		
		
<!-- 		<a class="btn btn-info" href="CertifierDocument">Certifier un Document</a> -->
		<div id="resultats"><br/>
			<%
				if (lcontact.size() > 0) {
			%>
			<table class="table table-striped">
				<tr>

					<th>Prenom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>Nombre de documents</th>
					<th></th>
					<th></th>

				</tr>
				<%
					for (int i = 0; i < lcontact.size(); i++) {
							Utilisateur c = lcontact.get(i);
				%>
				<tr>

					<td><%=c.getFirstName()%></td>
					<td><%=c.getLastName()%></td>
					<td><%=c.getEmail()%></td>
					<td><%=c.getPhoneNumber()%></td>
					<td><%= c.getDocuments().size() %></td>
					<td><a href="DetailsClient?id=<%= c.getId() %>" class="btn btn-small btn-info" type="buttons">Détails</a></td>
					<td><a href="DeleteClient?idClient=<%=c.getId() %>" class="btn btn-small btn-danger" type="button">Supprimer</a></td>
				<tr>
					<%
						}
					%>
				
			</table>
			<%
				}
			%>
		</div>

	</div>
	
	
	

</body>
</html>