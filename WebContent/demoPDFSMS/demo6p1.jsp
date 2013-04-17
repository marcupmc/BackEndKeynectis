<%!
private static final String PDF_FILE_NAME = "In/Test.pdf";
%>

<html>
<head>
	<title>K.Websign</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta Http-Equiv="Cache-Control" Content="no-cache">
	<meta Http-Equiv="Pragma" Content="no-cache">
	<meta Http-Equiv="Expires" Content="0">
	<link rel="stylesheet" href="../css/style2.css" type="text/css">
<script>
  function Valider()
  {
	    if(document.getElementsByName("tel")[0].value.length==0)
	    {
	    	alert("Veuillez saisir le num"+unescape('%E9')+"ro de t"+unescape('%E9')+"l"+unescape('%E9')+"phone.");
	    }
	    else
	    {
	    	document.mainForm.submit();
	    }
  }
  function Annuler()
  {
    location.replace("demo6p1.jsp");
  }
  </script>
</head>
<body class="contenu" style="margin:0px;padding:0px;background-color:black;overflow-y:hidden;margin-right: 5px"  >

<table align="center" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="background-color:black; margin:0px;padding:0px">
	<tr>
		<td width="10%" align="center" rowspan="6" class="Arial11Bold" valign="middle" style="color:white">
			<img src="../img/signature_contrat_electronique.gif">
			<br>
			<b>Document &agrave; faire signer</b>
		</td>
		<td width="10%" align="center" rowspan="6" valign="middle">
			<img src="../img/flecheDroite.jpg">
		</td>
	</tr>
	<tr height="50px">
		<td width="100%" align="center" nowrap class="tdTitre" style="color:white" colspan="2">
		Etape 1 - Cr&eacute;ation du document
		</td>
	</tr>
	<tr>

	</tr>
	<tr style="background-color:white">
		<td align="center" colspan="2">
			<input type="button" class="AllButton" onclick="window.open('../showPDF.jsp?PDFfileName=<%=PDF_FILE_NAME%>');" value="Voir">
		</td>
	</tr>
	<tr style="background-color:white">
		<td align="center" colspan="2">
		<form method="post" name="mainForm" action="demo6p2.jsp">
		<table border="0" cellpadding="0" cellspacing="2" style="background-color:white">
			<tr>
				<td class="Arial11Bold" nowrap>Nom&nbsp;</td>
				<td><input type="text" name="nom" class="EditBox" value="Dupont"></td>
			</tr>
			<tr>
				<td class="Arial11Bold" nowrap>Pr&eacute;nom&nbsp;</td>
				<td><input type="text" name="prenom" class="EditBox"
					value="Jean"></td>
			</tr>
			<tr>
				<td class="Arial11Bold" nowrap>Email&nbsp;</td>
				<td><input type="text" name="email" class="EditBox"
					value="jean.dupont@orange.fr"></td>
			</tr>
			<tr>
				<td class="Arial11Bold" nowrap>N° t&eacute;l. mobile&nbsp;</td>
				<td><input type="text" name="tel" class="EditBox"
					value=""></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
	<tr style="background-color:white">
		<td align="center" width="50%">
		<input type="button" name="NOK" class="AllButton" value="Annuler" onclick="Annuler()">
		</td>
		<td align="center" width="50%">
		<input type="button" name="OK" class="AllButton" value="Valider" onClick="Valider()">
		</td>
	</tr>
</table>

</body>
</html>

