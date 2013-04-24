<%@page import="domain.DocumentPDF"%>
<%@page import="dao.DAODocumentPDF"%>
<%@ page import="dao.DAOUtilisateur"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fin Certification</title>
</head>
<body>
<div class="container">
	<div class="alert alert-success">
		<h2> Certification reussie</h2>
		<p>Vous allez etre redirige</p>
	</div>
</div>

<%

String identifiant=(String)request.getParameter("identifiant");
long id = Long.parseLong((String)request.getParameter("id"));
String urlNew  =(String)request.getParameter("urlnew");

DAODocumentPDF.getInstance().changeUrl(id, urlNew);
DAODocumentPDF.getInstance().certifiedPDF(id);

%>

</body>
</html>