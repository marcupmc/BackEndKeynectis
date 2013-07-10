
<%@page import="controller.ControllerParameter"%>
<%@page import="model.KeynectisParameters"%>
<%@page import="model.AuthorityParameters"%>
<%@page import="controller.ControllerAjoutTypeCertification"%>
<%@page import="model.TagParameter"%>
<%@page import="model.TagParameters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />


<%@page import="domain.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%
	/* boolean typesExist;

	String saveFile = this.getServletContext()
			.getRealPath("/temp_xml/");

	String act = request.getParameter("act");

	ControllerAjoutTypeCertification controller = ControllerAjoutTypeCertification
			.getInstance(); */

	TagParameters types = (TagParameters)request.getAttribute("types");//controller.getParameters();
	/* if (null != act)
	{
		if ("firstAccess".equals(act))
			types = controller.getParameters(saveFile);
	}

	if (types.isEmpty()) //  request.getAttribute("types") == null) 
	{
		typesExist = false;
	}
	else
	{
		typesExist = true;
		//types = (TagParameters) request.getAttribute("types");
	} */
%>

<%
	

	AuthorityParameters autho = (AuthorityParameters)request.getAttribute("authorityParameter");//ControllerParameter.getInstance().getAutho();
	request.setAttribute("authorityParameter", autho);
	
	String authorit = "";
	
	if(null!=autho)
		authorit = autho.getAuthority();//request.getParameter("autho");
	
%>

<div class="container">

	<div class="row-fluid">
		<div class="span12">
			<h2>
				Configuration des types de certification <%= authorit %>
				<div class="rightAlign">
					<a href="adminHome.jsp" class="btn btn-info">Accueil</a>
				</div>
			</h2>
		</div>
	</div>

<%if( null != authorit )  
	{
	
		if("".equals(authorit))
		{
			%>
			<!-- 		Zone réservée aux messages d'erreurs -->
			<%
				
				String message = "Cette page affiche la liste des types de certifications correspondant à l'autorité de certification utilisée mais vous n'avez choisi aucune autorité.\n Veuillez vous rendre dans les configurations et y choisir l'autorité à utiliser.";
			%>
			<div class="alert alert-block alert-error fade in">
				Erreur :
				<%=message%></div>
			
			<!-- 		Fin de la zone des messages d'erreurs -->
		<%
		}
		else if("KWS_INTEGRATION_CDS".equals(authorit))
		{
%>
		
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4">
				<div class="pull-left">
					<div class="well" id="tagComment">
					
						<p>
							<b>Types de certification</b><br> <br> Ici vous pouvez
							paramétrer les différents types de certification KWS opérables par vos
							clients.<br> Les informations à remplir pour ce faire sont:<br>
							<br> -> L'identifiant du type: il s'agit du numéro de ertification
							utilisé par le portail KWS<br> -> Le nom du type: mettez de
							préférence un nom qui représente le cas métier dans lequel ce type sera
							utilisé<br> -> Le motif de la certification: information
							facultative, si remplie, elle pourra être prise en compte et apparaître
							sur la signature client<br> -> Le lieu de la signature:
							information facultative, si remplie, elle pourra être prise en compte
							et apparaître sur la signature client<br> -> Le contact du
							responsable de cette opération métier<br> Vous pouvez ajouter
							autant de type que vous voulez, à condition de respecter les
							identifiants KWS. <br> Pour ce faire, appuyer sur le bouton +Type
							en dessous de la liste des types. <br> <br> Ces informations
							seront utilisées lors de opérations de certification une fois que vous
							aurez appuyé sur VALIDER.<br> Vous pouvez les modifier à tout
							moment en revenant sur cette page.
						
						</p>
					
					</div>
				</div>
			</div>
			<div class="span8">
				<div class="well">

					<div id="tagList">
					
						<div class="underTitle">
							<h4>LISTE DES TYPES DE CERTIFICATION</h4>
						</div>
					
						<!-- 		<a class="btn btn-info" href="CertifierDocument">Certifier un Document</a> -->
						<div id="resultats">
							<br />
							<form class="form-horizontal" method="get" action="TypesXmlSaving">
								<table class="table table-striped">
									<thead>
										<tr>
					
											<th></th>
											<th>Nom</th>
											<th>Type</th>
											<th>Par défaut</th>
											<th></th>
											<th></th>
					
										</tr>
									</thead>
									<tbody>
										<%
											/* if (typesExist)
											{ */
											if (types.getTypes().size() > 0)
											{
												for (TagParameter type : types.getTypes())
												{
										%>
										<tr>
					
											<td></td>
											<td><%=type.getName()%></td>
											<td><%=type.getId_type()%></td>
											<td><label class="radio"> <input id="optionDefault"
													type="radio" name="optionDefault" value="<%=type.getName()%>"
													<%if (type.isDefaut())
										{%> checked="checked" <%}%>>
					
											</label></td>
											<td><a
												href="AddType?action=modify&id=<%=type.getId_type()%>&name=<%=type.getName()%>"
												class="btn btn-small btn-info" type="buttons">Editer</a></td>
											<td><a
												href="AddType?action=delete&id=<%=type.getId_type()%>&name=<%=type.getName()%>"
												class="btn btn-small btn-danger" type="button">Supprimer</a></td>
										</tr>
										<%
											}
					
												/* String option = request.getParameter("optionDefault");
					
												request.setAttribute("optionDefault", option); */
											}
											//}
										%>
										<tr>
											<td><a href="addType.jsp" class="btn btn-small btn-primary"
												type="buttons"><i class="icon-plus-sign icon-white"></i>Type</a></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
						</div>
					
						<div class="well">
							<div class="control-group">
								<div class="controls">
									<div class="rightAlign">
										<!-- <a href="TypesXmlSaving" class="btn btn-success">Enregistrer</a> -->
										<button type="submit" class="btn btn-success">Enregistrer</button>
									</div>
					
								</div>
							</div>
							<!-- <div class="leftAlign">
								<div class="controls">
									<a class="btn btn-inverse" href="parametrage.jsp?previous=const">Onglet précédent</a>
								</div>
							</div> -->
						</div>
					
					
						</form>
					
					
					</div>
					
					</div>

				</div>
			</div>
		</div>

	</div>

<%
		}
	}
%>
