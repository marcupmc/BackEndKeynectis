package tools; 

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import domain.DocumentPDF;
import domain.Utilisateur;

public class JSONFactory
{

	// SINGLETON
	public static JSONFactory getInstance()
	{
		if (null == instance)
		{
			instance = new JSONFactory();
		}
		return instance;
	}

	/**
	 * Constructeur redéfini comme étant privé pour interdire son appel et
	 * forcer à passer par la méthode <link
	 */
	private JSONFactory()
	{
	}

	/** L'instance statique */
	private static JSONFactory instance;

	/**
	 * Make a Json from a user
	 * 
	 * @param user
	 * @return make json object from the user with all his pdf files
	 */
	public JSONObject makeUserJSON(Utilisateur user)
	{
		JSONObject toSend = new JSONObject();
		try
		{
			toSend.put("firstName", user.getFirstName());
			toSend.put("lastName", user.getLastName());
			// toSend.put("email", user.getEmail());
			toSend.put("pdf", this.getPDFInfo(user));
			if (user.getSignature() == null)
				toSend.put("signature", "null");
			else
			{
				byte[] decode = EncoderBase64.encodingBlobToByteArray(user
						.getSignature());
				String chaine = EncoderBase64.byteArraytoStringBase64(decode);
				toSend.put("signature", chaine);
			}
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toSend;
	}

	/**
	 * 
	 * @param user
	 * @return make an array of json object which contains all the pdf info of
	 *         the user
	 */
	public ArrayList<JSONObject> getPDFInfo(Utilisateur user)
	{
		ArrayList<JSONObject> toReturn = new ArrayList<JSONObject>();
		for (DocumentPDF doc : user.getDocuments())
		{
			JSONObject jsonToAdd = new JSONObject();
			try
			{
				jsonToAdd.put("name", doc.getName());
				jsonToAdd.put("url", doc.getUrl());
				jsonToAdd.put("isCertified", doc.isCertified());
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toReturn.add(jsonToAdd);
		}
		return toReturn;
	}
}
