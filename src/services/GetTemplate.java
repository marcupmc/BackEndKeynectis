package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import controller.ControllerGetClient;
import controller.ControllerGetTemplate;

@Path("/getTemplate")
public class GetTemplate {

	
	@GET
	@Path("/all")
	@Consumes("application/x-www-form-urlencoded")
	public String getAllTemplate(){
		return ControllerGetTemplate.getInstance().getAllTemplate().toString();
	}
	
	@GET
	@Path("/byid")
	@Consumes("application/x-www-form-urlencoded")
	public String getClientById(@QueryParam("id") long id){
		return ControllerGetTemplate.getInstance().getTemplateById(id).toString();
	}
}
