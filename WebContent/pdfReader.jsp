<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="js/pdfReader.js"></script>
<title>Insert title here</title>

<style type="text/css">
#pdfreader {
	height: 800px;
	width: 600px;
}
</style>

</head>
<body>
	<div class="container">
		<h1>Lecteur PDF</h1>
		<button onclick="previousPage()" id="previous"
				class="btn btn-primary"><-</button>
			<button id="next" onclick="nextPage()" class="btn btn-success">-></button>
		

		<div id="pdfreader">
			<img src="" id="imagePDF" />
			</div>

	</div>

</body>
</html>