

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="pull-left">
				<div class="well" id="comment">
					<p>
						<b>Constantes de certification</b><br> <br> Les données
						présentes sur cette page paramètrent:<br> <br> -> Le
						certificat de signature de vos documents ainsi que son mot de
						passe<br> -> L'identifiant de votre application web (8
						caractères maximum dont les 2 premiers ont été fournis par
						KEYNECTIS)<br> -> L'identifiant de votre serveur (8
						caractères maximum)<br> -> L'identifiant de votre organisme
						(14 caractères maximum)<br> -> Le certificat de signature
						client ainsi que son mot de passe<br> -> Le certificat de
						chiffrement des paquets envoyés à KEYNECTIS pour signature<br>
						-> Le certificat de déchiffrement de ces mêmes paquets reçus de
						KEYNECTIS une fois signés, ainsi que son mot de passe<br> ->
						L'adresse du serveur de stockage des fichiers certifiés<br>
						-> Le répertoire de stockage sur le serveur des fichiers certifiés<br>
						-> L'identifiant de connexion au serveur de stockage des fichiers
						certifiés, ainsi que le mot de passe associé<br> <br>

						Ces informations seront utilisées lors de opérations de
						certification une fois que vous aurez appuyé sur VALIDER.<br>
						Vous pouvez les modifier à tout moment en revenant sur cette page.

					</p>
				</div>
			</div>
		</div>
		<div class="span8">
			<div class="well">

				<form id="configForm" class="form-horizontal"
					action="ConfigBackEndConstants" method="post">

					<div class="control-group">
						<label class="control-label" for="CertAuth">Autorité de
							certification</label>
						<div class="controls">
							<input name="authority" type="text" id="auth"
								value="KWS_INTEGRATION_CDS" readonly="readonly"
								class="input-xlarge" required="required">
						</div>
					</div>

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
								<input type="text" id="certMetier" name="certMetier"
									placeholder="" class="input-xlarge" required="required">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="mdpCert"> Mot de passe
								du certificat</label>
							<div class="controls">
								<input type="password" id="mdpMetier" name="mdpMetier"
									placeholder="" class="input-xlarge" required="required">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="idAppMetier">
								Identifiant application métier </label>
							<div class="controls">
								<input type="text" id="idAppMetier" name="idAppMetier"
									placeholder="" class="input-xlarge" required="required">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="idServMetier">
								Identifiant serveur métier </label>
							<div class="controls">
								<input type="text" id="idServMetier" name="idServMetier"
									placeholder="" class="input-xlarge" required="required">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="idOrgMetier">
								Identifiant organisme métier </label>
							<div class="controls">
								<input type="text" id="idOrgMetier" name="idOrgMetier"
									placeholder="" class="input-xlarge" required="required">
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
									class="input-xlarge" required="required">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="mdpCert"> Mot de passe
								du certificat</label>
							<div class="controls">
								<input type="password" id="mdpCert" name="mdpCert"
									placeholder="" class="input-xlarge" required="required">
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
							<label class="control-label" for="certChiff"> Certificat
								de chiffrement </label>
							<div class="controls">
								<input type="text" id="certChiff" name="certChiff"
									placeholder="" class="input-xlarge" required="required">
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
								<input type="text" id="certDecipher" name="certDecipher"
									placeholder="" class="input-xlarge" required="required">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="mdpDecipher"> Mot de
								passe du certificat</label>
							<div class="controls">
								<input type="password" id="mdpDecipher" name="mdpDecipher"
									placeholder="" class="input-xlarge" required="required">
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
							<label class="control-label" for="servPDFCert"> Serveur
								de sauvegarde des PDF certifiés </label>
							<div class="controls">
								<input type="text" id="servPDFCert" name="servPDFCert"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="pathPDFCert">
								Répertoire sur le serveur des PDF certifiés </label>
							<div class="controls">
								<input type="text" id="pathPDFCert" name="pathPDFCert"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="loginPDFCert"> login de
								connexion au serveur des PDF certifiés </label>
							<div class="controls">
								<input type="text" id="loginPDFCert" name="loginPDFCert"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="mdpPDFCert"> Mot de
								passe de connexion au serveur des PDF certifiés </label>
							<div class="controls">
								<input type="password" id="mdpPDFCert" name="mdpPDFCert"
									placeholder="" class="input-xlarge">
							</div>
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





