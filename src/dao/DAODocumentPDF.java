package dao;

import java.net.URL;
import java.util.ArrayList;

import model.TagParameter;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import tools.ToolsPDF;
import controller.ControllerAjoutTypeCertification;
import domain.CertificationType;
import domain.DocumentPDF;
import domain.Signature;
import domain.Utilisateur;

public class DAODocumentPDF
{

	// SINGLETON
	public static DAODocumentPDF getInstance()
	{
		if (null == instance)
		{
			instance = new DAODocumentPDF();
		}
		return instance;
	}

	private DAODocumentPDF()
	{
	}

	private static DAODocumentPDF instance;

	/**
	 * Save document for a client. The added document is not certified by
	 * default
	 * 
	 * @param idOwner
	 * @param name
	 * @param url
	 * @return true if the document is added, false if not
	 */
	public boolean addDocument(long idOwner, String name, String url)
	{ 
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idOwner);
		if (user == null)
			return false;
		if (name == null || name.length() == 0 || url == null
				|| url.length() == 0)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF doc = new DocumentPDF();
			doc.setName(name);
			doc.setUrl(url);
			doc.setOwner(user);
			doc.setCertified("En attente");
			doc.setNbPage(4);
			
			//A ajouter aussi dans le add document avec une zone de signature
			byte[] bits = ToolsPDF.getAsByteArray(new URL(url));
			doc.setContenu(Hibernate.createBlob(bits));
			// ajouter ici la conversion en blob et l'ajout en bdd voir signature user

			session.save(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Save document for a client. The added document is not certified by
	 * default
	 * 
	 * @param idOwner
	 * @param name
	 * @param url
	 * @return true if the document is added, false if not
	 */
	public boolean addDocument(long idOwner, String name, String url,
			String sigName)
	{
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idOwner);
		if (user == null)
			return false;
		if (name == null || name.length() == 0 || url == null
				|| url.length() == 0)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF doc = new DocumentPDF();
			doc.setName(name);
			doc.setUrl(url);
			doc.setOwner(user);
			doc.setCertified("En attente");

			// doc.setSignatureName(sigName); //TODO A modifier car peut être
			// parametrable

			session.save(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Delete a document by it id
	 * 
	 * @param idDocument
	 * @return true if the document has been delete from db, false if not
	 */
	public boolean deleteDocument(long idDocument)
	{
		if (idDocument <= 0)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF doc = this.getById(idDocument);
			if (doc == null)
				return false;

			session.delete(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Find a document by id
	 * 
	 * @param idDocument
	 * @return the document with an id = idDocument, null if it doesn't exist
	 */
	public DocumentPDF getById(long idDocument)
	{
		ArrayList<DocumentPDF> docs = new ArrayList<DocumentPDF>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from DocumentPDF as c where c.id = '"
							+ idDocument + "'");
			docs = (ArrayList<DocumentPDF>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
			if (docs.size() == 1)
				return docs.get(0);
			else
				return null;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Find a document by it url
	 * 
	 * @param url
	 * @return the document if it exist, null if not
	 */
	public DocumentPDF getByUrl(String url)
	{
		ArrayList<DocumentPDF> docs = new ArrayList<DocumentPDF>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from DocumentPDF as c where c.url = '" + url
							+ "'");
			docs = (ArrayList<DocumentPDF>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
			if (docs.size() == 1)
				return docs.get(0);
			else
				return null;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Change the url of the document describes by its id
	 * 
	 * @param id
	 * @param url
	 * @return true if the url has been changed, false if not
	 */
	public boolean changeUrl(long id, String url)
	{
		if (url == null || url.length() == 0)
			return false;
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			doc.setUrl(url);
			session.update(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Change the certified property of a document to true
	 * 
	 * @param id
	 * @return true if the document is now certified, false if not
	 */
	public boolean certifiedPDF(long id)
	{
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			doc.setCertified("Certifie");
			session.update(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean certifiedEchecPDF(long id)
	{
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			doc.setCertified("Erreur");
			session.update(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Set the position of a signature in a document
	 * 
	 * @param id
	 * @param posx
	 * @param posy
	 * @param width
	 * @param height
	 * @return true if the signature's has been saved, false if not
	 */
	public boolean setPosSignature(long id, float posx, float posy,
			float width, float height, int numPage, String sigName)
	{
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Signature signature = new Signature();

			signature.setSignatureX(posx);
			signature.setSignatureY(posy);
			signature.setHeightSignature(height);
			signature.setWidthSignature(width);
			signature.setName(sigName);
			signature.setPageNumber(numPage);
			signature.setDocument(doc);

			session.save(signature);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Set the certification type to operate on a document
	 * 
	 * 
	 */
	public boolean setCertificationType(long id, TagParameter type)
	{
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = type.getType();
			if(null == certif)
			{
				certif = DAOCertificationType.getInstance().getByTypesIdAndName(type.getId_type(), type.getName());
				type.setType(certif);
			}

			doc.setType(certif);
			DAOCertificationType.getInstance().addDocumentToCertif(certif, doc);

			session.update(doc);
			session.update(certif);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Set the certification type to operate on a document
	 * 
	 * 
	 */
	public boolean setCertificationType(long id, String id_type, String name)
	{
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = ControllerAjoutTypeCertification.getInstance().getType(id_type, name).getType();

			doc.setType(certif);
			DAOCertificationType.getInstance().addDocumentToCertif(certif, doc);

			session.update(doc);
			session.update(certif);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Find a document by id
	 * 
	 * @param idDocument
	 * @return the document with an id = idDocument, null if it doesn't exist
	 */
	public ArrayList<DocumentPDF> getDocumentsByOwnerOrderByAlphaBet(
			long idOwner)
	{
		ArrayList<DocumentPDF> docs = new ArrayList<DocumentPDF>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from DocumentPDF as c where c.owner.id = '"
							+ idOwner + "' order by c.name");
			docs = (ArrayList<DocumentPDF>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();

			return docs;

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Add a content to a document from bits array and it id
	 * @param id
	 * @param bits
	 * @return true if the content was add, false if not
	 */
	public boolean addContent(long id, byte[]bits){
		DocumentPDF doc = this.getById(id);
		if (doc == null)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			doc.setContenu(Hibernate.createBlob(bits));

			session.update(doc);
			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public int getNumberOfCertifiedDocument(){
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF docExample = new DocumentPDF();
			docExample.setCertified("Certifie");
			
			int nbToReturn = session.createCriteria(DocumentPDF.class).add(Example.create(docExample) ).list().size();

			tx.commit();
			session.close();
			sessionFactory.close();
			return nbToReturn;
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int getNumberOfErrorDocument(){
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF docExample = new DocumentPDF();
			docExample.setCertified("Erreur");
			
			int nbToReturn = session.createCriteria(DocumentPDF.class).add(Example.create(docExample) ).list().size();

			tx.commit();
			session.close();
			sessionFactory.close();
			return nbToReturn;
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int getNumberOfWaitingDocument(){
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			DocumentPDF docExample = new DocumentPDF();
			docExample.setCertified("En attente");
			
			int nbToReturn = session.createCriteria(DocumentPDF.class).add(Example.create(docExample) ).list().size();

			tx.commit();
			session.close();
			sessionFactory.close();
			return nbToReturn;
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
