

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="pull-left">
				<div class="well">
					<p>
						<b>Constantes de certification</b><br>
						<br> Les données présentes sur cette page paramètrent:<br>
						<br> -> Le chemin vers les certificats utilisés<br> ->
						Le répertoire de stockage des fichiers temporaires du processus<br>
						-> L'adresse du serveur de stockage des fichiers certifiés<br>
						<br> Ces informations seront utilisées lors de opérations de
						certification une fois que vous aurez appuyé sur VALIDER.<br>
						Vous pouvez les modifier à tout moment en revenant sur cette page.

					</p>
				</div>
			</div>
		</div>
		<div class="span8">

			<div class="well">

				<form class="form-horizontal" action="ConfigBackEndConstants"
					method="post">

					<div class="control-group">
						<label class="control-label" for="CertAuth">Authorité de
							certification</label>
						<div class="controls">
							<input name="authority" type="text" id="auth" readonly="readonly"
								value="DICTAO">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputCertPath">Répertoire
							des certificats</label>
						<div class="controls">
							<input name="CertPath" type="text" id="inputCertPath"
								placeholder="">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputTempPath">Répertoire
							des fichiers temporaires</label>
						<div class="controls">
							<input name="TempPath" type="text" id="inputTempPath"
								placeholder="">
						</div>
					</div>

					<!-- <div class="control-group">
							<label class="control-label" for="inputServIPAd">Adresse IP du serveur</label>
							<div class="controls">
								<input name="IPAd" type="text" id="inputServIPAd" placeholder="">
							</div>								
						</div> -->

					<div class="control-group">
						<label class="control-label" for="inputSavePath">Adresse
							de stockage des fichiers certifiés</label>
						<div class="controls">
							<input name="SavePath" type="text" id="inputSavePath"
								placeholder="">
						</div>
					</div>

					<div class="control-group">

						<div class="controls">
							<button type="submit" class="btn btn-success">Valider</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

</div>





