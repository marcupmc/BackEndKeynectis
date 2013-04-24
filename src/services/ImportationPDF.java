package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import tools.JSONFactory;

import dao.DAOUtilisateur;
import domain.Utilisateur;

@Path("/importation")
public class ImportationPDF {

	/**
	 * Find the pdf of a user from his "Identifiant"
	 * @param formParam
	 * @return a JSON with all the documentPDF informations
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String getPDF(MultivaluedMap<String, String> formParam)
	{
		System.out.println("taille : "+formParam);
		
		String temp= formParam.toString().split("=")[0];
		System.out.println(temp);
		String id = temp.substring(1);
		
		System.out.println("Identifiant : "+id);
		if(id==null||id.length()==0)return "error";
		Utilisateur user = DAOUtilisateur.getInstance().getUserByIdentifiant(id);
		if(user==null) return "error";
		JSONObject toReturn = JSONFactory.getInstance().makeUserJSON(user);
		System.out.println(toReturn.toString());
		return toReturn.toString();
	}
}
