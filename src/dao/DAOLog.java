package dao;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import domain.DocumentPDF;
import domain.EventType;
import domain.Log;
import domain.TypeLog;

public class DAOLog
{

	// SINGLETON
	public static DAOLog getInstance()
	{
		if (null == instance)
		{
			instance = new DAOLog();
		}
		return instance;
	}

	private DAOLog()
	{
	}

	private static DAOLog instance;

	public boolean addLog(TypeLog type, String ipadresse,
			String identifiant_client, EventType eventType,String longitude,String latitude)
	{
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Log log = new Log();
			Date now = new Date();
			log.setDate(now);
			log.setIdentifiant_client(identifiant_client);
			log.setIpadresse(ipadresse);
			log.setType(type);
			log.setEventype(eventType);
			log.setLongitude(longitude);
			log.setLatitude(latitude);

			session.save(log);
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

	public ArrayList<Log> getAllLogs()
	{
		ArrayList<Log> logs = new ArrayList<Log>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Query q = session.createQuery("from Log ");
			logs = (ArrayList<Log>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return logs;
		}
		return logs;
	}
	
	public ArrayList<Log> getLogPerType(TypeLog type)
	{
		ArrayList<Log> logs = new ArrayList<Log>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Log logExample = new Log();
			logExample.setType(type);
			
			logs = (ArrayList<Log>) session.createCriteria(Log.class).add(Example.create(logExample) ).list();

			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return logs;
		}
		return logs;
	}
	
	public ArrayList<Log> getLogPerEventType(EventType type)
	{
		ArrayList<Log> logs = new ArrayList<Log>();
		Session session = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Log logExample = new Log();
			logExample.setEventype(type);
			
			logs = (ArrayList<Log>) session.createCriteria(Log.class).add(Example.create(logExample) ).list();

			tx.commit();
			session.close();
			sessionFactory.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return logs;
		}
		return logs;
	}

}
