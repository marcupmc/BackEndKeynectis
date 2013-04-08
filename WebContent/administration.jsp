<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des clients</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@page import="domain.Utilisateur"%>
	<%@page import="java.util.ArrayList"%>
	<%
		ArrayList<Utilisateur> lcontact = new ArrayList<Utilisateur>();
		if (request.getAttribute("liste") == null) {

		} else {
			lcontact = (ArrayList<Utilisateur>) request
					.getAttribute("liste");
		}
	%>
	<div class="container">
		<h2>Gérer vos Clients</h2>
		<form class="form-search" method="get" action="FindClient">
			<input type="text" name="recherche" class="input-medium search-query">
			<button type="submit" class="btn">Search</button>
		</form>
		<a class="btn btn-primary" href="addClient.jsp">Ajouter un client</a>
		<div><br/>
			<%
				if (lcontact.size() > 0) {
			%>
			<table class="table table-hover">
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
					<td><a href="DetailsClient?id=<%= c.getId() %>" class="btn btn-small btn-info" type="buttons">Consulter
							les informations</a></td>
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