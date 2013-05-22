package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import servlets.AddDocument;
import tools.JSONFactory;

import controller.ControllerAuthentification;
import controller.Initialisator;
import dao.DAOLog;
import dao.DAOUtilisateur;
import domain.Log;
import domain.TypeLog;
import domain.Utilisateur;

@Path("/authentification")
public class Connexion {

	/**
	 * Check if the user is in the database and if the password is correct
	 * @param formParam
	 * @return a string to the device which called this service : "error" if there was an error during the treatment
	 */
	@POST 
	@Consumes("application/x-www-form-urlencoded")
	public String isAuthentified(MultivaluedMap<String, String> formParam)
	{
		final Marker marker1 = MarkerFactory.getMarker(TypeLog.CONNEXION.toString());
		final Marker marker2 = MarkerFactory.getMarker(TypeLog.CONNEXION_FAILED.toString());
		
		final Logger logger = LoggerFactory.getLogger(AddDocument.class);
		Log l = new Log();
		l.setIdentifiant_client( formParam.get("login").get(0));
		l.setIpadresse(formParam.get("ipClient").get(0));
		System.out.println(formParam.get("login").get(0) + " | "+formParam.get("password").get(0));
		if(ControllerAuthentification.getInstance().isAuthentified(formParam.get("login").get(0), formParam.get("password").get(0))){
			logger.info(marker1, "Connexion reussie", l);
			return formParam.get("login").get(0);		
		}
		else{
			logger.info(marker2, "Connexion echouee", l);
			return "error";
		}
	}
}
