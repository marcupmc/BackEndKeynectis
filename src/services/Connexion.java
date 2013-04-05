package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import tools.JSONFactory;

import controller.ControllerAuthentification;
import controller.Initialisator;
import dao.DAOUtilisateur;
import domain.Utilisateur;

@Path("/authentification")
public class Connexion {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String isAuthentified(MultivaluedMap<String, String> formParam)
	{
		//Initialisation de la base A ENLEVER !!
//		System.out.println("[HIBERNATE] Initialisation de la base...");
//		boolean isInitOk = Initialisator.getInstance().initBDD();
//		if(isInitOk)System.out.println("[HIBERNATE] Base Initialisée");
//		else System.err.println("[HIBERNATE] Erreur!!!");
		
		System.out.println(formParam.get("login").get(0) + " | "+formParam.get("password").get(0));
		if(ControllerAuthentification.getInstance().isAuthentified(formParam.get("login").get(0), formParam.get("password").get(0))){
			System.out.println("Authenfication reussie");
			return formParam.get("login").get(0);		
		}
		else{
			System.out.println("Authenfication echouée");
			return "error";
		}
		// retourne ok si l utilisateur est dans la base, retourne ko sinon
		//System.out.println("Connection Autorisée pour "+formParam.get("login"));

		//Pour test
//		JSONObject toReturn = new JSONObject();
//		Utilisateur userToParse = DAOUtilisateur.getInstance().getUserById(1);
//		if(userToParse!=null)
//			toReturn=JSONFactory.getInstance().makeUserJSON(userToParse);
//		System.out.println(toReturn);
		//return toReturn.toString();
	}
}
