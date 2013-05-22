
<script src="js/validateAuthorityConstForm.js" type="text/javascript"></script>

<div class="control-group">
	<label class="control-label" for="CertAuth">Autorit� de
		certification</label>
	<div class="controls">
		<input name="authority" type="text" id="auth" value="KWS_INTEGRATION_CDS"
			readonly="readonly" class="input-xlarge">
	</div>
</div>

<div id="legend">
	<legend class="partTitle">Param�tres globaux</legend>
</div>

<div class="control-group">
	<label class="control-label" for="inputCertPath">R�pertoire des
		certificats</label>
	<div class="controls">
		<input name="CertPath" type="text" id="inputCertPath" placeholder="">
	</div>
</div>

<div class="control-group">
	<label class="control-label" for="inputTempPath">R�pertoire des
		fichiers temporaires</label>
	<div class="controls">
		<input name="TempPath" type="text" id="inputTempPath" placeholder="">
	</div>
</div>

<!-- <div class="control-group">
							<label class="control-label" for="inputServIPAd">Adresse IP du serveur</label>
							<div class="controls">
								<input name="IPAd" type="text" id="inputServIPAd" placeholder="">
							</div>								
						</div> -->

<div class="control-group">
	<label class="control-label" for="inputSavePath">Adresse de
		stockage des fichiers certifi�s</label>
	<div class="controls">
		<input name="SavePath" type="text" id="inputSavePath" placeholder="">
	</div>
</div>


<div id="legend">
	<legend class="partTitle">Param�tres du Request TransID</legend>
</div>

<div class="well">
	<div id="legend1">
		<legend class="partTitle">
			<h5>Original M�tier</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certSign">Certificat de
			signature Original M�tier</label>
		<div class="controls">
			<input type="text" id="certMetier" name="certMetier" placeholder=""
				class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpCert"> Mot de passe du
			certificat</label>
		<div class="controls">
			<input type="password" id="mdpMetier" name="mdpMetier" placeholder=""
				class="input-xlarge">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="idAppMetier"> Identifiant
			application m�tier </label>
		<div class="controls">
			<input type="text" id="idAppMetier" name="idAppMetier" placeholder=""
				class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="idServMetier"> Identifiant
			serveur m�tier </label>
		<div class="controls">
			<input type="text" id="idServMetier" name="idServMetier"
				placeholder="" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="idOrgMetier"> Identifiant
			organisme m�tier </label>
		<div class="controls">
			<input type="text" id="idOrgMetier" name="idOrgMetier" placeholder=""
				class="input-xlarge">
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
			<input type="text" id="certSign" name="certSign" placeholder=""
				class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpCert"> Mot de passe du
			certificat</label>
		<div class="controls">
			<input type="password" id="mdpCert" name="mdpCert" placeholder=""
				class="input-xlarge">
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
			<input type="text" id="certChiff" name="certChiff" placeholder=""
				class="input-xlarge">
		</div>
	</div>
</div>


<div class="well">
	<div id="legend3">
		<legend class="partTitle">
			<h5>D�chiffrement</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="certSign">Certificat de
			d�chiffrement</label>
		<div class="controls">
			<input type="text" id="certDecipher" name="certDecipher"
				placeholder="" class="input-xlarge">
		</div>
	</div>


	<div class="control-group">
		<label class="control-label" for="mdpDecipher"> Mot de passe
			du certificat</label>
		<div class="controls">
			<input type="password" id="mdpDecipher" name="mdpDecipher"
				placeholder="" class="input-xlarge">
		</div>
	</div>

</div>

<div class="well">
	<div id="legend4">
		<legend class="partTitle">
			<h5>Sauvegarde</h5>
		</legend>
	</div>

	<div class="control-group">
		<label class="control-label" for="servPDFCert"> Serveur de
			sauvegarde des PDF certifi�s </label>
		<div class="controls">
			<input type="text" id="servPDFCert" name="servPDFCert" placeholder=""
				class="input-xlarge">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="pathPDFCert"> R�pertoire sur
			le serveur des PDF certifi�s </label>
		<div class="controls">
			<input type="text" id="pathPDFCert" name="pathPDFCert" placeholder=""
				class="input-xlarge">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="loginPDFCert"> login de
			connexion au serveur des PDF certifi�s </label>
		<div class="controls">
			<input type="text" id="loginPDFCert" name="loginPDFCert"
				placeholder="" class="input-xlarge">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="mdpPDFCert"> Mot de passe de
			connexion au serveur des PDF certifi�s </label>
		<div class="controls">
			<input type="password" id="mdpPDFCert" name="mdpPDFCert"
				placeholder="" class="input-xlarge">
		</div>
	</div>

</div>

<div class="control-group">

	<div class="controls">
		<button id="formButton" type="submit" class="btn btn-success">Valider</button>
	</div>
</div>
