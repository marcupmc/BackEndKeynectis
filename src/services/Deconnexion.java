package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import controller.ControllerAuthentification;
import dao.DAOLog;
import domain.TypeLog;

@Path("/deconnexion")
public class Deconnexion {

	
	@POST 
	@Consumes("application/x-www-form-urlencoded")
	public String deconect(MultivaluedMap<String, String> formParam)
	{
			DAOLog.getInstance().addLog(TypeLog.DECONNEXION, formParam.get("ipClient").get(0), formParam.get("idClient").get(0) );
			return "";
	}
}
