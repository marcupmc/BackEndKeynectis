<%@page import="tools.EncoderBase64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details Client</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@page import="domain.Utilisateur"%>
	<%@page import="domain.DocumentPDF"%>
	<%@page import="java.util.ArrayList"%>
	<%
		Utilisateur client = (Utilisateur) request.getAttribute("client");
	%>
	<div class="container">
		<h2>
			Informations de :
			<%=client.getFirstName()%>
			<%=client.getLastName()%> <a href="administration.jsp" class="btn btn-info">Accueil</a></h2>
			
			
		<ul>
			<li>Nom : <%=client.getLastName()%></li>
			<li>Prénom : <%=client.getFirstName()%></li>
			<li>Identifiant : <%=client.getIdentifiant()%></li>
			<li>Email : <%=client.getEmail()%></li>
			<li>Telephone : <%=client.getPhoneNumber()%></li>
		</ul>
		
		<div>
			<%if(client.getSignature()==null){
				%>
				<h4>Aucune Signature Enregistrée</h4>
			<% }else{
			
				byte[] decode = EncoderBase64.encodingBlobToByteArray(client.getSignature());
				String chaine = EncoderBase64.byteArraytoStringBase64(decode);
			%>
				<img src="data:image/png;base64,<%=chaine%>"/>
			<%} %>
		</div>
		
		<h3>Liste des documents</h3>
			<table class="table table-striped">
 				<tr>
 					<th>Nom du document</th>
 					<th>Est Certifié </th>
 					<th></th>
 					<th></th>
 				</tr>
 				<%
				for (DocumentPDF doc : client.getDocuments()) {
					boolean signable;
					if(doc.getSignatureName().length()>0)
						signable =true;
					else{
						if(doc.getSignatureX()==0 && doc.getSignatureY()==0)
							signable=false;
						else
							signable=true;
					}
				%>
				<tr>
					<td><a href="<%=doc.getUrl()%>"><%=doc.getName()%></a></td>
					<td>
						<%if(doc.isCertified()){ %>
						Oui
						<%}else{ %>
						Non
						<%} %>
					</td>
					<td><a href="DeleteDocument?idDocument=<%=doc.getId() %>&idClientDoc=<%=doc.getOwner().getId() %>" class="btn btn-small btn-danger">Supprimer</a></td>
					<td><a href="RedirectSignature?id=<%=doc.getId() %>" class="btn btn-small btn-info">Ajouter Signature</a></td>
				</tr>
				<%
				}
			%>
			</table>
		

		<form method="post" action="AddDocument" id="addContactForm">
			<input type="hidden" id="idClient" name="idClient" value="<%=client.getId() %>" />
			<input type="hidden" id="nbDoc" name="nbDoc" value="1" />
			<div class="control-group" id="documentsClient">
				<div id="t0">
					<div class="controls">
						<label class="control-label" for="name0">Nom du document :
						</label> <input type="text" id="name0" name="name0" placeholder="Nom"
							required="required"> <label class="control-label"
							for="url0"> URL du document: </label> <input type="text"
							id="url0" name="url0" placeholder="URL" required="required">
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-primary"
				value="Ajouter le document" />
		</form>
	</div>

</body>
</html>