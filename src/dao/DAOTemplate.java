package dao;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.DocumentPDF;
import domain.Log;
import domain.Template;
import domain.Utilisateur;

public class DAOTemplate {


	// SINGLETON
	public static DAOTemplate getInstance()
	{
		if (null == instance)
		{
			instance = new DAOTemplate();
		}
		return instance;
	}

	private DAOTemplate()
	{
	}

	private static DAOTemplate instance;


	public boolean addTemplate(String url, String nom){
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Template template =  new Template();
			template.setNom(nom);
			template.setUrl(url);
			
			session.save(template);
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
	
	public Template getById(long idTemplate)
	{
		ArrayList<Template> templates = new ArrayList<Template>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session
					.createQuery("from Template as c where c.id = '"
							+ idTemplate + "'");
			templates = (ArrayList<Template>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
			if (templates.size() == 1)
				return templates.get(0);
			else
				return null;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Template> getTemplateByRegex(String s)
	{
		ArrayList<Template> templates = new ArrayList<Template>();
		String regex = "%" + s + "%";
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			Query q = session
					.createQuery("from Template as c where c.nom like '"
							+ regex
							+ "'"
							+ " or c.url like '"
							+ regex
							+ "'"
							+ " or c.id like '"
							+ regex
							+ "'"
							+ " order by c.nom");
			templates = (ArrayList<Template>) q.list();
			session.close();
			sessionFactory.close();
			return templates;
		}
		catch (Exception e)
		{
			return null;
		}

	}
	

}
