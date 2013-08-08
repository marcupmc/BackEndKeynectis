package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.w3c.dom.Document;

import tools.ToolsXMLTemplate;

@Path("/testservice")
public class TestService {

	
	@GET
	@Path("/test")
	@Consumes("application/x-www-form-urlencoded")
	public String getInfoClient(@QueryParam("idClient") long id){
		Document doc =  ToolsXMLTemplate.buildXMLTemplate(id);
		return ToolsXMLTemplate.docToString(doc);
//		return ToolsXMLTemplate.getInfoClient(id).get("nom");
	}
}
