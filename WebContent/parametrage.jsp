<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style3.css" />
<!-- <link rel="stylesheet" href="css/orange_style.css" /> -->
<!-- <link rel="stylesheet" href="css/orange_style3.css" /> -->

<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#nextTab").click(function() {
			// $("#const").attr("class", "tab-pane active");
			//document.getElementById("const").setAttribute("class", "tab-pane active"); 
			// document.getElementById("tabConst").setAttribute("class", "active");
			// document.getElementById("tabAuth").setAttribute("class", ""); 
			
			$("#tabConst").attr("class", "active");
			$("#tabAuth").attr("class", "");

			$("#tabAuth").hide();
			$("#tabConst").show();
			//$("#tabTypes").show();

			var sel = $('#authority').val();
			switch (sel) {
			case "1":
				// $("#constInner").load("paramConstKEY.jsp"); 
				$("#comment").load("commentKEY.jsp");
				$("#configForm").load("formKEY.jsp");
				break;
			case "2":
				// $("#constInner").load("paramConstDICTAO.jsp"); 
				$("#comment").load("commentDICTAO.jsp");
				$("#configForm").load("formDICTAO.jsp");
				break;
			}
		});
	});
	/* $(function() {
		$("#nextTab").click(nextTab());
	}); */

	$(function() {
		$("#authority").change(function() {
			/* $("#const").attr('class', 'tab-pane active');
			document.getElementById("const").setAttribute("class", "tab-pane active"); */
			/* $("#tabConst").attr("class", "active");
			$("#tabAuth").attr("class", ""); */
			var sel = $('#authority').val();
			switch (sel) {
			case "1":
				$("#constInner").load("paramConstKEY.jsp");
				break;
			case "2":
				$("#constInner").load("paramConstDICTAO.jsp");
				break;
			}
		});
	});
</script>

<%
	String previous = (String) request.getParameter("previous");
	if ("const".equals(previous)) {
%>
<script type="text/javascript">
	//nextTab();
</script>
<%
	}
%>


