package dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.DocumentPDF;
import domain.Utilisateur;

public class DAODocumentPDF {

	//SINGLETON
	public static DAODocumentPDF getInstance() {
		if (null == instance) { 
			instance = new DAODocumentPDF();
		}
		return instance;
	}


	private DAODocumentPDF() {
	}

	private static DAODocumentPDF instance;

	/**
	 * Save document for a client. The added document is not certified by default
	 * @param idOwner
	 * @param name
	 * @param url
	 * @return true if the document is added, false if not
	 */
	public boolean addDocument(long idOwner, String name, String url){
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idOwner);
		if(user==null)return false;
		if(name==null||name.length()==0||url==null||url.length()==0)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF doc = new DocumentPDF();
			doc.setName(name);
			doc.setUrl(url);
			doc.setOwner(user);
			doc.setCertified(false);
			doc.setSignatureName(""); //TODO A modifier car peut �tre parametrable

			session.save(doc); 
			tx.commit();
			session.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Delete a document by it id
	 * @param idDocument
	 * @return true if the document has been delete from db, false if not
	 */
	public boolean deleteDocument(long idDocument) {
		// TODO Auto-generated method stub
		if(idDocument<=0)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF doc = DAODocumentPDF.getInstance().getById(idDocument);
			if(doc==null)return false;
			
			session.delete(doc);
			tx.commit();
			session.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Find a document by id
	 * @param idDocument
	 * @return the document with an id = idDocument, null if it doesn't exist
	 */
	public DocumentPDF getById(long idDocument) {
		// TODO Auto-generated method stub

		ArrayList<DocumentPDF> docs = new ArrayList<DocumentPDF>();
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q =session.createQuery("from DocumentPDF as c where c.id = '"+idDocument+"'");
			docs = (ArrayList<DocumentPDF>) q.list();

			tx.commit();
			session.close();
			if(docs.size()==1)
				return docs.get(0);  
			else
				return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public DocumentPDF getByUrl(String url) {
		// TODO Auto-generated method stub

		ArrayList<DocumentPDF> docs = new ArrayList<DocumentPDF>();
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q =session.createQuery("from DocumentPDF as c where c.url = '"+url+"'");
			docs = (ArrayList<DocumentPDF>) q.list();

			tx.commit();
			session.close();
			if(docs.size()==1)
				return docs.get(0);  
			else
				return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean changeUrl(long id, String url){
		if(url==null||url.length()==0)return false;
		DocumentPDF doc = this.getById(id);
		if(doc==null)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			
			doc.setUrl(url);
			session.update(doc);
			tx.commit();
			session.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean certifiedPDF(long id){
		DocumentPDF doc = this.getById(id);
		if(doc==null)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			
			doc.setCertified(true);
			session.update(doc);
			tx.commit();
			session.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean setPosSignature(long id, float posx, float posy,float width,float height){
		DocumentPDF doc = this.getById(id);
		if(doc==null)return false;
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			
			doc.setSignatureX(posx);
			doc.setSignatureY(posy);
			doc.setHeightSignature(height);
			doc.setWidthSignature(width);
			session.update(doc);
			tx.commit();
			session.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
}
