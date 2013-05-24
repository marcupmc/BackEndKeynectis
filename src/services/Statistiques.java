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
	public String getConnexionsPerHour()
	{
		String toSend =ControllerCalculStats.getInstance().getConnexionPerHour().toString();
		System.out.println("TOSEND : "+toSend);
		return toSend;
	}
	
	@GET
	@Path("/connexionreport")
	@Consumes("application/x-www-form-urlencoded")
	public String getConnexionReport(){
		return ControllerCalculStats.getInstance().getConnexionReport().toString();
	}
	
	@GET
	@Path("/errorpertype")
	@Consumes("application/x-www-form-urlencoded")
	public String getErrorPerType(){
		return ControllerCalculStats.getInstance().getErrorPerType().toString();
	}
	
	@GET
	@Path("/errorperday")
	@Consumes("application/x-www-form-urlencoded")
	public String getErrorPerDay(){
		return ControllerCalculStats.getInstance().getErrorPerDay().toString();
	}
	
	
}
