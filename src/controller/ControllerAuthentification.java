package controller;

import java.security.NoSuchAlgorithmException;

import tools.CryptoTool;
import dao.DAOUtilisateur;
import domain.Utilisateur;

public class ControllerAuthentification {

	//SINGLETON
	public static ControllerAuthentification getInstance() {
		if (null == instance) { // Premier appel
			instance = new ControllerAuthentification();
		}
		return instance;
	}

	/** Constructeur redéfini comme étant privé pour interdire
	 * son appel et forcer à passer par la méthode <link
	 */
	private ControllerAuthentification() {
	}

	/** L'instance statique */
	private static ControllerAuthentification instance;

	
	/**
	 * Check if the login match with the password
	 * @param login
	 * @param password
	 * @return true if the login and the password match, false if not
	 */
	public boolean isAuthentified(String login, String password){
		// On vérifie les entrées
		if(login==null||password==null||login.length()==0||password.length()==0)return false;
	
		//On recupere le client
		Utilisateur toCheck = DAOUtilisateur.getInstance().getUserByIdentifiant(login);
		
		//On test si il est null
		if (toCheck==null)return false;
		
		String cipherPass="";
		try {
			cipherPass = CryptoTool.getEncodedPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		//Compare les mots de passe
		if(toCheck.getPassword().equals(cipherPass))return true;
		
		return false;
	}
	
}

