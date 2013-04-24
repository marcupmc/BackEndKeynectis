<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" media="screen">
<title>AutoSign</title>
</head>
<body>

	<form name="connexionForm" id="connexionForm" class="form-signin" method="POST" action="ClientAuthentification">
		<h2 class="form-signin-heading">Bienvenue dans AutoSign</h2>
		<input name="login" type="text" id="inputIdentifiant" placeholder="Identifiant" class="input-block-level"> 
		<input type="password" name="password" id="inputPassword" placeholder="Password" class="input-block-level">
		<button type="submit" class="btn btn-primary">Se Connecter</button>
	</form>

	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>