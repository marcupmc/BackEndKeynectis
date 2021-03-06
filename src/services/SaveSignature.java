package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import servlets.AddDocument;
import tools.EncoderBase64;
import dao.DAOUtilisateur;
import domain.Log;
import domain.TypeLog;

@Path("/signature")
public class SaveSignature
{

	/**
	 * Save a signature into the database from an id and the signature (base64)
	 * 
	 * @param formParam
	 * @return "error" if there was a problem and "ok" if not
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String saveSignature(MultivaluedMap<String, String> formParam)
	{
		String identifiant = formParam.get("idClient").get(0);
		String imageSignature = formParam.get("imgSignature").get(0);

		String ipClient = formParam.get("ipClient").get(0);
		System.out.println("Identifiant : " + identifiant);
		System.out.println("Signature Base 64 : " + imageSignature);

		byte[] decodedBytes = EncoderBase64
				.base64StringToByteArray(imageSignature);

		if (identifiant == null || identifiant.length() == 0
				|| imageSignature == null || imageSignature.length() == 0)
			return "error";
		if (DAOUtilisateur.getInstance()
				.addSignature(identifiant, decodedBytes))
		{

			final Marker marker = MarkerFactory
					.getMarker(TypeLog.CHANGEMENT_SIGNATURE.toString());
			final Logger logger = LoggerFactory.getLogger(AddDocument.class);
			Log l = new Log();
			l.setIdentifiant_client(identifiant);
			l.setIpadresse(ipClient);
			logger.info(marker, "Changement de signature", l);

			return "ok";
		}
		else
			return "error";

	}
}
