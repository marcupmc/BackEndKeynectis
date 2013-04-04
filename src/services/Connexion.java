package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.core.HttpRequestContext;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeMultipart;

import controller.Initialisator;

@Path("/authentification")
public class Connexion {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String isAuthentified(MultivaluedMap<String, String> formParam)
	{
		//Initialisation de la base A ENLEVER !!
		System.out.println("[HIBERNATE] Initialisation de la base...");
		boolean isInitOk = Initialisator.getInstance().initBDD();
		if(isInitOk)System.out.println("[HIBERNATE] Base Initialisée");
		else System.err.println("[HIBERNATE] Erreur!!!");
		// retourne ok si l utilisateur est dans la base, retourne ko sinon
		System.out.println("Connection Autorisée pour "+formParam.get("login"));
		return "ok";
	}
	

}
