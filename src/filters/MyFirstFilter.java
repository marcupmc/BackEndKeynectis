package filters;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import dao.DAOLog;
import dao.DAOUtilisateur;
import domain.EventType;
import domain.Log;
import domain.TypeLog;

public class MyFirstFilter  extends Filter<ILoggingEvent> {


	@Override
	public FilterReply decide(ILoggingEvent arg0) {
		String typeName = arg0.getMarker().getName();
		Log l = (Log)arg0.getArgumentArray()[0];
		TypeLog typeToSave  = null;
		EventType eventType = null;
		String longitude = "";
		String latitude ="";
		if(typeName.equals("CONNEXION_FAILED"))
		{
			typeToSave=TypeLog.CONNEXION_FAILED;
			eventType=EventType.USER;
		}
		else if(typeName.equals("CONNEXION"))
		{
			typeToSave=TypeLog.CONNEXION;
			eventType=EventType.USER;
			longitude=l.getLongitude();
			latitude=l.getLatitude();
		}
		else if(typeName.equals("DECONNEXION"))
		{
			typeToSave=TypeLog.DECONNEXION;
			eventType=EventType.USER;
		}
		else if(typeName.equals("DEMANDE_SIGNATURE"))
		{
			typeToSave=TypeLog.DEMANDE_SIGNATURE;
			eventType=EventType.USER;
		}
		else if(typeName.equals("CHANGEMENT_SIGNATURE"))
		{
			typeToSave=TypeLog.CHANGEMENT_SIGNATURE;
			eventType=EventType.USER;
		}
		else if(typeName.equals("AJOUT_CLIENT"))
		{
			typeToSave=TypeLog.AJOUT_CLIENT;
			eventType=EventType.ADMIN;
		}
		else if(typeName.equals("SUPPRESSION_CLIENT"))
		{
			typeToSave=TypeLog.SUPPRESSION_CLIENT;
			eventType=EventType.ADMIN;
		}
		else if(typeName.equals("AJOUT_DOCUMENT"))
		{
			typeToSave=TypeLog.AJOUT_DOCUMENT;
			eventType=EventType.ADMIN;
		}
		else if(typeName.equals("SUPPRESSION_DOCUMENT"))
		{
			typeToSave=TypeLog.SUPPRESSION_DOCUMENT;
			eventType=EventType.ADMIN;
		}
		else if(typeName.equals("AJOUT_SIGNATURE"))
		{
			typeToSave=TypeLog.AJOUT_SIGNATURE;
			eventType=EventType.ADMIN;
		}
		DAOLog.getInstance().addLog(typeToSave, l.getIpadresse(),l.getIdentifiant_client(),eventType,longitude,latitude);
		
		//TODO : A changer
		return null;
	}

}
