package services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import sun.misc.BASE64Decoder;
import tools.EncoderBase64;

import controller.ControllerAuthentification;
import dao.DAOLog;
import dao.DAOUtilisateur;
import domain.TypeLog;

@Path("/signature")
public class SaveSignature {

	/**
	 * Save a signature into the database from an id and the signature (base64)
	 * @param formParam
	 * @return "error" if there was a problem and "ok" if not
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String saveSignature(MultivaluedMap<String, String> formParam)
	{
		String identifiant  = formParam.get("idClient").get(0);
		String imageSignature = formParam.get("imgSignature").get(0);
		String ipClient=formParam.get("ipClient").get(0);
		System.out.println("Identifiant : "+identifiant);
		System.out.println("Signature Base 64 : "+imageSignature);
		
		byte[] decodedBytes = EncoderBase64.base64StringToByteArray(imageSignature);
		
		if(identifiant==null || identifiant.length()==0||imageSignature==null||imageSignature.length()==0)return "error";
		if(DAOUtilisateur.getInstance().addSignature(identifiant, decodedBytes)){
			
			DAOLog.getInstance().addLog(TypeLog.CHANGEMENT_SIGNATURE, ipClient, identifiant);
			return "ok";
			}
		else return "error";
	}
}
