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

import sun.jdbc.odbc.OdbcDef;
import tools.CryptoTool;
import domain.DocumentPDF;
import domain.Utilisateur;

public class DAOUtilisateur {

	//SINGLETON
	public static DAOUtilisateur getInstance() {
		if (null == instance) { 
			instance = new DAOUtilisateur();
		}
		return instance;
	}

	private DAOUtilisateur() {
	}

	
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
	public boolean addUser(String identifiant,String firstName,String lastName,String email,
			String password, String phoneNumber,Set<DocumentPDF> documents){
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

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

			try {
				user.setPassword(CryptoTool.getEncodedPassword(password));
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
			user.setPhoneNumber(phoneNumber);
			session.save(user); 
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Find a user by his id
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
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.id = '"+id+"'");
			users = (ArrayList<Utilisateur>) q.list();
			tx.commit();
			session.close();
			sessionFactory.close();
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
	 * Find a user by his identifiant
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
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.identifiant = '"+id+"'");
			users = (ArrayList<Utilisateur>) q.list();
			tx.commit();
			session.close();
			sessionFactory.close();
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
	 * Find the users that match with the string in parameter
	 * @param s
	 * @return a list of users
	 */
	public ArrayList<Utilisateur> getUsersByRegex(String s)
	{
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		String regex = "%"+s+"%";
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q =session.createQuery("from Utilisateur as c where c.lastName like '"+regex+"'" +
					" or c.firstName like '"+regex+"' " +
					" or c.email like '"+regex+"'" +
					" or c.identifiant like '"+regex+"'" +
					" order by c.lastName");
			users = (ArrayList<Utilisateur>) q.list();
			session.close();
			sessionFactory.close();
			return users;
		}catch(Exception e){
			return null;
		}
		
	}

	/**
	 * Delete a user, and all his document because of the cascade
	 * @param idClient
	 * @return true if the user is deleted, false if not
	 */
	public boolean deleteUser(long idClient) {
		// TODO Auto-generated method stub
		if(idClient<=0)return false;
		Session session=null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			
			Utilisateur user = this.getUserById(idClient);
			if(user==null)return false;
			
			session.delete(user);
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Add a signature to a user
	 * @param identifiant
	 * @param signature
	 * @return true if the signature has been added, false if not
	 */
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
			org.hibernate.Transaction tx = session.beginTransaction();
			
			user.setSignature(Hibernate.createBlob(signature));
			
			session.update(user);
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Change the parameter that define the certification of a document, and set it to True
	 * @param identifiant
	 * @param url
	 * @return true if the document has been updated, false if not
	 */
	public boolean certifiedDocument(String identifiant, String url){
		Utilisateur user = this.getUserByIdentifiant(identifiant);
		
		if(user==null)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			for(DocumentPDF docs : user.getDocuments()){
				if(docs.getUrl().equals(url))
				{
					docs.setCertified(true);
					session.update(docs);
				}
			}
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Replace the url of a document by a new one
	 * @param identifiant
	 * @param oldUrl
	 * @param newURl
	 * @return
	 */
	public boolean changeUrlDocument(String identifiant, String oldUrl,String newURl){
		Utilisateur user = this.getUserByIdentifiant(identifiant);
		if(user==null)return false;
		
		if(oldUrl==null||oldUrl.length()==0||newURl==null||newURl.length()==0)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			for(DocumentPDF docs : user.getDocuments()){
				if(docs.getUrl().equals(oldUrl))
				{
					docs.setUrl(newURl);
					session.update(docs);
				}
			}
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
