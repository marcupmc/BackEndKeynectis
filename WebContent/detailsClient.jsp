<%@page import="dao.DAOCertificationType"%>
<%@page import="domain.CertificationType"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="controller.ControllerAjoutTypeCertification"%>
<%@page import="model.TagParameter"%>
<%@page import="tools.ToolsPDF"%>
<%@page import="tools.EncoderBase64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details Client</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/detailsClient.js"></script>
</head>
<body>
	<%@page import="domain.Utilisateur"%>
	<%@page import="domain.DocumentPDF"%>
	<%@page import="java.util.ArrayList"%>
	<%
		Utilisateur client = (Utilisateur) request.getAttribute("client");
		String msg = (String) request.getAttribute("msg");
	%>
	<div class="container">
		<h2>
			Informations de :
			<%=client.getFirstName()%>
			<%=client.getLastName()%>
			<div class="rightAlign">
				<a href="adminHome.jsp" class="btn btn-info">Accueil</a>
			</div>
		</h2>
		<%
			if (msg != null)
			{
				String message = "";
				if (msg.contains("success"))
				{
					if ("success".equals(msg))
						message = "Le document a été ajouté avec succès !";
					else if ("certif_adding_success".equals(msg))
						message = "Les types de certification ont été enregistrés avec suucès!";
		%>
		<div class="alert alert-block alert-success fade in">
			<%=message%>
		</div>
		<%
			}
				else
				{
					if (msg.equals("bad_parameters"))
						message = "Erreur, le document n'a pas pu être ajouté : Veuillez remplir tous les champs du formulaires.";
					else if (msg.equals("bad_signame"))
						message = "Erreur, le document n'a pas pu être ajouté : Veuillez donner un nom à la zone de signature.";
					else if (msg.equals("signame_err"))
						message = "Erreur, le document n'a pas pu être ajouté : Le document ne contient pas de zone de signature.";
					else if ("cetif_adding_error".equals(msg))
					{
						message = "Une erreur est survenue lors de l'ajout du type "
								+ request.getAttribute("typ")
								+ " au un document "
								+ request.getAttribute("docu") + ".";
					}
		%>
		<div class="alert alert-block alert-error fade in">

			<%=message%></div>
		<%
			}
			}
		%>

		<ul>
			<li>Nom : <%=client.getLastName()%></li>
			<li>Prénom : <%=client.getFirstName()%></li>
			<li>Identifiant : <%=client.getIdentifiant()%></li>
			<li>Email : <%=client.getEmail()%></li>
			<li>Telephone : <%=client.getPhoneNumber()%></li>
		</ul>

		<div>
			<%
				if (client.getSignature() == null)
				{
			%>
			<h4>Aucune Signature Enregistrée</h4>
			<%
				}
				else
				{

					byte[] decode = EncoderBase64.encodingBlobToByteArray(client
							.getSignature());
					String chaine = EncoderBase64.byteArraytoStringBase64(decode);
			%>
			<img src="data:image/png;base64,<%="" + chaine + ""%>" />
			<%
				}
			%>
		</div>

		<h3>Liste des documents</h3>
		<form class="form-horizontal" method="post"
			action="DetailsClient?id=<%=client.getId()%>">
			<table class="table">
				<tr>
					<th>Nom du document</th>
					<th>Est Certifié</th>
					<th>Signable</th>
					<th>Type de certification</th>
					<th></th>
					<th></th>
				</tr>
				<%
					for (DocumentPDF doc : client.getDocuments())
					{
						boolean signable;
						if (doc.getSignatures().size() > 0 && null != doc.getType())
							signable = true;
						else
						{
							signable = false;
						}
				%>

				<%
					if (signable)
						{
				%><tr class="alert alert-block alert-success">
					<%
						}
							else
							{
					%>
				
				<tr class="alert alert-block alert-unready">
					<%
						}
					%>

					<td>
						<%-- 				<a href="<%=doc.getUrl()%>"> --%> <a href="#myModal2"
						data-toggle="modal" onclick="showPDF('<%=doc.getId()%>')"> <%=doc.getName()%></a>
					</td>
					<td>
						<%
							if (doc.getCertified().equals("Certifie"))
								{
						%> Oui <%
							}
								else
								{
						%> Non <%
							}
						%>
					</td>
					<td>
						<%
							if (signable)
								{
						%> Oui <%
							}
								else
								{
						%> Non <%
							}
						%>
					</td>
					<td id="<%=doc.getId()%>"><select name="<%=doc.getName()%>"
						id="selectType">
							<option>Choisir un type</option>
							<%
								for (CertificationType type : ControllerAjoutTypeCertification
											.getInstance().getCertifsFromDAO())
									{
							%>
							<option
								<%if (null != doc.getType()
							&& type.getName().equals(doc.getType().getName()))
					{%>
								selected="selected" <%}%> value="<%=type.getName()%>"><%=type.getName()%>
							</option>
							<%
								}
							%>
					</select></td>
					<td><a
						href="DeleteDocument?idDocument=<%=doc.getId()%>&idClientDoc=<%=doc.getOwner().getId()%>"
						class="btn btn-small btn-danger">Supprimer</a></td>
					<td>
						<%
							if (!signable)
								{
						%> <a href="RedirectSignature?id=<%=doc.getId()%>"
						class="btn btn-small btn-info">Ajouter Signature</a> <%
 	}
 %>
					</td>
				</tr>
				<%
					}
				%>
			</table>

			<div class="control-group">
				<div class="controls">
					<div class="rightAlign">
						<button type="submit" class="btn btn-success">Enregistrer</button>
					</div>

				</div>
			</div>

		</form>

		<form method="post" action="AddDocument" id="addContactForm">
			<input type="hidden" id="idClient" name="idClient"
				value="<%=client.getId()%>" /> <input type="hidden" id="nbDoc"
				name="nbDoc" value="1" /> <input type="hidden" id="containsSign"
				name="containsSign" value="non" />
			<div class="control-group" id="documentsClient">
				<div id="t0">
					<div class="controls">
						<label class="control-label" for="name0">Nom du document :</label>
						<input type="text" id="name0" name="name0" placeholder="Nom"
							required="required"> <label class="control-label"
							for="url0"> URL du document: </label> <input type="text"
							id="url0" name="url0" placeholder="URL" required="required">
						<label for="isSign" class="control-label">Le document
							contient-il déjà une signature ? :</label> <label class="radio">
							<input onclick="signOUI()" type="radio" name="isSign" value="oui"
							id="oui">Oui
						</label> <label class="radio"> <input onclick="signNON()"
							type="radio" name="isSign" value="non" id="non" checked>Non
						</label>
						<div id="champsAjout"></div>
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-primary"
				value="Ajouter le document" />
		</form>
	</div>


	<!-- 	MODAL ICI -->
	<div id="myModal2" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 800px; margin-left: -400px;">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>PDF Viewer</h3>
		</div>
		<div class="modal-body" style="height: 800px;" id="pdfViewer"></div>
		<div class="modal-footer">
			<a class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
		</div>


	</div>
	<!-- 	FIN MODAL 	 -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>