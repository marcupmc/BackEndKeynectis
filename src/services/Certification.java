package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import controller.ControllerCertification;

/**
 * {@link Deprecated}
 * 
 * @author magregoi
 * 
 */
@Path("/certification")
public class Certification
{

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String certificationPDF(MultivaluedMap<String, String> formParam)
	{
		System.out.println("JE DEMANDE LA CERTIFICATION");

		int nbDoc = Integer.parseInt(formParam.get("nbDoc").get(0));
		if (nbDoc <= 0)
			return "error";

		String identifiant = formParam.get("identifiant").get(0);
		if (identifiant == null || identifiant.length() == 0)
			return "error";
		ArrayList<String> urls = new ArrayList<String>();
		for (int i = 0; i < nbDoc; i++)
		{
			urls.add(formParam.get("pdfs[" + i + "][url]").get(0));
		}

		String urlRetour = "http://10.0.2.2:8080/TestRest/ResponseKeynectis";
		String saveFile = "temp_xml/";
		String certFolder = "CERT/";

		System.out.println("[TEST KEYNECTIS] On lance la certif");
		HashMap<String, String> toReturn = ControllerCertification
				.getInstance().certificationPDF(identifiant, urls.get(0),
						urlRetour, saveFile, certFolder);
		String ret = toReturn.get("blob");

		return ret;
	}
}
