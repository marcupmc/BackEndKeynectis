

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="pull-left">
				<div class="well">
					<p>
						<b>Constantes de certification</b><br>
						<br> Les donn�es pr�sentes sur cette page param�trent:<br>
						<br> -> Le chemin vers les certificats utilis�s<br> ->
						Le r�pertoire de stockage des fichiers temporaires du processus<br>
						-> L'adresse du serveur de stockage des fichiers certifi�s<br>
						<br> Ces informations seront utilis�es lors de op�rations de
						certification une fois que vous aurez appuy� sur VALIDER.<br>
						Vous pouvez les modifier � tout moment en revenant sur cette page.

					</p>
				</div>
			</div>
		</div>
		<div class="span8">

			<div class="well">

				<form class="form-horizontal" action="ConfigBackEndConstants"
					method="post">

					<div class="control-group">
						<label class="control-label" for="CertAuth">Authorit� de
							certification</label>
						<div class="controls">
							<input name="authority" type="text" id="auth" readonly="readonly"
								value="DICTAO">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputCertPath">R�pertoire
							des certificats</label>
						<div class="controls">
							<input name="CertPath" type="text" id="inputCertPath"
								placeholder="">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputTempPath">R�pertoire
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
							de stockage des fichiers certifi�s</label>
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





