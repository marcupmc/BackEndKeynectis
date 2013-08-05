package controller;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dao.DAOTemplate;
import dao.DAOUtilisateur;
import domain.Template;
import domain.Utilisateur;

public class ControllerGetTemplate {

	// SINGLETON
	public static ControllerGetTemplate getInstance()
	{
		if (null == instance)
		{ // Premier appel
			instance = new ControllerGetTemplate();
		}
		return instance;
	}


	private ControllerGetTemplate()
	{
	}  

	private static ControllerGetTemplate instance;

	public JSONObject getAllTemplate(){
		ArrayList<Template> templates = DAOTemplate.getInstance().getTemplateByRegex("");
		ArrayList<JSONObject> jsonOfAllTemplate = new ArrayList<JSONObject>();
		JSONObject jsonToReturn = new JSONObject();
		try {
		for (Template template : templates) {
			JSONObject jsonOfOneUser = new JSONObject();
			jsonOfOneUser.put("id", template.getId());
			jsonOfOneUser.put("nom", template.getNom());
			jsonOfOneUser.put("url", template.getUrl());
			jsonOfAllTemplate.add(jsonOfOneUser);
		}
		jsonToReturn.put("templates",jsonOfAllTemplate);
		}catch(JSONException e){
			e.printStackTrace();
		}
		return jsonToReturn;
	}
	
	public JSONObject getTemplateById(long id){
		Template template = DAOTemplate.getInstance().getById(id);
		JSONObject jsonToReturn = new JSONObject();
		try{
			jsonToReturn.put("id", template.getId());
			jsonToReturn.put("nom", template.getNom());
			jsonToReturn.put("url",template.getUrl());
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		return jsonToReturn;
	
	}
}
