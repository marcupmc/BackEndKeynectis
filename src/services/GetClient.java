package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import controller.ControllerGetClient;

@Path("/getClient")
public class GetClient {

	@GET
	@Path("/all")
	@Consumes("application/x-www-form-urlencoded")
	public String getAllClient(){
		return ControllerGetClient.getInstance().getAllClient().toString();
	}
	
	@GET
	@Path("/byid")
	@Consumes("application/x-www-form-urlencoded")
	public String getClientById(@QueryParam("id") long id){
		return ControllerGetClient.getInstance().getClientById(id).toString();
	}
}
