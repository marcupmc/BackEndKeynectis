package dao;



import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import domain.DocumentPDF;
import domain.EventType;
import domain.Log;
import domain.TypeLog;

public class DAOLog {


	//SINGLETON
	public static DAOLog getInstance() {
		if (null == instance) { 
			instance = new DAOLog();
		}
		return instance;
	}

	private DAOLog() {
	}


	private static DAOLog instance;

	public boolean addLog(TypeLog type, String ipadresse,String identifiant_client,EventType eventType){
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();

			Log log = new Log();
			Date now = new Date();
			log.setDate(now);
			log.setIdentifiant_client(identifiant_client);
			log.setIpadresse(ipadresse);
			log.setType(type);
			log.setEventype(eventType);

			session.save(log); 
			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public ArrayList<Log> getAllLogs(){
		ArrayList<Log> logs = new ArrayList<Log>();
		Session session = null;
		try{
			SessionFactory sessionFactory =
					new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Transaction tx = session.beginTransaction();


			Query q =session.createQuery("from Log ");
			logs = (ArrayList<Log>) q.list();

			tx.commit();
			session.close();
			sessionFactory.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return logs;
		}
		return logs;
	}

}
