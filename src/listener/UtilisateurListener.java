package listener;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.event.DeleteEvent;
import org.hibernate.event.DeleteEventListener;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LoadEventListener;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;

public class UtilisateurListener implements LoadEventListener, DeleteEventListener,SaveOrUpdateEventListener {

	@Override
	public void onLoad(LoadEvent arg0, LoadType arg1) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(DeleteEvent arg0) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete(DeleteEvent arg0, Set arg1) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent arg0)
			throws HibernateException {
		
//		if(arg0.equals(obj))
		// TODO Auto-generated method stub
		
	}

}
