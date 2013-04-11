package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import tools.EncoderBase64;
import tools.ToolsPDF;

import com.dictao.keynectis.quicksign.transid.QuickSignException;
import com.dictao.keynectis.quicksign.transid.RequestTransId;
import com.itextpdf.text.DocumentException;

import dao.DAOUtilisateur;
import domain.DocumentPDF;
import domain.Utilisateur;

public class ControllerCertification {


	//SINGLETON
	public static ControllerCertification getInstance() {
		if (null == instance) { // Premier appel
			instance = new ControllerCertification();
		}
		return instance;
	}

	/** Constructeur red�fini comme �tant priv� pour interdire
	 * son appel et forcer � passer par la m�thode <link
	 */
	private ControllerCertification() {
	}

	/** L'instance statique */
	private static ControllerCertification instance;

	public boolean certificationPDF(String identifiant, ArrayList<String> urls){
		if(identifiant ==null||identifiant.length()==0)return false;
		if(urls==null || urls.size()==0)return false;
		boolean isOk=true;
		for(String url : urls){
			if(!DAOUtilisateur.getInstance().certifiedDocument(identifiant,url))
				isOk=false;
		}
		return isOk;
	}

	//TODO : Mettre les attributs dans un fichier de config pour parametrage
	public HashMap<String, String>  certificationPDF(String identifiant, String url,String basePath,String saveFile,String certFolder){
		HashMap<String, String> toReturn =new HashMap<String, String>();

		//------------------Depend de la Base ----------------------------------------
		System.out.println("[TEST KEYNECTIS] Debut de la certification");

		Utilisateur user = DAOUtilisateur.getInstance().getUserByIdentifiant(identifiant);
		DocumentPDF document = getDocumentOfUserByUrl(user, url);

		//--------------------------Variable parametrable en fonction du metier / fichier de config?--------
		String pathCertificat = certFolder+"/demoqs_s.p12"; 
		String motPasse = "DemoQS";
		//	String adresseXML="ressources/Test.pdf.xml";
		String adresseXML="";
		//Ajout d'une zone de signature au pdf

		String urlPdfToEncode="";

		System.out.println("[TEST KEYNECTIS] Debut PDF avec signature");

		try {
			urlPdfToEncode=ToolsPDF.createPDFDocToSign(document.getUrl(),saveFile,document.getName());
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("[TEST KEYNECTIS] Fichier PDF avec signature Cr�� ! ");
		try {
			adresseXML = ToolsPDF.pdf2xml(urlPdfToEncode, document.getName(),saveFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(adresseXML.equals(""))return null;
		System.out.println("[TEST KEYNECTIS] Fichier XML du PDF Cr�� ! ");

		//-------------------------Creation du certificat de signature metier-----------------------
		System.out.println("[TEST KEYNECTIS] Debut de la signature");
		String origMetierSign="";
		try {
			origMetierSign = com.dictao.keynectis.quicksign.transid.Util.signXmlFileDsig(
					adresseXML,
					pathCertificat, 
					motPasse);
		} catch (QuickSignException e) {
			e.printStackTrace();
			return null;
		}
		if(origMetierSign.equals(""))return null;
		System.out.println("[TEST KEYNECTIS] Fin de la signature");

		//----------------------Calcul du Hash -----------------------------------------------------
		System.out.println("[TEST KEYNECTIS] Debut du calcul du HASH");
		String hashBase64="";
		try {
			hashBase64 = com.dictao.keynectis.quicksign.transid.Util.getB64Hash(origMetierSign);
		} catch (QuickSignException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(hashBase64.equals(""))return null;
		System.out.println("[TEST KEYNECTIS] Fin du calcul du HASH");

		//---------------------Informations Utilisateur-----------------------------------------------
		String nom = user.getLastName();
		String prenom = user.getFirstName();
		String email = user.getEmail();
		String authority = "KWS_INTEGRATION_CDS";
		System.out.println("[TEST KEYNECTIS] Debut de la g�n�ration du CUF");
		String cuf = String.valueOf(1000 + (new java.util.Random()).nextInt(8999));
		System.out.println("[TEST KEYNECTIS] Fin du calcul du HASH");

		//		String basePath = request.getScheme() + "://"
		//				+ request.getServerName() + ":" + request.getServerPort()
		//				+ request.getContextPath() + request.getServletPath();

		//String returnUrl = basePath.substring(0, basePath.lastIndexOf("/"))+ "/demoPDFSMS/demo6p4.jsp";
		String returnUrl = basePath.substring(0, basePath.lastIndexOf("/"))+"/ResponseKeynectis";
		System.out.println("[TEST KEYNECTIS] url de retour : "+returnUrl);

		String blob = "";
		String transNum = "";
		BufferedReader br = null;
		String ligne = "";
		String tag = "";

		System.out.println("[TEST KEYNECTIS] Debut de l'encodage de la signature");
		
		//TODO possible probl�me d'encodage
		byte[] decode = EncoderBase64.encodingBlobToByteArray(user.getSignature());
		String signatureBase64 = EncoderBase64.byteArraytoStringBase64(decode);
		
		System.out.println("[TEST KEYNECTIS] Fin encodage de la signature");

		//-----------------------------DEFINITION DU TAG---------------------------------------------------------
		System.out.println("[TEST KEYNECTIS] Debut de la d�finition du TAG");
		
		tag += "DATA_METIER=contrat\n";
		tag += "CUF_ORG=no\n";
		tag += "TYPE=38\n";
		tag += "VISU=docPDFb64\n";
		//tag += "PDF_VERIFY_FIELDS=Signature1\n";
		//tag += "PDF_SIGN_FIELD=Signature2\n";
		tag += "PDF_SIGN_FIELD=Signature1\n";
		//tag += "PDF_REASON=Test avec image\n"; //Motif de la signature: Conseill� mais facultatif
		//tag += "PDF_LOCATION=Capgemini France | Paris, Rue de Lyon\n"; //Lieu de la signature: Conseill� mais facultatif
		//tag += "PDF_CONTACT=01.49.67.44.11\n"; //Coordonn�es du signataire: Conseill� mais facultatif
		//tag += "PDF_SIGNATURE_GRAPHIC="+signatureBase64+"\n";
		
		tag += "PDF_SIGNATURE_LAYER2_TEXT=\n";
		//	tag += "PDF_SIGNATURE_BACKGROUND_IMAGE=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\n";
		tag+= "PDF_SIGNATURE_BACKGROUND_IMAGE="+signatureBase64+"\n";
		tag += "PDF_SIGNATURE_BACKGROUND_IMAGE_SCALE=-1\n";
		//		tag += "EXTRA_PARAM=demo=demoPDFSMS;numTelSMS="
		//				+ user.getPhoneNumber()
		//				+ ";smsBody=Bonjour. Voici votre code de signature : "
		//				+ cuf + ". "
		//				+ " Keynectis vous remercie de votre confiance.\n";

		System.out.println("[TEST KEYNECTIS] Fin de la d�finition du TAG");

		//--------------------------DEFINTION DE TRANSID--------------------------------------
		System.out.println("[TEST KEYNECTIS] Debut de la d�finition de TRANSID");

		String identifiant_application_metier = "ZZDEMAV1";
		String identifiant_application_serveur_metier = "DEMO";
		String identifiant_organisme_application_metier="PDFSMS";

		String path_certificat_signature_blob =   certFolder+"/demoqs_i.p12";
		String mot_de_passe_certificat_blob = "DemoQS";

		String path_certificat_chiffrement_blob =  certFolder+"/certQSkeyncryp.cer";
		RequestTransId rti=null;
		try {
			rti = new RequestTransId(
					identifiant_application_metier,
					identifiant_application_serveur_metier,
					identifiant_organisme_application_metier);
		} catch (QuickSignException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		if(rti==null)return null;

		rti.setSignCertFilePath(path_certificat_signature_blob, mot_de_passe_certificat_blob);
		rti.setCipherCertFilePath(path_certificat_chiffrement_blob);
		rti.setName(prenom + " " + nom);
		rti.setEmail(email);
		rti.setAuthority(authority);
		//rti.setCuf(cuf);
		rti.setReturnUrl(returnUrl);
		rti.setFilePath(origMetierSign); // L'original m�tier sign� en pj
		rti.setTag(tag);

		try {
			transNum = rti.getTransNum();
			//			session.setAttribute("transNum", transNum);
			blob = rti.getB64Blob();
		} catch (QuickSignException qse) {
			return null;
		}
		System.out.println("[TEST KEYNECTIS] Fin de la d�finition de TRANSID");

		toReturn.put("blob", blob);
		toReturn.put("transNum", transNum);


		return toReturn;
	}

	/**
	 * Find the document of a user by its url
	 * @param user
	 * @param url
	 * @return the document if it has been found or null if not
	 */
	private DocumentPDF getDocumentOfUserByUrl(Utilisateur user,String url){
		for (DocumentPDF doc : user.getDocuments()){
			if(doc.getUrl().equals(url))return doc;
		}
		return null;
	}
}
