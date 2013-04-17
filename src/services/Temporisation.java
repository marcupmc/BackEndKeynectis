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
		System.out.println("ON va temporiser");
		while(!DAODocumentPDF.getInstance().getByUrl("http://www.marc-gregoire.fr/pdf/cv.pdf").isCertified()){
			System.out.println("----------------ON TEMPORISE-------------");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("--------------------C'est bon le doc est pret----------------");
		return "ok";
	}
}
