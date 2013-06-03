/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.TagParameter;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controller.ControllerAjoutTypeCertification;
import domain.CertificationType;
import domain.DocumentPDF;

/**
 * @author dtadmi
 * 
 */
public class DAOCertificationType
{

	// SINGLETON
	public static DAOCertificationType getInstance()
	{
		if (null == instance)
		{
			instance = new DAOCertificationType();
		}
		return instance;
	}

	private DAOCertificationType()
	{
	}

	private static DAOCertificationType instance;

	/**
	 * @param id
	 * @param documents
	 * @param name
	 * @param id_type
	 * @param dATA_METIER
	 * @param pDF_REASON
	 * @param pDF_LOCATION
	 * @param pDF_CONTACT
	 * @param defaut
	 */
	public CertificationType createCertificationType(long id,
			Set<DocumentPDF> documents, String name, String id_type,
			String dATA_METIER, String pDF_REASON, String pDF_LOCATION,
			String pDF_CONTACT, boolean defaut)
	{
		CertificationType type = new CertificationType();
		type.setId(id);
		type.setDocuments(documents);
		type.setName(name);
		type.setId_type(id_type);
		type.setDATA_METIER(dATA_METIER);
		type.setPDF_REASON(pDF_REASON);
		type.setPDF_LOCATION(pDF_LOCATION);
		type.setPDF_CONTACT(pDF_CONTACT);
		type.setDefaut(defaut);

		return type;
	}

	/**
		 * 
		 */
	public CertificationType createCertificationType(TagParameter type)
	{
		CertificationType typ = new CertificationType();
		// TODO Auto-generated constructor stub

		typ.setName(type.getName());
		typ.setId_type(type.getId_type());
		typ.setDATA_METIER(type.getDATA_METIER());
		typ.setPDF_REASON(type.getPDF_REASON());
		typ.setPDF_LOCATION(type.getPDF_LOCATION());
		typ.setPDF_CONTACT(type.getPDF_CONTACT());
		typ.setDefaut(type.isDefaut());

		return typ;
	}

	public boolean addDocumentToCertif(CertificationType type, DocumentPDF doc)
	{

		boolean state = false;
		if ((null != type) && (null != doc))
		{
			if (null == type.getDocuments())
				type.setDocuments(new HashSet<DocumentPDF>(null));
			if (!type.getDocuments().contains(doc))
			{
				type.getDocuments().add(doc);
				state = true;
			}
		}

		return state;
	}

	public boolean removeDocumentToCertif(CertificationType type,
			DocumentPDF doc)
	{

		boolean state = false;

		if (null == type.getDocuments())
			type.setDocuments(new HashSet<DocumentPDF>(null));
		if (type.getDocuments().contains(doc))
		{
			type.getDocuments().remove(doc);
			state = true;
		}

		return state;
	}

	/**
		 * 
		 */
	public boolean addCertificationType(String name, String id_type,
			String dATA_METIER, String pDF_REASON, String pDF_LOCATION,
			String pDF_CONTACT, boolean defaut)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			/*TagParameter type = new TagParameter(name, id_type, dATA_METIER,
					pDF_REASON, pDF_LOCATION, pDF_CONTACT, defaut);
			ControllerAjoutTypeCertification.getInstance().addType(type);*/
			
			TagParameter type = ControllerAjoutTypeCertification.getInstance()
					.getType(id_type, name);

			CertificationType certif = null;

			if (null == type.getType())
			{
				certif = createCertificationType(type);
				type.setType(certif);
			}
			else
				certif = type.getType();

			ArrayList<CertificationType> certifs = new ArrayList<CertificationType>();
			
			Query q = session
					.createQuery("from CertificationType as c where c.id_type = '"
							+ certif.getId_type() + "' and c.name = '" + certif.getName() + "'");
			certifs = (ArrayList<CertificationType>) q.list();
			
			if(certifs.isEmpty())
				session.save(certif);
			
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
		 * 
		 */
	public boolean addCertificationType(String name, String id_type)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			TagParameter type = ControllerAjoutTypeCertification.getInstance()
					.getType(id_type, name);

			CertificationType certif = null;

			if (null == type.getType())
			{
				certif = createCertificationType(type);
				type.setType(certif);
			}
			else
				certif = type.getType();

			ArrayList<CertificationType> certifs = new ArrayList<CertificationType>();
			
			Query q = session
					.createQuery("from CertificationType as c where c.id_type = '"
							+ certif.getId_type() + "' and c.name = '" + certif.getName() + "'");
			certifs = (ArrayList<CertificationType>) q.list();
			
			if(certifs.isEmpty())
				session.save(certif);
			
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
		 * 
		 */
	public boolean addCertificationType(TagParameter type)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = null;

			if (null == type.getType())
			{
				certif = createCertificationType(type);
				type.setType(certif);
			}
			else
				certif = type.getType();
			
			ArrayList<CertificationType> certifs = new ArrayList<CertificationType>();
			
			Query q = session
					.createQuery("from CertificationType as c where c.id_type = '"
							+ certif.getId_type() + "' and c.name = '" + certif.getName() + "'");
			certifs = (ArrayList<CertificationType>) q.list();
			
			if(certifs.isEmpty())
				session.save(certif);
			
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
		 *
		 */
	public boolean updateCertificationType(String editId, String editName,
			String id, String name)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = this.getByTypesIdAndName(editId,
					editName);
			if (null == certif)
				return false;

			TagParameter type = ControllerAjoutTypeCertification.getInstance()
					.getType(id, name);

			if (null == type.getType())
			{
				certif = createCertificationType(type);
				type.setType(certif);
			}
			else
				certif = type.getType();

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
		 *
		 */
	public boolean deleteCertificationType(String id, String name)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = this.getByTypesIdAndName(id, name);
			if (null == certif)
				return false;

			// ControllerAjoutTypeCertification.getInstance().removeType(certif.getId_type(),
			// certif.getName());
			session.delete(certif);
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
		 *
		 */
	public boolean deleteCertificationType(long idCertif)
	{
		if (idCertif <= 0)
			return false;
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			CertificationType certif = this.getById(idCertif);
			if (null == certif)
				return false;

			ControllerAjoutTypeCertification.getInstance().removeType(
					certif.getId_type(), certif.getName());
			session.delete(certif);
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
		 * 
		 */
	public CertificationType getById(long idCertif)
	{
		ArrayList<CertificationType> certifs = new ArrayList<CertificationType>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from CertificationType as c where c.id = '"
							+ idCertif + "'");
			certifs = (ArrayList<CertificationType>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
			if (certifs.size() == 1)
				return certifs.get(0);
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
		 * 
		 */
	public CertificationType getByTypesIdAndName(String id_type, String name)
	{
		ArrayList<CertificationType> certifs = new ArrayList<CertificationType>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from CertificationType as c where c.id_type = '"
							+ id_type + "' and c.name = '" + name + "'");
			certifs = (ArrayList<CertificationType>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
			if (certifs.size() == 1)
				return certifs.get(0);
			else
				return null;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
}
