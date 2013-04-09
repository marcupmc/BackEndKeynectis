package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

@Path("/certification")
public class Certification {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String certificationPDF(MultivaluedMap<String, String> formParam){
		System.out.println("JE DEMANDE LA CERTIFICATION");
		// Appeler un controlleur qui sera un bouchon
		// pour certifier une liste de document d'un utilisateur
		
		//Recuperer l'identifiant puis la liste des urls
		System.out.println(formParam.toString());
		
		return "Hello";
	}
}
