<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/style.css" />
<title>Parametrage</title>
</head>
<body>
	<div class="container">
		<h2>Paramètrage du Serveur</h2>

		<div class="params" id="loginModal">
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#login" data-toggle="tab">RTI
								Paramètres</a></li>
						<li><a href="#create" data-toggle="tab">TAG Paramètres</a></li>
					</ul>
					<div id="myTabContent" class="tab-content">

						<!-- 					RTI Parameter -->

						<div class="tab-pane active in" id="login">
							<form class="form-horizontal" action='' method="POST">
								<fieldset>
									<div id="legend">
										<legend class="">Request TransID</legend>
									</div>
									<!----------------------------------------------------------------------------------------------->
									<div class="control-group">
										<label class="control-label" for="certSign">
											Certificat de signature</label>
										<div class="controls">
											<input type="text" id="certSign" name="certSign"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->

									<div class="control-group">
										<label class="control-label" for="mdpCert"> Mot de
											passe du certificat</label>
										<div class="controls">
											<input type="text" id="mdpCert" name="mdpCert" placeholder=""
												class="input-xlarge">
										</div>
									</div>

									<!----------------------------------------------------------------------------------------------->
									<div class="control-group">
										<label class="control-label" for="certChiff">
											Certificat de chiffrement </label>
										<div class="controls">
											<input type="text" id="certChiff" name="certChiff"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->

									<div class="control-group">
										<label class="control-label" for="idAppMetier">
											Identifiant application métier </label>
										<div class="controls">
											<input type="text" id="idAppMetier" name="idAppMetier"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->
									<div class="control-group">
										<label class="control-label" for="idServMetier">
											Identifiant serveur métier </label>
										<div class="controls">
											<input type="text" id="idServMetier" name="idServMetier"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->
									<div class="control-group">
										<label class="control-label" for="idOrgMetier">
											Identifiant organisme métier </label>
										<div class="controls">
											<input type="text" id="idOrgMetier" name="idOrgMetier"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->
									<div class="control-group">
										<label class="control-label" for="pathPDFCert">
											Chemin PDF certifiés </label>
										<div class="controls">
											<input type="text" id="pathPDFCert" name="pathPDFCert"
												placeholder="" class="input-xlarge">
										</div>
									</div>
									<!----------------------------------------------------------------------------------------------->


									<div class="control-group">
										<!-- Button -->
										<div class="controls">
											<button class="btn btn-success">Enregister</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>

						<!-- 						TAG PARAMETER -->

						<div class="tab-pane fade" id="create"></div>
					</div>
			</div>

		</div>
	</div>

	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-min.js"></script>
</body>
</html>