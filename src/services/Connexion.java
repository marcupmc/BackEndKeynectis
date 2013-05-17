package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import tools.JSONFactory;

import controller.ControllerAuthentification;
import controller.Initialisator;
import dao.DAOLog;
import dao.DAOUtilisateur;
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
		System.out.println(formParam.get("login").get(0) + " | "+formParam.get("password").get(0));
		if(ControllerAuthentification.getInstance().isAuthentified(formParam.get("login").get(0), formParam.get("password").get(0))){
			System.out.println("Authenfication reussie");
			
			//LOG
			DAOLog.getInstance().addLog(TypeLog.CONNEXION, formParam.get("ipClient").get(0), formParam.get("login").get(0));
			
			return formParam.get("login").get(0);		
		}
		else{
			System.out.println("Authenfication echouée");
			//LOG
			DAOLog.getInstance().addLog(TypeLog.CONNEXION_FAILED, formParam.get("ipClient").get(0), formParam.get("login").get(0));
			return "error";
		}
	}
}
