package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import dao.DAODocumentPDF;

@Path("/temporisation")
public class Temporisation
{

	/**
	 * Check if the a document has been certified by the server, and pause
	 * during 5 sec
	 * 
	 * @param formParam
	 * @return "ok" if the document has been certified, "notyet" if not
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String tempo(MultivaluedMap<String, String> formParam)
	{
		System.out.println("------------ON va temporiser---------------");

		String temp = formParam.toString().split("=")[0];
		long idDocument = Long.parseLong(temp.substring(1));
		System.out.println(idDocument);
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			System.out.println("erreur : " + e.getMessage());
		}
		if (!DAODocumentPDF.getInstance().getById(idDocument).getCertified().equals("En attente"))
		{
			return "ok";
		}
		return "notyet";
	}
}
