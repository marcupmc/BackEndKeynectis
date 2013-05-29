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
	boolean typesExist;
	ControllerAjoutTypeCertification controller = ControllerAjoutTypeCertification.getInstance();
	
	TagParameters types = controller.getParameters();
	if (types.isEmpty()) //  request.getAttribute("types") == null) 
	{
		typesExist = false;
	}
	else
	{
		typesExist = true;
		//types = (TagParameters) request.getAttribute("types");
	}
%>
<div class="container">

	<div class="underTitle">
		<h3>LISTE DES TYPES DE CERTIFICATION</h3>
	</div>

	<!-- 		<a class="btn btn-info" href="CertifierDocument">Certifier un Document</a> -->
	<div id="resultats">
		<br />

		<table class="table table-striped">
			<!-- <thead>
					<tr>
	
						<th></th>
						<th></th>						
						<th></th>
						<th></th>
	
					</tr>
				</thead> -->
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
					<td><a
						href="AddType?action=modify&id=<%=type.getId_type()%>&name=<%=type.getName()%>"
						class="btn btn-small btn-info" type="buttons">Editer</a></td>
					<td><a href="#" class="btn btn-small btn-danger" type="button">Supprimer</a></td>
				</tr>
				<%
							}
						}
					//}
				%>
				<tr>
					<td><a href="addType.jsp" class="btn btn-small btn-primary"
						type="buttons"><i class="icon-plus-sign icon-white"></i>Type</a></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>

	</div>

	<div class="control-group">
		<div class="controls">
			<div class="rightAlign">
				<a href="TypesXmlSaving" class="btn btn-success">Enregistrer</a>
			</div>

		</div>
	</div>

</div>
