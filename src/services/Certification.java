package services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import controller.ControllerCertification;

@Path("/certification")
public class Certification {
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String certificationPDF(MultivaluedMap<String, String> formParam){
		System.out.println("JE DEMANDE LA CERTIFICATION");
		
		int nbDoc =Integer.parseInt(formParam.get("nbDoc").get(0));
		if(nbDoc<=0)return "error";
		
		String identifiant = formParam.get("identifiant").get(0);
		if(identifiant==null||identifiant.length()==0)return "error";
		ArrayList<String> urls = new ArrayList<String>();
		for(int i = 0;i<nbDoc;i++){
			urls.add(formParam.get("pdfs["+i+"][url]").get(0));
		}
		ControllerCertification.getInstance().certificationPDF(identifiant, urls);
		
		return "ok";
	}
}
