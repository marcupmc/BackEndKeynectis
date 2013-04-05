package controller;

import java.util.HashSet;
import java.util.Set;

import dao.DAOUtilisateur;
import domain.DocumentPDF;
import domain.Utilisateur;

public class Initialisator {

	//SINGLETON
	public static Initialisator getInstance() {
		if (null == instance) { // Premier appel
			instance = new Initialisator();
		}
		return instance;
	}

	
	private Initialisator() {
	}

	/** L'instance statique */
	private static Initialisator instance;
	
	// Faire ici une méthode qui initialise la BDD
	public boolean initBDD(){
		// On cree les documents
		
		//Document 1
		DocumentPDF doc1  = new DocumentPDF();
		doc1.setCertified(false);
		doc1.setName("Contrat Assurance");
		doc1.setUrl("http://wwww.mma.fr/documents/contrats");
		
		//Document 2
		DocumentPDF doc2  = new DocumentPDF();
		doc2.setCertified(false);
		doc2.setName("Facture");
		doc2.setUrl("http://wwww.mma.fr/documents/factures");
		
		//On cree la HashSet de document
		Set<DocumentPDF>documents = new HashSet<DocumentPDF>();
		documents.add(doc1);
		documents.add(doc2);
		
//		// On cree un Utilisateur
//		Utilisateur user1 = new Utilisateur();
//		user1.setEmail("marc.gregoire@gmail.com");
//		user1.setFirstName("Marc");
//		user1.setLastName("Gregoire");
//		user1.setPassword("marcgregoire");
//		user1.setPhoneNumber("0102030405");
//		user1.setDocuments(documents);
//		
//		// On affecte le owner de chaque doc
//		doc1.setOwner(user1);
//		doc2.setOwner(user1);
		
		// On fait appel au dao
		return DAOUtilisateur.getInstance().addUser("MGregoire","Marc", "Gregoire", "marc.gregoire@gmail.com", "marcgregoire", "0102030405", documents);
		
		
	}
}
