package controller;

import java.util.HashSet;
import java.util.Set;

import dao.DAOUtilisateur;
import domain.DocumentPDF;

public class ControllerAjoutClient {

	//SINGLETON
		public static ControllerAjoutClient getInstance() {
			if (null == instance) { // Premier appel
				instance = new ControllerAjoutClient();
			}
			return instance;
		}

		private ControllerAjoutClient() {
		}
 
		
		private static ControllerAjoutClient instance;
		
		/**
		 * Add a Client into the DB by checking his attributes first
		 * @param identifiant
		 * @param nom
		 * @param prenom
		 * @param email
		 * @param telephone
		 * @param password
		 * @return true if the client was add, false if not
		 */
		public boolean addClientInDB(String identifiant, String nom,String prenom,
				String email,String telephone,String password){
			//Verifier les entrées
			if(nom==null || nom.length()== 0 ||
					prenom==null || prenom.length()==0 ||
					email==null || email.length()==0 ||
					telephone == null || telephone.length()==0 ||
					password == null || password.length()==0 ||
					identifiant == null || identifiant.length()==0 
					) return false;
			
			// Verifier que l'identifiant n'existe pa deja
			if(DAOUtilisateur.getInstance().getUserByIdentifiant(identifiant)!=null)return false;
			
			//On construit une liste de documents
			Set<DocumentPDF> documents=new HashSet<DocumentPDF>();
			return DAOUtilisateur.getInstance().addUser(identifiant, prenom, nom, email, password,telephone ,documents );
		}

}
