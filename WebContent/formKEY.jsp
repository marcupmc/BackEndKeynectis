
<script src="js/modifyServerPart.js" type="text/javascript"></script>

<div class="control-group">
	<label class="control-label" for="CertAuth">Autorité de
		certification</label>
	<div class="controls">
		<input name="authority" type="text" id="auth"
			value="KWS_INTEGRATION_CDS" readonly="readonly" class="input-xlarge">
	</div>
</div>

<!-- <div id="legend">
	<legend class="partTitle">Paramètres globaux</legend>
</div>

<div class="control-group">
	<label class="control-label" for="inputCertPath">Répertoire des
		certificats</label>
	<div class="controls">
		<input name="CertPath" type="text" id="inputCertPath" placeholder="">
	</div>
</div>

<div class="control-group">
	<label class="control-label" for="inputTempPath">Répertoire des
		fichiers temporaires</label>
	<div class="controls">
		<input name="TempPath" type="text" id="inputTempPath" placeholder="">
	</div>
</div>

<div class="control-group">
							<label class="control-label" for="inputServIPAd">Adresse IP du serveur</label>
							<div class="controls">
								<input name="IPAd" type="text" id="inputServIPAd" placeholder="">
							</div>								
						</div>

<div class="control-group">
	<label class="control-label" for="inputSavePath">Adresse de
		stockage des fichiers certifiés</label>
	<div class="controls">
		<input name="SavePath" type="text" id="inputSavePath" placeholder="">
	</div>
</div> -->


<div id="legend">
	<legend class="partTitle">Paramètres du Request TransID</legend>
</div>

<div class="well">
	<div id="legend1">
		<legend class="partTitle">
			<h5>Original Métier</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certSign">Certificat de
			signature Original Métier</label>
		<div class="controls">
			<input type="file" id="certMetier" name="certMetier" placeholder=""
				value="demoqs_s.p12" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpCert"> Mot de passe du
			certificat</label>
		<div class="controls">
			<input type="password" id="mdpMetier" name="mdpMetier" placeholder=""
				value="DemoQS" class="input-xlarge">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="idAppMetier"> Identifiant
			application métier </label>
		<div class="controls">
			<input type="text" id="idAppMetier" name="idAppMetier" placeholder=""
				value="ZZDEMAV1" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="idServMetier"> Identifiant
			serveur métier </label>
		<div class="controls">
			<input type="text" id="idServMetier" name="idServMetier"
				placeholder="" value="DEMO" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="idOrgMetier"> Identifiant
			organisme métier </label>
		<div class="controls">
			<input type="text" id="idOrgMetier" name="idOrgMetier" placeholder=""
				value="PDFSMS" class="input-xlarge">
		</div>
	</div>
</div>


<div class="well">
	<div id="legend2">
		<legend class="partTitle">
			<h5>Signature Client</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certSign">Certificat de
			signature</label>
		<div class="controls">
			<input type="file" id="certSign" name="certSign" placeholder=""
				value="demoqs_i.p12" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpCert"> Mot de passe du
			certificat</label>
		<div class="controls">
			<input type="password" id="mdpCert" name="mdpCert" placeholder=""
				value="DemoQS" class="input-xlarge">
		</div>
	</div>
</div>


<div class="well">
	<div id="legend3">
		<legend class="partTitle">
			<h5>Chiffrement</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certChiff"> Certificat de
			chiffrement </label>
		<div class="controls">
			<input type="file" id="certChiff" name="certChiff" placeholder=""
				value="certQSkeyncryp.cer" class="input-xlarge">
		</div>
	</div>
</div>


<div class="well">
	<div id="legend3">
		<legend class="partTitle">
			<h5>Déchiffrement</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certSign">Certificat de
			déchiffrement</label>
		<div class="controls">
			<input type="file" id="certDecipher" name="certDecipher"
				placeholder="" value="demoqs_c.p12" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpDecipher"> Mot de passe
			du certificat</label>
		<div class="controls">
			<input type="password" id="mdpDecipher" name="mdpDecipher"
				placeholder="" value="DemoQS" class="input-xlarge">
		</div>
	</div>

</div>

<div class="well">
	<div id="legend4">
		<legend class="partTitle">
			<h5>Sauvegarde</h5>
		</legend>
	</div>

	<label class="radio"> <input type="radio" name="optionsRadios"
		id="bddSave" value="bddSave" checked="checked"> Sauvegarde en
		base de données (Recommandé)
	</label> <label class="radio"> <input type="radio" name="optionsRadios"
		id="ftpSave" value="ftpSave"> Sauvegarde sur un serveur
		externe
	</label>

	<div id="serverPart">
		<div class="control-group">
			<label class="control-label" for="servPDFCert"> Serveur de
				sauvegarde des PDF certifiés </label>
			<div class="controls">
				<input type="text" id="servPDFCert" name="servPDFCert"
					placeholder="" value="ftp.marc-gregoire.fr" class="input-xlarge">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="pathPDFCert"> Répertoire
				sur le serveur des PDF certifiés </label>
			<div class="controls">
				<input type="text" id="pathPDFCert" name="pathPDFCert"
					placeholder="" value="www/Keynectis_Certified" class="input-xlarge">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="loginPDFCert"> login de
				connexion au serveur des PDF certifiés </label>
			<div class="controls">
				<input type="text" id="loginPDFCert" name="loginPDFCert"
					placeholder="" value="marcgreg" class="input-xlarge">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="mdpPDFCert"> Mot de passe
				de connexion au serveur des PDF certifiés </label>
			<div class="controls">
				<input type="password" id="mdpPDFCert" name="mdpPDFCert"
					placeholder="" value="nCcKMr7E" class="input-xlarge">
			</div>
		</div>
	</div>
</div>
<div class="well">
	<div class="rightAlign">
		<div class="control-group">

			<div class="controls">
				<button id="formButton" type="submit" class="btn btn-success">Valider</button>
			</div>
		</div>
	</div>
	<div class="leftAlign">
		<a class="btn btn-inverse" href="parametrage.jsp">Onglet Précédent</a>
	</div>
</div>
