<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	//Fonction pour envoyer le blob dans la frame
	function test() {
		return document.getElementById("blobVal").value;
	}
</script>

</head>
<body>
	<%
		String blob = (String) request.getAttribute("blob");
	%>

	<div class="alert alert-info">
		<h1>Veuillez patienter SVP</h1>

		<p>Nous traitons votre demande</p>
	</div>

	<input id="blobVal" type="hidden" value="<%=blob%>" />

	<form id="myForm" method="POST"
		action="https://keynectis.kwebsign.net/QS/Page1V2">
		<input type="hidden" name="appid" value="ZZDEMAV1"> <input
			type="hidden" id="blob" name="blob" value="<%=blob%>">
	</form>

	<script type="text/javascript">
		document.getElementById("myForm").submit();
	</script>

	<!-- 		<iframe name="myiframe" id="myiframe" -->
	<!-- 			style="width: 500px; height: 300px;" src="frameToKeynectis.jsp"> -->

	<!-- 			<p>PROBLEME</p> -->
	<!-- 		</iframe> -->




</body>
</html>