package controller;

import java.util.HashSet;
import java.util.Set;

import dao.DAOUtilisateur;
import domain.DocumentPDF;

/**
 * {@link Deprecated}
 * 
 * @author magregoi
 * 
 */
public class Initialisator
{

	// SINGLETON
	public static Initialisator getInstance()
	{
		if (null == instance)
		{
			instance = new Initialisator();
		}
		return instance;
	}

	private Initialisator()
	{
	}

	private static Initialisator instance;

	public boolean initBDD()
	{
		// On cree les documents

		// Document 1
		DocumentPDF doc1 = new DocumentPDF();
		doc1.setCertified("En attente");
		doc1.setName("Contrat Assurance");
		doc1.setUrl("http://wwww.mma.fr/documents/contrats");

		// Document 2
		DocumentPDF doc2 = new DocumentPDF();
		doc2.setCertified("En attente");
		doc2.setName("Facture");
		doc2.setUrl("http://wwww.mma.fr/documents/factures");

		// On cree la HashSet de document
		Set<DocumentPDF> documents = new HashSet<DocumentPDF>();
		documents.add(doc1);
		documents.add(doc2);

		return DAOUtilisateur.getInstance().addUser("MGregoire", "Marc",
				"Gregoire", "marc.gregoire@gmail.com", "marcgregoire",
				"0102030405", documents);

	}
}
