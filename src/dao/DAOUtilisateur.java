package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.BlobImplementer;

import tools.CryptoTool;
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
			user.setSignature(null);

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
			session.close();
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

	public ArrayList<Utilisateur> getUsersByRegex(String s)
	{
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		String regex = "%"+s+"%";
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.lastName like '"+regex+"'" +
					" or c.firstName like '"+regex+"' " +
					" or c.email like '"+regex+"'" +
					" or c.identifiant like '"+regex+"'" +
					" order by c.lastName");
			users = (ArrayList<Utilisateur>) q.list();
			return users;
		}catch(Exception e){
			return null;
		}
		
	}

	public boolean deleteUser(long idClient) {
		// TODO Auto-generated method stub
		if(idClient<=0)return false;
		Session session=null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			
			Utilisateur user = this.getUserById(idClient);
			if(user==null)return false;
			
			session.delete(user);
			tx.commit();
			session.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean addSignature(String identifiant, byte[] signature){
		if(identifiant==null || identifiant.length()==0||signature==null||signature.length==0)return false;
		
		Utilisateur user = this.getUserByIdentifiant(identifiant);
		if(user==null)return false;
		Session session=null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//begin a transaction
			org.hibernate.Transaction tx = session.beginTransaction();
			
			user.setSignature(Hibernate.createBlob(signature));
			
			session.update(user);
			tx.commit();
			session.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
