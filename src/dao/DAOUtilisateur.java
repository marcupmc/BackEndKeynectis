package dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tools.CryptoTool;

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

	private DAOUtilisateur() {
	}

	/** L'instance statique */
	private static DAOUtilisateur instance;

	/**
	 * 
	 * @param identifiant
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param phoneNumber
	 * @param documents
	 * @return true if the user has been add successully, and false if there was an error
	 */
	public boolean addUser(String identifiant,String firstName,String lastName,String email,String password, String phoneNumber,Set<DocumentPDF> documents){
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
			user.setIdentifiant(identifiant);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			
			//On crypte le mot de passe ici
			try {
				user.setPassword(CryptoTool.getEncodedPassword(password));
			} catch (NoSuchAlgorithmException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
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

	/**
	 * 
	 * @param id
	 * @return the user with the id "id". Return null if the user doesn't exist
	 */
	public Utilisateur getUserById(long id){
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.id = '"+id+"'");
			users = (ArrayList<Utilisateur>) q.list();
			tx.commit();
			session.close();
			if(users.size()==1)
				return users.get(0);  
			else
				return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return the user with the identifiant "id". Return null if the user doesn't exist
	 */
	public Utilisateur getUserByIdentifiant(String id){
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.identifiant = '"+id+"'");
			users = (ArrayList<Utilisateur>) q.list();
			tx.commit();
			session.close();
			if(users.size()==1)
				return users.get(0);  
			else
				return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
