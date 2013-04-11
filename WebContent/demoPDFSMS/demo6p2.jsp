<%!
private static final String PDF_FILE_NAME = "In/Test.pdf";
private static final String CERT = "CERT";
private static final String OUT = "Out";
%>
<%

    String nom=request.getParameter("nom");
    String prenom=request.getParameter("prenom");
    String email=request.getParameter("email");
	String tel = request.getParameter("tel");
    
    session.setAttribute("nom",nom);
    session.setAttribute("PDF_FILE_NAME",PDF_FILE_NAME);
	session.setAttribute("prenom",prenom);
    session.setAttribute("email",email);
	session.setAttribute("tel",tel);
	session.setAttribute("CERT",CERT);
	session.setAttribute("OUT",OUT);
%>
<html>
<head>
	<title>K.Websign</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta Http-Equiv="Cache-Control" Content="no-cache">
	<meta Http-Equiv="Pragma" Content="no-cache">
	<meta Http-Equiv="Expires" Content="0">
	<link rel="stylesheet" href="../css/style.css" type="text/css">
<script>
  function Valider()
  {
	  document.mainForm.submit();
  }
  function Annuler()
  {
    location.replace("demo6p1.jsp");
  }
  </script>
</head>
<body class="contenu" style="margin:0px;padding:0px;background-color:black;overflow-y:hidden;margin-right: 5px"  >

<table align="center" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="background-color:black; margin:0px;padding:0px">
	<tr height="10%">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td align="center" class="tdTitre" style="color:white">
		Etape 2 - Pr&eacute;sentation du document<br>&agrave; signer
		</td>
	</tr>
	<tr>
		<td id="kwebsignTD1" width="10%" align="center" class="Arial11Bold" valign="middle" style="color:white">
			&nbsp;<img src="../img/signature_contrat_electronique.gif">
			<br>
			<b style="color:white">Document sign&eacute; non modifiable</b>
		</td>
		<td id="kwebsignTD2" width="10%" align="center" valign="middle">
			<img src="../img/flecheDroite.jpg">
		</td>
		<td align="center" rowspan="2">
<!-- 			<iframe width="100%" height="100%" src="demo6p3.jsp" frameborder="0"></iframe> -->
		</td>
	</tr>
	<tr>
		<td id="kwebsignTD1" width="10%" align="center" class="Arial11Bold" valign="middle" style="color:white">
			&nbsp;<img src="../img/K.Websign.png">
			<br>
			<b style="color:white">Cadre g&eacute;n&eacute;r&eacute; par K.Websign</b>
		</td>
		<td id="kwebsignTD2" width="10%" align="center" valign="middle">
			<img src="../img/flecheDroite.jpg">
		</td>
	</tr>
</table>
</body>
</html>

