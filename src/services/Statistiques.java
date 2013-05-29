package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import controller.ControllerCalculStats;

@Path("/statistiques")
public class Statistiques {

	@GET
	@Path("/connexionperhours")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * TODO
	 * @return
	 */
	public String getConnexionsPerHour()
	{
		return ControllerCalculStats.getInstance().getConnexionPerHour().toString();
	}
	
	@GET
	@Path("/connexionreport")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * TODO
	 * @return
	 */
	public String getConnexionReport(){
		return ControllerCalculStats.getInstance().getConnexionReport().toString();
	}
	
	@GET
	@Path("/errorpertype")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * TODO
	 * @return
	 */
	public String getErrorPerType(){
		return ControllerCalculStats.getInstance().getErrorPerType().toString();
	}
	
	@GET
	@Path("/errorperday")
	@Consumes("application/x-www-form-urlencoded")
	/**
	 * TODO
	 * @return
	 */
	public String getErrorPerDay(){
		return ControllerCalculStats.getInstance().getErrorPerHour().toString();
	}
	
	
}
