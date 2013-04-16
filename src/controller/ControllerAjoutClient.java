package controller;

import java.util.HashSet;
import java.util.Set;

import dao.DAOUtilisateur;
import domain.DocumentPDF;

public class ControllerAjoutClient
{

	// SINGLETON
	public static ControllerAjoutClient getInstance()
	{
		if (null == instance)
		{ // Premier appel
			instance = new ControllerAjoutClient(); 
		}
		return instance;
	}

	private ControllerAjoutClient()
	{
	}

	private static ControllerAjoutClient instance;

	/**
	 * Add a Client into the DB by checking his attributes first
	 * 
	 * @param identifiant
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param password
	 * @return true if the client was add, false if not
	 */
	public boolean addClientInDB(String identifiant, String nom, String prenom,
			String email, String telephone, String password)
	{
		// Verifier les entrées
		if (null == nom || 0 == nom.length() || null == prenom
				|| 0 == prenom.length() || null == email || 0 == email.length()
				// || null == telephone || 0 == telephone.length()
				|| null == password || 0 == password.length()
				|| null == identifiant || 0 == identifiant.length())
			return false;

		// Verify that the id doesn't already exist
		if (null != DAOUtilisateur.getInstance().getUserByIdentifiant(
				identifiant))
			return false;

		// On construit une liste de documents
		Set<DocumentPDF> documents = new HashSet<DocumentPDF>();
		return DAOUtilisateur.getInstance().addUser(identifiant, prenom, nom,
				email, password, telephone, documents);
	}

}
