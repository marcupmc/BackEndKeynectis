package controller;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dao.DAOCertificationType;
import dao.DAOUtilisateur;
import domain.Utilisateur;

public class ControllerGetClient {

	// SINGLETON
		public static ControllerGetClient getInstance()
		{
			if (null == instance)
			{ // Premier appel
				instance = new ControllerGetClient();
			}
			return instance;
		}


		private ControllerGetClient()
		{
		}  

		private static ControllerGetClient instance;

		
		public JSONObject getAllClient(){
			ArrayList<Utilisateur> users = DAOUtilisateur.getInstance().getUsersByRegex("");
			ArrayList<JSONObject> jsonOfAllUser = new ArrayList<JSONObject>();
			JSONObject jsonToReturn = new JSONObject();
			try {
			for (Utilisateur utilisateur : users) {
				JSONObject jsonOfOneUser = new JSONObject();
				jsonOfOneUser.put("id", utilisateur.getId());
				jsonOfOneUser.put("nom", utilisateur.getLastName());
				jsonOfOneUser.put("prenom", utilisateur.getFirstName());
				jsonOfAllUser.add(jsonOfOneUser);
			}
			jsonToReturn.put("clients",jsonOfAllUser);
			}catch(JSONException e){
				e.printStackTrace();
			}
			return jsonToReturn;
		}
		
		public JSONObject getClientById(long id){
			Utilisateur user = DAOUtilisateur.getInstance().getUserById(id);
			JSONObject jsonToReturn = new JSONObject();
			try{
				jsonToReturn.put("id", user.getId());
				jsonToReturn.put("nom", user.getLastName());
				jsonToReturn.put("prenom",user.getFirstName());
				jsonToReturn.put("email",user.getEmail());
				jsonToReturn.put("telephone",user.getPhoneNumber());
			}catch(JSONException e){
				e.printStackTrace();
			}
			return jsonToReturn;
		
		}
}