<title>Parametrage du serveur</title>
</head>
<body>

	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h2>
					Configuration du serveur Métier
					<div class="rightAlign">
						<a href="adminHome.jsp" class="btn btn-info">Accueil</a>
					</div>
				</h2>
			</div>
		</div>

		<div class="params" id="loginModal">
			<div class="well">
				<div class="tab-wrapper" id="tab-wrapper">
					<div class="tab-header">
						<ul class="nav nav-pills">

							<li class="active" id="tabAuth"><a href="#auth"
								data-toggle="tab">Autorité de certification</a></li>

							<li id="tabConst"><a href="#const" data-toggle="tab">Constantes
									du processus</a></li>

							<li id="tabTypes"><a href="#types" data-toggle="tab">Types
									de certification</a></li>

						</ul>
					</div>
					<%
						if (request.getAttribute("authorityParameter") == null) {
					%>
					<script type="text/javascript">
						$("#tabConst").hide();
						$("#tabTypes").hide();
					</script>

					<%
						}
					%>

					<div class="tab-content">

						<%
							String message = "";
							String msg = (String) request.getParameter("error");
							String type = (String) request.getParameter("messType");

							if (("success").equals(msg)) {
								if ("KWS_first".equals(type)) {
						%>
						<script type="text/javascript">
							$(function() {
								$("#tagList").load("tagParameters.jsp", {
									act : "firstAccess"
								});
							});
						</script>
						<%
							} else {
						%>
						<script type="text/javascript">
							$(function() {
								$("#tagList").load("tagParameters.jsp");
							});
						</script>
						<%
							}
						%>
						<script>
							$(function() {
								$("#tabConst").hide();
								$("#tabAuth").hide();
								$("#tabConst").attr("class", "");
								$("#tabAuth").attr("class", "");
								$("#tabTypes").attr("class", "active");
								$("#types").attr("class", "tab-pane active");
								$("#auth").attr("class", "tab-pane");
								$("#tabTypes").show();
								$("#tabTypes").attr("class", "active");
								$("#tabConst").attr("class", "");

								$("#tagComment").load("tagComment.jsp");
							});
						</script>
						<%
							if ((("adding_type_error").equals(type))) {

									message = "Un autre type possède déjà cet identifiant et ce nom. Veuillez en changer et sauvegarder de nouveau.";
						%>
						<div class="alert alert-block alert-error fade in">
							Erreur :
							<%=message%></div>
						<%
							}
							else if ((("removing_type_error").equals(type))) {
		
									message = "Impossible de supprimer ce type pour le moment. Débuggez ou réessayez ultérieurement.";
						%>
						<div class="alert alert-block alert-error fade in">
							Erreur :
							<%=message%></div>
						<%
							}
						
							}
							if ((("error").equals(msg))) {
								if ("certMetier".equals(type))
									message = "Le certificat de signature métier n'existe pas.\n Veuillez insérer un fichier existant.";
								if ("certSign".equals(type))
									message = "Le certificat de signature client n'existe pas.\n Veuillez insérer un fichier existant.";
								if ("certChiff".equals(type))
									message = "Le certificat de chiffrement n'existe pas.\n Veuillez insérer un fichier existant.";
								if ("certDecipher".equals(type))
									message = "Le certificat de déchiffrement n'existe pas.\n Veuillez insérer un fichier existant.";
								if ("null".equals(type))
									message = "Certains ou tous les champs n'ont pas été remplis.\nMerci de bien vouloir remplir tous les champs avant de sauvegarder.";
						%>

						<div class="alert alert-block alert-error fade in">
							Erreur :
							<%=message%></div>
						<script>
							$(function() {
								//$("#tabAuth").hide();
								$("#tabConst").attr("class", "active");
								$("#tabAuth").attr("class", "");

								$("#tabConst").show();
								$("#tabTypes").attr("class", "");
								$("#const").attr("class", "tab-pane active");
								$("#types").attr("class", "tab-pane");
								$("#auth").attr("class", "tab-pane");

								var sel = $('#authority').val();
								$("#comment").load("commentKEY.jsp");
								$("#configForm").load("formKEY.jsp");
							});
						</script>
						<%
							}
						%>
						<!-- 		Fin de la zone des messages d'erreurs -->



						<!-- ************************ Onglet de paramétrage de l'autorité de certification ************************ -->

						<div class="tab-pane active" id="auth">

							<div class="container-fluid">
								<div class="row-fluid">
									<div class="span4">
										<div class="pull-left">
											<div class="well">
												<p>
													<b>Autorité de certification</b><br> <br> Cette
													page sert à paramétrer l'autorité de certification. Vous
													avez le choix entre une liste exhaustive d'autorités de
													certification.<br> Ce choix définira par la suite les
													autres paramètres à configurer.<br> Choisissez donc
													une autorité de certification avant de continuer.

												</p>
											</div>
										</div>
									</div>
									<div class="span8">
										<div class="well">
											<!-- 											<form class="form-horizontal" method="post"> -->

											<div class="control-group">
												<div class="controls">
													<select id="authority">
														<option value="1" id="KWS">KEYNECTIS K.WebSign</option>
														<option value="2" id="DICTAO">DICTAO</option>
													</select>

												</div>

											</div>

											<div class="control-group">

												<div class="controls">
													<a class="btn btn-success" href="#const" data-toggle="tab"
														id="nextTab">Onglet suivant</a>
												</div>
											</div>

											<!-- 											</form> -->

										</div>
									</div>
								</div>

							</div>

						</div>

						<!-- ************************ Onglet de paramétrage des constantes de certification ************************ -->

						<div class="tab-pane" id="const">
							<div id="constInner">

								<div class="container-fluid">
									<div class="row-fluid">
										<div class="span4">
											<div class="pull-left">
												<div class="well" id="comment"></div>
											</div>
										</div>
										<div class="span8">
											<div class="well">

												<form id="configForm" class="form-horizontal"
													action="ConfigBackEndConstants" method="post"
													enctype="multipart/form-data"></form>



											</div>
										</div>
									</div>

								</div>

							</div>

						</div>


						<!-- ************************ Onglet de paramétrage des types de certification ************************ -->

						<div class="tab-pane" id="types">
							<div id="tagParam">

								<div class="container-fluid">
									<div class="row-fluid">
										<div class="span4">
											<div class="pull-left">
												<div class="well" id="tagComment"></div>
											</div>
										</div>
										<div class="span8">
											<div class="well">

												<div id="tagList"></div>

											</div>
										</div>
									</div>

								</div>

							</div>

						</div>

					</div>


				</div>
			</div>
		</div>
	</div>


</body>
</html>