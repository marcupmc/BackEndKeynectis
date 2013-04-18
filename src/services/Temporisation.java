package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import dao.DAODocumentPDF;
import dao.DAOUtilisateur;

@Path("/temporisation")
public class Temporisation {

	@POST 
	@Consumes("application/x-www-form-urlencoded")
	public String tempo(MultivaluedMap<String, String> formParam){
		System.out.println("------------ON va temporiser---------------");
		
		String temp= formParam.toString().split("=")[0];
		System.out.println(temp);
		long idDocument = Long.parseLong(temp.substring(1));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(DAODocumentPDF.getInstance().getById(idDocument).isCertified())
			return "ok";
		return "notyet";  
	}
}
   