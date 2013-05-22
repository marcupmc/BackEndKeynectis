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

import controller.ControllerAuthentification;
import dao.DAOLog;
import domain.Log;
import domain.TypeLog;

@Path("/deconnexion")
public class Deconnexion {


	@POST 
	@Consumes("application/x-www-form-urlencoded")
	public String deconect(MultivaluedMap<String, String> formParam)
	{
		final Marker marker = MarkerFactory.getMarker(TypeLog.DECONNEXION.toString());
		final Logger logger = LoggerFactory.getLogger(AddDocument.class);
		Log l = new Log();
		l.setIdentifiant_client( formParam.get("idClient").get(0));
		l.setIpadresse(formParam.get("ipClient").get(0));
		logger.info(marker, "Deconnexion reussie", l);
		return "";
	}
}
