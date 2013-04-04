package dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controller.Initialisator;
import domain.DocumentPDF;
import domain.Utilisateur;

public class DAOUtilisateur {

	//SINGLETON
	public static DAOUtilisateur getInstance() {
		if (null == instance) { // Premier appel
			instance = new DAOUtilisateur();
		}
		return instance;
	}

	/** Constructeur redéfini comme étant privé pour interdire
	 * son appel et forcer à passer par la méthode <link
	 */
	private DAOUtilisateur() {
	}

	/** L'instance statique */
	private static DAOUtilisateur instance;

	
	public boolean addUser(String firstName,String lastName,String email,String password, String phoneNumber,Set<DocumentPDF> documents){
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			//create a contact and save it into the DB
			
			Utilisateur user = new Utilisateur();
			user.setEmail(email);
			
			user.setDocuments(documents);
			for(DocumentPDF doc : documents){
				doc.setOwner(user);
			
			}
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			
			//save the contact into the DB
			session.save(user); // or session.persist(contact);
			//if you modify one of its properties, no need to save it again
			
			//mandatory to flush the data into the DB
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
}
